package servlets;

import applayer.Cart;
import applayer.User;
import datalayer.Data;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tommytroest on 21/05/2017.
 */
@WebServlet(name = "ShoppingCart")
public class ShoppingCart extends HttpServlet {

   Data d = new Data();
   ArrayList<Cart> carts = new ArrayList<>();

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String session = request.getSession().getId();
      User user = d.fetchUserFromSession(session);

      if (user != null) {
         int cartIndex = getCartIndex(user);
      }
   }

   private int getCartIndex(User user) {
      for (int i = 0; i < carts.size(); i++) {
         if (carts.get(i).getUserID() == user.getId()) {
            return i;
         }
      }

      Cart cart = new Cart(user.getId());
      carts.add(cart);
      return carts.size() - 1;
   }
}
