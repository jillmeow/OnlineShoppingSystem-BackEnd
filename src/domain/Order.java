/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 *
 * @author mirji507
 */
public class Order {
   private Customer customer;
   private List<OrderItem> orderItems = new ArrayList<>();
   private Date orderDate = new Date();
   private Integer orderID = null;

   public Order(Customer customer) {
      this.customer = customer;
   }

   public Customer getCustomer() {
      return customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   public Date getOrderDate() {
      return orderDate;
   }

   public void setOrderDate(Date orderDate) {
      this.orderDate = orderDate;
   }

   public List<OrderItem> getOrderItems() {
      return orderItems;
   }

   public void setOrderItems(List<OrderItem> orderItems) {
      this.orderItems = orderItems;
   }

   public int getOrderID() {
      return orderID;
   }

   public void setOrderID(int orderID) {
      this.orderID = orderID;
   }
   
   public double getTotal(){
      double total = 0;
   
      // loop through order items
        // for each
      for(OrderItem order : orderItems){
         total = total + order.getItemTotal();
      }
          // call getItemTotal, and add to total
      return total;
      
      
   }
   
   public String getCurrencyTotal() {
      NumberFormat df = DecimalFormat.getCurrencyInstance();
      return df.format(getTotal());
   }
   
   public void addItem(OrderItem item){
      orderItems.add(item);
   }

   @Override
   public String toString() {
      return "Order{" + "orderItems=" + orderItems + ", orderDate=" + orderDate + ", customerID=" + orderID + '}';
   }
   
   
}
