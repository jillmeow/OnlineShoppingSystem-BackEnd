/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.*;
import domain.*;
import gui.helpers.SimpleListModel;
import java.util.*;
import org.fest.swing.fixture.DialogFixture;
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
public class ProductReportDialogTest {
    private Collection<Product> products;
    private Product product1;
    private Product product2;
   
    private ProductDAO dao;
    private ProductReportDialog dialog;
   
    private DialogFixture fest;
    
    public ProductReportDialogTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
      product1 = new Product("FL1234", "Fuzzy Flange", "Flange that needs a shave", "Flanges", 3.21, 5);
      product2 = new Product("WD1347", "Slimy Widget", "Widget for fun", "Widget", 4.00, 10);
      
      products = new TreeSet<>();
      products.add(product1);
      products.add(product2);
      
      dao = mock(ProductDAO.class);
      when(dao.getAll()).thenReturn(products);
      
      Collection<String> categories = new TreeSet<>();
      categories.add("Flanges");
      categories.add("Widget"); 
      
      when(dao.getById("FL1234")).thenReturn(product1);
      when(dao.getById("WD1347")).thenReturn(product2);
      
      when(dao.getAllCategories()).thenReturn(categories);
      
      Collection<Product> flanges = new ArrayList<>();
      flanges.add(product1);
      
      when(dao.getByCategory("Flanges")).thenReturn(flanges);
      
      
      dialog = new ProductReportDialog(null, true, dao);
    }
    
    @After
    public void tearDown() {
        fest.cleanUp();
    }
    @Test
   public void testReportView(){
      //use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
      
      //slow down the FEST robot a bit -- default is 30 millis
      fest.robot.settings().delayBetweenEvents(75);
      
      //ensure getAll was called
      verify(dao).getAll();
      
      //get the JList's model
      SimpleListModel model
              = (SimpleListModel) fest.list().component().getModel();
      //check that the model actually contains the students
      assertTrue("Ensure list contain Product1", model.contains(product1));
      assertTrue("Ensure list contain Product2", model.contains(product2));
      assertTrue("Ensure list contain the correct number of products", 
              model.getSize() == products.size());
   }
    @Test
   public void testReportDelete(){
      //use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
      
      //slow down the FEST robot a bit -- default is 30 millis
      fest.robot.settings().delayBetweenEvents(75);
      
      fest.list().selectItem(product2.toString());
      
      //click the delete button
      fest.button("btnDelete").click();
      
      DialogFixture confirmDialog
              = fest.dialog(withTitle("Select an Option")
              .andShowing()).requireVisible();
      
      confirmDialog.button(withText("No")).click();
      
      verify(dao, never()).delete(null);
      
      fest.list().selectItem(product2.toString());

      fest.button("btnDelete").click();

      confirmDialog
              = fest.dialog(withTitle("Select an Option")
               .andShowing()).requireVisible();

      confirmDialog.button(withText("Yes")).click();
      
      //create a Mockito argument captor to use to retrieve the passed student
      //from the mocked DAO
      ArgumentCaptor<Product> argument
              = ArgumentCaptor.forClass(Product.class);

      verify(dao).delete(argument.capture());
      
      //retrieve the passed student from the captor
      Product deletedProduct = argument.getValue();
      
      //ensure that the correct student was deleted
      assertEquals("Deleted product should be Product2", product2, deletedProduct);
   }
    @Test
   public void testReportSearch(){
      //use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
      
      //slow down the FEST robot a bit -- default is 30 millis
      fest.robot.settings().delayBetweenEvents(75);
      
      fest.textBox("idTextField").enterText("FL1234");
      
      fest.button("btnSearch").click();
      
      verify(dao).getById(product1.getProductID());

      SimpleListModel model
              = (SimpleListModel) fest.list().component().getModel();
      
      //ensure that the list is displaying ONLY Jack
      assertTrue("Ensure list contain Product1", model.contains(product1));
      
      assertTrue("Ensure list contain exactly Product1", 
              model.getSize() == 1); 
   }
    @Test
   public void testReportEdit(){
      //use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();

      fest.robot.settings().delayBetweenEvents(75);
      
      fest.list().selectItem(product2.toString());
      
      fest.button("btnEdit").click();

      DialogFixture editDialog = fest.dialog("productDialog").requireVisible();
      
      editDialog.textBox("idField").requireText("WD1347");
   }
    
    
   @Test
   public void testReportCategoryFilter(){
      //use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
      
      //slow down the FEST robot a bit -- default is 30 millis
      fest.robot.settings().delayBetweenEvents(200);
      fest.comboBox("cmbCategoryFilter").selectItem("Flanges");
      
      //ensure that getById was called and was passed Jack's ID
      verify(dao).getByCategory("Flanges");
      
      //get the JList's model
      SimpleListModel model
              = (SimpleListModel) fest.list().component().getModel();
      
      //ensure that the list is displaying ONLY Jack
      assertTrue("Ensure list contains all the products that is in that category", model.contains(product1));
      assertFalse("Ensure list contains all the products that is in that category", model.contains(product2));
      
      assertTrue("Ensure list contain the correct number of students", 
              model.getSize() == 1);
   }
}
