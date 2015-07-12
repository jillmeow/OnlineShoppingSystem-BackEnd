/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author mirji507
 */
public class OrderItem {
   private Product product;
   private Order order;
   private Integer purchasedQuantity;

   public OrderItem(Order order, Product product, Integer purchasedQuantity) {
      this.order = order;
      this.product = product;
      this.purchasedQuantity = purchasedQuantity;
   }

   public Order getOrder() {
      return order;
   }

   public void setOrder(Order order) {
      this.order = order;
   }

   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public Integer getPurchasedQuantity() {
      return purchasedQuantity;
   }

   public void setPurchasedQuantity(Integer purchasedQuantity) {
      this.purchasedQuantity = purchasedQuantity;
   }
   public double getItemTotal(){
     return product.getPrice()* getPurchasedQuantity();
   }

   @Override
   public String toString() {
      return "Product: "+product + "Item Total: " + getItemTotal();
   }
   
   
}
