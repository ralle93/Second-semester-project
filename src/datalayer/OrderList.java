package datalayer;

class OrderList {
   private Order[] list = new Order[RecInfo.getMaxOrderSize()];
   private int price;

   OrderList() {
      this.price = 0;
   }

   void add(Order order) {
      for (int i = 0; i < list.length; i++) {
         if (list[i] == null) {
            list[i] = order;
            price += order.getPrice();
            return;
         }
      }
      System.out.println("Order List Full");
   }

   void remove(int index) {
      for (int i = 0; i < list.length; i++) {
         if (list[i] != null && i == index) {
            price -= list[i].getPrice();
            list[i] = null;
            shiftList();
            return;
         }
      }
      System.out.println("Order Not Found");
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

   Order getOrder(int index) {
      return list[index];
   }

   int getPrice() {
      return price;
   }
}