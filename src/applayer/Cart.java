package applayer;

import javax.servlet.http.HttpSession;
import javax.sound.sampled.Line;
import java.util.HashMap;

/**
 * Created by tommytroest on 19/05/2017.
 */
public class Cart {

   private LineItem[] list = new LineItem[RecInfo.getMaxOrderSize()];
   private int totalPrice;


   public Cart(int totalPrice) {
      this.totalPrice = totalPrice;
   }

   public void addItem() {

   }

   public void deleteItem() {

   }

   public void clearCart() {

   }

   public LineItem[] getList() {
      return list;
   }
}
