package datalayer;

class OrderList {
   private Order[] orderList = new Order[RecInfo.getMaxOrderSize()];
   private int price;

   OrderList() {
      this.price = 0;
   }

   void add(Order order) {
      for (int i = 0; i < orderList.length; i++) {
         if (orderList[i] == null) {
            orderList[i] = order;
            price += order.getPrice();
            return;
         }
      }
      System.out.println("Order List Full");
   }

   void remove(int index) {
      for (int i = 0; i < orderList.length; i++) {
         if (orderList[i] != null && i == index) {
            price -= orderList[i].getPrice();
            orderList[i] = null;
            shiftList();
            return;
         }
      }
      System.out.println("Order Not Found");
   }

   private void shiftList() {
      for (int i = 0; i < orderList.length; i++) {
         if (orderList[i] == null) {
            if (i + 1 < orderList.length && orderList[i + 1] != null) {
               orderList[i] = orderList[i + 1];
               orderList[i + 1] = null;
            }
         }
      }
   }

   Order getOrder(int index) {
      return orderList[index];
   }

   int getPrice() {
      return price;
   }
}