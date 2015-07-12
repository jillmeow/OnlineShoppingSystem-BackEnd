/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.*;
import domain.*;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.fest.swing.core.matcher.JButtonMatcher.withText;
import static org.fest.swing.core.matcher.DialogMatcher.withTitle;
import org.fest.swing.fixture.DialogFixture;
import org.mockito.ArgumentCaptor;

/**
 *
 * @author mirji507
 */
public class ProductDialogTest {
    private ProductDAO dao;
    private DialogFixture fest;
    
    public ProductDialogTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //add some categories for testing with
        Collection<String> categories = new TreeSet<>();
        categories.add("Flange");
        categories.add("Widget");
        categories.add("Classic");
        //create a mock for the DAO
        dao = mock(ProductDAO.class);
        //sub the getCategories method to return the test categories
        when(dao.getAllCategories()).thenReturn(categories);
        
    }
    
    @After
    public void tearDown() {
        fest.cleanUp();
    }
    @Test
   public void testEdit(){
      Product product1;
      product1 = new Product("FL1234", "Fuzzy Flange", "Flange that needs a shave", "Flange", 3.21, 5);
      
      ProductDialog dialog = new ProductDialog(null, true, product1, dao);
      
      fest = new DialogFixture(dialog);
      fest.show();
      
      //slow down the robot a bit -- default is 30 millis
      fest.robot.settings().delayBetweenEvents(100);
      
      //ensure that the UI components contains the Jack's details
      fest.textBox("idField").requireText("FL1234");
      fest.textBox("nameField").requireText("Fuzzy Flange");
      fest.textBox("descriptionField").requireText("Flange that needs a shave");
      fest.comboBox("cmbCategory").requireSelection("Flange");
      fest.textBox("priceField").requireText("3.21");
      fest.textBox("quantityField").requireText("5");
      
      //edit the name, description, category, price, and quantity
      fest.textBox("nameField").selectAll().enterText("Classic Flange");
      fest.textBox("descriptionField").selectAll().enterText("An old but classic flange");
      fest.comboBox("cmbCategory").selectItem("Classic");
      fest.textBox("priceField").selectAll().enterText("5.00");
      fest.textBox("quantityField").selectAll().enterText("10");

      //click the save button
      fest.button("btnSave").click();
      
      //create a Mockito argument captor to use to retrieve the passed product
      //from the mocked DAO
      ArgumentCaptor<Product> argument 
              = ArgumentCaptor.forClass(Product.class);
      //ensure that the DAO save method was called, 
      //and capture the passed product
      verify(dao).save(argument.capture());
      
      //retrieve the passed product from the captor
      Product edited = argument.getValue();
      
      //ensure that the changes were save
      assertEquals("Ensure the name changed", "Classic Flange", edited.getName());
      assertEquals("Ensure the description changed", "An old but classic flange", edited.getDescription());
      assertEquals("Ensure the category changed", "Classic", edited.getCategory());
      assertEquals("Ensure the price changed", 5.00, new Double(edited.getPrice()), 0);
      assertEquals("Ensure the quantity changed", "105", String.valueOf(edited.getQuantity())); //causing error at the test
   }
    
    @Test
   public void testSave(){
      //create the dialog passing in the mocked DAO
      ProductDialog dialog = new ProductDialog(null, true, dao);
      
      //use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
      
      //slow down the FEST robot a bit -- default is 30 millis
      fest.robot.settings().delayBetweenEvents(75);
      //Enter some details into the UI components
      fest.textBox("idField").enterText("FL1234");
      fest.textBox("nameField").enterText("Fuzzy Flange");
      fest.textBox("descriptionField").enterText("Flange that needs a shave");
      fest.comboBox("cmbCategory").selectItem("Flange");
      fest.textBox("priceField").enterText("3.21");
      fest.textBox("quantityField").enterText("5");
      
      //click the save button
      fest.button("btnSave").click();
      
      //create a Mockito argument captor to use to retrieve the 
      //passed product form the mocked DAO
      ArgumentCaptor<Product> argument
              = ArgumentCaptor.forClass(Product.class);
      //Verify that the DAO save method was called, and capture the 
      //passed product
      verify(dao).save(argument.capture());
      //retrieve the passed product from the captor
      Product edited = argument.getValue();
      //ensure that the student's ID, name, and major were properly saved
      assertEquals("Ensure the Id saved", "FL1234", edited.getProductID());
      assertEquals("Ensure the name saved", "Fuzzy Flange", edited.getName());
      assertEquals("Ensure the description saved", "Flange that needs a shave", edited.getDescription());
      assertEquals("Ensure the category saved", "Flange", edited.getCategory());
      assertEquals("Ensure the price saved", 3.21, new Double(edited.getPrice()), 0);
      assertEquals("Ensure the quantity saved", "5", String.valueOf(edited.getQuantity()));
   }
}
