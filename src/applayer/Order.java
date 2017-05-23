package applayer;

import java.time.LocalDate;

class Order {
   private LineItem[] list = new LineItem[RecInfo.getMaxOrderSize()];
   private int userID;
   private int total;
   private LocalDate deliveryDate;
   private String address;
   private String note;

   Order(int userID, LocalDate deliveryDate, String address, String note) {
      this.userID = userID;
      this.total = 0;
      this.deliveryDate = deliveryDate;
      this.address = address;
      this.note = note;
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

   LineItem getLineItem(int index) {
      return list[index];
   }

   int getTotal() {
      return total;
   }

   LocalDate getDeliveryDate() {
      return deliveryDate;
   }

   String getAddress() {
      return address;
   }

   String getNote() {
      return note;
   }
}