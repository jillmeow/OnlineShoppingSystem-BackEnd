/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import domain.*;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jillianmirandilla
 */
public class DaoTest {
   private ProductDAO dao = new ProductJdbcDao();
   
   private String productid1 = "FL1234";
   private Product product1;
   private String product1category = "Flange";
   
   private String productid2 = "WD1347";
   private Product product2;
   private String product2category = "Widget";
   

   
    @Before
    public void setUp() {
        product1 = new Product(productid1, "Fuzzy Flange", "Flange that needs a shave", product1category, 3.21, 5);
        product2 = new Product(productid2, "Slimy Widget", "Widget for fun", product2category, 4.00, 10);
        
        dao.save(product1);
        dao.save(product2);
    }
    
    @After
    public void tearDown() {
        dao.delete(product1);
        dao.delete(product2);
    }
    
    @Test
   public void testDaoSaveAndDelete(){
      //create student for testing
      Product product3 = new Product("FL4224","Polkadot Flange", "A Flange that has a polkadot pattern", "Flange",3.21, 20);
      
      //save the student using DAO
      dao.save(product3);
      
      //retrieve the same student via DAO
      Product retrieved = dao.getById("FL4224");
      
      //ensure that the student we saved is the one we got back
      assertEquals("Retrieved product should be the same as the saved one",
              product3, retrieved);
      
      //delete the student via the DAO
      dao.delete(product3);
      
      //try to retrieve the deleted student
      retrieved = dao.getById("FL4224");
      
      //ensure that the student was not retrieved(should be null)
      assertNull("Product should no longer exist", retrieved);
   }
    
    @Test
   public void testDAOGetAll(){
      //call getAll
      Collection<Product> products = dao.getAll();
      
      //ensure the result includes the test students
      assertTrue("Product1 should exist in the result", products.contains(product1));
      assertTrue("Product2 should exist in the result", products.contains(product2));
      
      //find Jack - result is not a map, so we have to sequentially search
      for(Product p : products){
         if(p.equals(product1)){
            //ensure that Jack's details were correctly retrieved
            assertEquals(product1.getProductID(), p.getProductID());
            assertEquals(product1.getName(), p.getName());
            assertEquals(product1.getDescription(), p.getDescription());
            assertEquals(product1.getCategory(), p.getCategory());
            assertEquals(product1.getPrice(), p.getPrice(), 0);
            assertEquals(product1.getQuantity(), p.getQuantity());
         }
      }
   }
    
    @Test
   public void testDaoGetById() {
      Product p;
      p = new Product(product1.getProductID(), product1.getName(), product1.getDescription(), product1.getCategory(), product1.getPrice(), product1.getQuantity());
      Product retrievedProduct = dao.getById(productid1);
      
      assertEquals("This ID should be Product1, not another product", p, retrievedProduct);

      assertEquals(p.getProductID(), retrievedProduct.getProductID());
      assertEquals(p.getName(), retrievedProduct.getName());
      assertEquals(p.getDescription(), retrievedProduct.getDescription());
      assertEquals(p.getCategory(), retrievedProduct.getCategory());
      //assertEquals(p.getPrice(), retrievedProduct.getPrice());
      assertEquals(p.getQuantity(), retrievedProduct.getQuantity());
      // call getById using a non..existent ID
      Product otherId = dao.getById("SD1532");
      // ensure that the result is null
      assertNull("Product ID doesn't exist", otherId);
   }
    
    @Test
   public void testDaoGetAllMajors(){

      Collection<String> categories = dao.getAllCategories();

      assertTrue("Product1's category should exist in the result", categories.contains(product1.getCategory()));
      assertTrue("Product2's category should exist in the result", categories.contains(product2.getCategory()));
      
   }
    
    @Test
   public void testDaoEditStudent(){

      product1.setName("Stripe Flange");

      dao.save(product1);

      Product changed = dao.getById(productid1);
      
      //Ensure that the name was changed
      assertTrue("Product1's name should be changed", "Stripe Flange".equals(changed.getName()));
   }
    
    @Test
   public void testDaoGetByCategory() {
      Collection<Product> flanges = dao.getByCategory(product1category);
      
      assertTrue("Should contain a flange", flanges.contains(product1));
      assertFalse("Should NOT contain a widget", flanges.contains(product2));
      
       for(Product p : flanges){
         if(p.equals(product1)){
            //ensure that Jack's details were correctly retrieved
            assertEquals(product1.getProductID(), p.getProductID());
            assertEquals(product1.getName(), p.getName());
            assertEquals(product1.getDescription(), p.getDescription());
            assertEquals(product1.getCategory(), p.getCategory());
            assertEquals(product1.getPrice(), p.getPrice(), 0);
            assertEquals(product1.getQuantity(), p.getQuantity());
         }
      }
      
   }
}