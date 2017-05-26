package applayer;

import java.time.LocalDate;

/**
 *  Lavet af Mikkel Olsen
 */

public class Order {
   private LineItem[] list = new LineItem[RecInfo.getMaxOrderSize()];
   private int userID;
   private int orderID;
   private int total;
   private LocalDate deliveryDate;
   private String address;
   private String note;

   public Order(int userID, LineItem[] list, String address, String note, LocalDate deliveryDate) {
      this.userID = userID;
      this.list = list;
      this.address = address;
      this.note = note;
      this.deliveryDate = deliveryDate;

      this.total = 0;
      for (LineItem item : this.list) {
         if (item != null) {
            this.total += item.getPrice();
         }
      }
   }

   public int getUserID() {
      return userID;
   }

   public int getOrderID() {
      return orderID;
   }

   public void setOrderID(int id) {
      this.orderID = id;
   }

   public LineItem[] getList() {
      return list;
   }

   LineItem getLineItem(int index) {
      return list[index];
   }

   public int getTotal() {
      return total;
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

   public int getListLength() {
      int length = 0;

      for (LineItem aList : list) {
         if (aList != null) {
            length += 1;
         }
      }

      return length;
   }
}