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
public interface ProductDAO {
   
   public void save(Product product);
   
   public void delete (Product product);
   
   public Collection<Product> getAll();
   
   public Collection<String> getAllCategories();
   
   public Product getById(String productId);
   
   public Collection<Product> getByCategory(String category);
}
