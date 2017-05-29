package applayer;

import java.time.LocalDate;

/**
 *  Made by Mikkel Olsen & Søren Diamant Skovgaard
 */

public class Order extends Cart {
   private int orderID;
   private LocalDate deliveryDate;
   private String address;
   private String note;

   public Order(int userID, LineItem[] list, String address, String note, LocalDate deliveryDate) {
      super(userID, list);

      this.address = address;
      this.note = note;
      this.deliveryDate = deliveryDate;
   }

   public int getOrderID() {
      return orderID;
   }

   public void setOrderID(int id) {
      this.orderID = id;
   }

   public LocalDate getDeliveryDate() {
      return deliveryDate;
   }

   public String getAddress() {
      return address;
   }

   public String getNote() {
      return note;
   }
}