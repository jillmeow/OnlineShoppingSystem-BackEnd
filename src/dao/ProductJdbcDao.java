/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.*;
import java.sql.*;

/**
 *
 * @author mirji507
 */
public class ProductJdbcDao implements ProductDAO {

    @Override
    public void save(Product product) {
        try (
            //get connection to database
            Connection connection = JdbcConnection.getConnection();
  
            //create the SQL statement
            PreparedStatement stmt = connection.prepareStatement(
                "merge into products (productid,name,description,category,price,quantity) values (?,?,?,?,?,?)");
        ) {

            //copy the data from the student domain object into the statement
            stmt.setString(1, product.getProductID());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getCategory());
            stmt.setDouble(5, product.getPrice());
            stmt.setInt(6, product.getQuantity());

            stmt.executeUpdate();
        } catch (SQLException ex) {
               throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void delete(Product product) {
        try(
          Connection connection = JdbcConnection.getConnection();
          PreparedStatement stmt = connection.prepareStatement(
                  "delete from products where productid = ?");
          ) {
         stmt.setString(1, product.getProductID());
         stmt.executeUpdate();
      } catch(SQLException ex){
          throw new DAOException(ex.getMessage(), ex);
      }
    }

    @Override
    public Collection<Product> getAll() {
        try(
          Connection connection = JdbcConnection.getConnection();
          PreparedStatement stmt = connection.prepareStatement(
                  "select * from products order by productid");
          ResultSet rs = stmt.executeQuery();
          ){
         Collection<Product> products = new ArrayList<>();
         while (rs.next()){
            String id = rs.getString("productid");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String category = rs.getString("category");
            Double price = rs.getDouble("price");
            Integer quantity = rs.getInt("quantity");
            
            Product p = new Product(id, name, description, category, price, quantity);
            
            products.add(p);
         }
         return products;
      } catch (SQLException ex){
         throw new DAOException(ex.getMessage(), ex);
      }
    }

    @Override
    public Collection<String> getAllCategories() {
        try(
          Connection connection = JdbcConnection.getConnection();
          PreparedStatement stmt = connection.prepareStatement(
                  "select distinct category from products order by category");
          ResultSet rs = stmt.executeQuery();
          ){
         Collection<String> categories = new ArrayList<>();
         while (rs.next()){
            String category = rs.getString("category");
            
            
            categories.add(category);
         }
         return categories;
      } catch (SQLException ex){
         throw new DAOException(ex.getMessage(), ex);
      }
    }

    @Override
    public Product getById(String productId) {
        try{
          Connection connection = JdbcConnection.getConnection();
          PreparedStatement stmt = connection.prepareStatement(
                  "select * from products where productid=?");
              stmt.setString(1, productId);
          ResultSet rs = stmt.executeQuery();
         
         Product product = null;
         if (rs.next()){
            String id = rs.getString("productid");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String category = rs.getString("category");
            Double price = rs.getDouble("price");
            Integer quantity = rs.getInt("quantity");
            
            product = new Product(id, name, description,category,price,quantity);
         }
         return product;
      } catch (SQLException ex){
         throw new DAOException(ex.getMessage(), ex);
      }
    }


    @Override
    public Collection<Product> getByCategory(String category) {
         try(
          Connection connection = JdbcConnection.getConnection();
          PreparedStatement stmt = connection.prepareStatement(
                  "select * from products where category = ? order by productid");
          ){
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();            
         Collection<Product> products = new ArrayList<>();
         while (rs.next()){
            String id = rs.getString("productid");
            String name = rs.getString("name");
            String description = rs.getString("description");
            Double price = rs.getDouble("price");
            Integer quantity = rs.getInt("quantity");
            
            Product p = new Product(id, name, description, category, price, quantity);
            
            products.add(p);
         }
         return products;
      } catch (SQLException ex){
         throw new DAOException(ex.getMessage(), ex);
      }
    }
    
}
