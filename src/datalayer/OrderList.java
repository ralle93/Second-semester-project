package datalayer;

public class OrderList {
   private Order[] orderlist = new Order[5];
   private int price;

   OrderList() {
      this.price = 0;
   }

   void add(Order order) {
      for (int i = 0; i < orderlist.length; i++) {
         if (orderlist[i] == null) {
            orderlist[i] = order;
            price += order.getPrice();
            System.out.println("Order Added");
            return;
         }
      }
      System.out.println("Order List Full");
   }

   void remove(int index) {
      for (int i = 0; i < orderlist.length; i++) {
         if (orderlist[i] != null && i == index) {
            price -= orderlist[i].getPrice();
            orderlist[i] = null;
            shiftList();
            System.out.println("Order Removed");
            return;
         }
      }
      System.out.println("Order Not Found");
   }

   void shiftList() {
      for (int i = 0; i < orderlist.length; i++) {
         if (orderlist[i] == null) {
            if (i + 1 < orderlist.length && orderlist[i + 1] != null) {
               orderlist[i] = orderlist[i + 1];
               orderlist[i + 1] = null;
            }
         }
      }
   }

   Order getOrder(int index) {
      return orderlist[index];
   }

   int getPrice() {
      return price;
   }
}