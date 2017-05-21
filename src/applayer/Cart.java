package applayer;

import javax.servlet.http.HttpSession;
import javax.sound.sampled.Line;
import java.util.HashMap;

/**
 * Created by tommytroest on 19/05/2017.
 */
public class Cart {

   private LineItem[] list = new LineItem[RecInfo.getMaxOrderSize()];
   private int totalPrice = 0;

   // Constructor for Cart
   public Cart() {
      this.totalPrice = totalPrice;
   }

   // Add Items method to Cart
   private void addItem(LineItem lineItem) {
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
   private void deleteItem(int indexLine) {
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
   private void rearrangeList() {
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
   private void clearCart() {
      for (int i = 0; i < list.length; i++) {
         list[i] = null;
      }
   }

   // getList Method for Cart.
   private LineItem[] getList() {
      for (LineItem aList : list) {
         if (aList != null) {
            System.out.println(aList.getCake());
         }
      }
      System.out.println("End of the list.");
      return list;
   }

   // TESTING.
   public static void main(String[] args) {
      Cake testCake1 = new Cake(1,250,"TestKage","Den smager af fisk.");
      Cake testCake2 = new Cake(2,250,"TestCake2","Den smager også af fisk");

      LineItem testItem1 = new LineItem(testCake1, 2, "none");
      LineItem testItem2 = new LineItem(testCake2, 1,"Fisk");

      Cart testCart = new Cart();
      System.out.println("ADD");
      testCart.addItem(testItem1);
      testCart.addItem(testItem2);
      System.out.println("GET LIST");
      System.out.println(testCart.getList());
      System.out.println("DELETE ITEM");
      testCart.deleteItem(0);
      System.out.println("GET LIST");
      System.out.println(testCart.getList());
      System.out.println("CLEAR");
      testCart.clearCart();
      System.out.println("Current Cart: " + testCart.getList());
   }

}
