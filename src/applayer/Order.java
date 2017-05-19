package applayer;

class Order {
   private LineItem[] list = new LineItem[RecInfo.getMaxOrderSize()];
   private int price;

   Order() {
      this.price = 0;
   }

   void add(LineItem lineItem) {
      for (int i = 0; i < list.length; i++) {
         if (list[i] == null) {
            list[i] = lineItem;
            price += lineItem.getPrice();
            return;
         }
      }
      System.out.println("LineItem List Full");
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

   LineItem getOrder(int index) {
      return list[index];
   }

   int getPrice() {
      return price;
   }
}