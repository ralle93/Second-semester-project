package applayer;

import java.util.Date;

class Order {
   private LineItem[] list = new LineItem[RecInfo.getMaxOrderSize()];
   private int total;
   private Date deliveryDate;
   private String note;

   Order(Date deliveryDate, String note) {
      this.total = 0;
      this.deliveryDate = deliveryDate;
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

   LineItem getLineItem(int index) {
      return list[index];
   }

   int getTotal() {
      return total;
   }

   Date getDeliveryDate() {
      return deliveryDate;
   }

   String getNote() {
      return note;
   }
}