package applayer;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by tommytroest on 19/05/2017.
 */
public class Cart {

   // Holding values in the ShoppingCart
   HashMap<String, Integer> shoppingCartItems;

   // Constructor
   public Cart() {
      shoppingCartItems = new HashMap<>();
   }

   // Get ShoppingCart Items
   public HashMap getShoppingCartItems() {
      return shoppingCartItems;
   }

   // Adding items to ShoppingCart
   public void addToCart(String itemID, int price) {
      shoppingCartItems.put(itemID, price);
   }

}
