/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author mirji507
 */
public class Product implements Comparable<Product>{
   @NotBlank(message="Product ID must be provided.")
   @Length(min=6, message="ID must contains at least 6 characters.")
   private String productID;
   
   @NotBlank(message="Name must be provided.")
   private String name;
   
   private String description;
   
   @NotBlank(message="Category must be provided")
   private String category;
   
   @NotNull(message="Price must be provided.")
   private Double price;
   
   @NotNull(message="Quantity must be provided.")
   private Integer quantity;

   public Product(String productID, String name, String description, String category, Double price, Integer quantity) {
      this.productID = productID;
      this.name = name;
      this.description = description;
      this.category = category;
      this.price = price;
      this.quantity = quantity;
   }
   
   public Product(){
   }

   public String getProductID() {
      return productID;
   }

   public void setProductID(String productID) {
      this.productID = productID;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   public Integer getQuantity() {
      return quantity;
   }

   public void setQuantity(Integer quantity) {
      this.quantity = quantity;
   }

 

   @Override
   public String toString() {
      return "ID: " + productID + ", Name: " + name;
   }

    @Override
    public int compareTo(Product otherProduct) {
        String currentID = this.getProductID();
        String otherID = otherProduct.getProductID();
        return currentID.compareTo(otherID);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.productID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.productID, other.productID)) {
            return false;
        }
        return true;
    }
   
   
   
}
