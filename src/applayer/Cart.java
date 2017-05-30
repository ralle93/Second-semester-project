package applayer;

/**
 *  Made by Tommy Troest & Mikkel Olsen
 */

public class Cart {
   private LineItem[] list = new LineItem[RecInfo.getMaxOrderSize()];
   private int userID;
   private int totalPrice;

   // Constructor for Cart
   public Cart(int userID) {
      this.userID = userID;
      this.totalPrice = 0;
   }

   Cart(int userID, LineItem[] list) {
      this.userID = userID;
      this.list = list;

      this.totalPrice = 0;
      for (LineItem item : this.list) {
         if (item != null) {
            this.totalPrice += item.getPrice();
         }
      }
   }

   public int getUserID() {
      return userID;
   }

   public int getTotalPrice() {
      return totalPrice;
   }

   public boolean isEmpty() {
      return list[0] == null;
   }

   public LineItem[] getList() {
      return list;
   }

   LineItem getLineItem(int index) {
      return list[index];
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

   // Add Items method to Cart
   public String addItem(LineItem lineItem) {
      for (int i = 0; i < list.length; i++) {
         if (list[i] == null) {
            list[i] = lineItem;
            calcTotalPrice();
            return null;
         } else if (list[i].getCake().getId() == lineItem.getCake().getId()) {
            list[i].setAmount(list[i].getAmount() + lineItem.getAmount());
            calcTotalPrice();
            return null;
         }
      }

      return "Kan ikke tilføje flere slags kager til kurv";
   }

   // Delete Existing item from Cart.
   public void deleteItem(int indexLine) {
      for (int i = 0; i < list.length; i++) {
         if (list[i] != null && i == indexLine) {
            list[i] = null;
            rearrangeList();
            calcTotalPrice();
            return;
         }
      }
   }

   // Rearrange Method for Cart.
   private void rearrangeList() {
      for (int i = 0; i < list.length; i++) {
         if (list[i] == null) {
            if (i + 1 < list.length && list[i + 1] != null) {
               list[i] = list[i + 1];
               list[i + 1] = null;
            }
         }
      }
   }

   // Calculates Carts Total Price.
   private void calcTotalPrice() {
      totalPrice = 0;

      for (LineItem aList : list) {
         if (aList != null) {
            totalPrice += aList.getPrice();
         }
      }
   }

   // ClearCart Method.
   public void clearCart() {
      for (int i = 0; i < list.length; i++) {
         list[i] = null;
      }

      totalPrice = 0;
   }
}