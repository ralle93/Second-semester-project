package applayer;

import datalayer.Data;

import java.time.LocalDate;

public class Order {
   private LineItem[] list = new LineItem[RecInfo.getMaxOrderSize()];
   private int userID;
   private int orderID;
   private int total;
   private LocalDate deliveryDate;
   private String address;
   private String note;

   public Order(int userID, LocalDate deliveryDate, String address, String note) {
      this.userID = userID;
      this.orderID = 0;
      this.total = 0;
      this.deliveryDate = deliveryDate;
      this.address = address;
      this.note = note;
   }

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

   void add(LineItem lineItem) {
      for (int i = 0; i < list.length; i++) {
         if (list[i] == null) {
            list[i] = lineItem;
            total += lineItem.getPrice();
            return;
         }
      }
      System.out.println("LineItem List Full");
   }

   void remove(int index) {
      for (int i = 0; i < list.length; i++) {
         if (list[i] != null && i == index) {
            total -= list[i].getPrice();
            list[i] = null;
            shiftList();
            return;
         }
      }
      System.out.println("LineItem Not Found");
   }

   private void shiftList() {
      for (int i = 0; i < list.length; i++) {
         if (list[i] == null) {
            if (i + 1 < list.length && list[i + 1] != null) {
               list[i] = list[i + 1];
               list[i + 1] = null;
            }
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