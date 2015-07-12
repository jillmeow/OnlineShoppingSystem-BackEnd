/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import domain.Product;
import java.util.*;
/**
 *
 * @author mirji507
 */
public class ProductCollectionsDAO implements ProductDAO {
   private static Map<String, Product> products = new TreeMap<>();
   private static Set<String> categories = new TreeSet<>();
   
   @Override
   public void save(Product product){
      products.put(product.getProductID(), product);
      categories.add(product.getCategory());
   }
   
   @Override
   public void delete(Product product){
       products.remove(product.getProductID());
   }
   
   @Override
   public Collection<Product> getAll(){
      return products.values();
   }
   
   @Override
   public Collection<String> getAllCategories(){
       return categories;
   }
   @Override
   public Product getById(String productId){
       return products.get(productId);
   }
   
   @Override
   public Collection<Product> getByCategory(String category){
       Collection<Product> p = new ArrayList<>();
       for(Product product : products.values()){
           if(product.getCategory().equals(category)){
               p.add(product);
           }
       }
       return p;
   }
}
