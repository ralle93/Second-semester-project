package applayer;

import java.time.LocalDateTime;

/**
 * Created by tommytroest on 19/05/2017.
 */
public class Cart {

   private LineItem[] list = new LineItem[RecInfo.getMaxOrderSize()];
   private int userID;
   private LocalDateTime creationTime;
   private int totalPrice = 0;

   // Constructor for Cart
   public Cart(int userID) {
      this.userID = userID;
      this.creationTime = LocalDateTime.now();
      this.totalPrice = totalPrice;
   }

   // Add Items method to Cart
   public void addItem(LineItem lineItem) {
      for (int i = 0; i <= list.length; i++) {
         if (list[i] == null) {
            list[i] = lineItem;
            totalPrice += lineItem.getPrice();
            return;
         }
      }
      System.out.println("Cannot add any more items to basket.");
   }

   // Delete Existing item from Cart.
   public void deleteItem(int indexLine) {
      for (int i = 0; i < list.length; i++) {
         if (list[i] != null && i == indexLine) {
            totalPrice -= list[i].getPrice();
            list[i] = null;
            rearrangeList();
            return;
         }
      }
      System.out.println("No such item in the cart");
   }

   // Rearrange Method for Cart.
   public void rearrangeList() {
      for (int i = 0; i < list.length; i++) {
         if (list[i] == null) {
            if ( list[i] != null && i < list.length) {
               list[i] = list[i + 1];
               list[i + 1] = null;
            }
         }
      }
   }

   // ClearCart Method.
   public void clearCart() {
      for (int i = 0; i < list.length; i++) {
         list[i] = null;
      }
   }

   // getList Method for Cart.
   public LineItem[] getList() {
      for (LineItem aList : list) {
         if (aList != null) {
            System.out.println(aList.getCake());
         }
      }
      System.out.println("End of the list.");
      return list;
   }

   public void printListItem(int index) {
      String cakeName = list[index].getCake().getName();
      int amount = list[index].getAmount();
      int price = list[index].getPrice();

      System.out.println(cakeName);
      System.out.println(amount);
      System.out.println(price);
   }

   public int getUserID() {
      return userID;
   }

   // TESTING.
   public static void main(String[] args) {
      Cake testCake1 = new Cake(1,250,"TestKage","Den smager af fisk.");
      Cake testCake2 = new Cake(2,250,"TestCake2","Den smager også af fisk");
      Cake testCake3 = new Cake(3,300,"testCake3","Dette er en kage som smager lidt af Æsel.");

      LineItem testItem1 = new LineItem(testCake1, 2);
      LineItem testItem2 = new LineItem(testCake2, 1);
      LineItem testItem3 = new LineItem(testCake3, 3);

      /*Cart testCart = new Cart();
      System.out.println("ADD");
      testCart.addItem(testItem1);
      testCart.addItem(testItem2);
      testCart.addItem(testItem3);
      testCart.printListItem(0);
      testCart.printListItem(1);
      testCart.printListItem(2);
      System.out.println("CLEAR");
      testCart.clearCart();
      System.out.println("Current Cart: " + testCart.getList());*/
   }

}
