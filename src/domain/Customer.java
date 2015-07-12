/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Objects;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author mirji507
 */
public class Customer {
   @NotBlank(message="Username must be provided")
   private String username;
   
   @NotBlank(message="Name must be provided")
   private String name;
   
   @NotBlank(message="Address must be provided")
   private String address;
   
   @NotBlank(message="Credit card must be provided")
   private String creditcard;
   
   @NotBlank(message="Password must be provided")
   private String password;

   public Customer(String username, String name, String address, String creditcard, String password) {
      this.username = username;
      this.name = name;
      this.address = address;
      this.creditcard = creditcard;
      this.password = password;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getCreditcard() {
      return creditcard;
   }

   public void setCreditcard(String creditcard) {
      this.creditcard = creditcard;
   }

   public String getPassword() {
      return password;
   }

   @Override
   public int hashCode() {
      int hash = 3;
      hash = 29 * hash + Objects.hashCode(this.username);
      hash = 29 * hash + Objects.hashCode(this.name);
      hash = 29 * hash + Objects.hashCode(this.address);
      hash = 29 * hash + Objects.hashCode(this.creditcard);
      hash = 29 * hash + Objects.hashCode(this.password);
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
      final Customer other = (Customer) obj;
      if (!Objects.equals(this.username, other.username)) {
         return false;
      }
      if (!Objects.equals(this.name, other.name)) {
         return false;
      }
      if (!Objects.equals(this.address, other.address)) {
         return false;
      }
      if (!Objects.equals(this.creditcard, other.creditcard)) {
         return false;
      }
      if (!Objects.equals(this.password, other.password)) {
         return false;
      }
      return true;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Override
   public String toString() {
      return "Customer{" + "username=" + username + ", name=" + name + ", address=" + address + ", creditcard=" + creditcard + ", password=" + password + '}';
   }
}
