package servlets;

import applayer.Cake;
import applayer.Cart;
import applayer.LineItem;
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

         String action = request.getParameter("action");
         int cakeID = Integer.parseInt(request.getParameter("cakeID"));
         int amount = Integer.parseInt(request.getParameter("amount"));

         if (action.equals("add")) {
            addItem(cartIndex, cakeID, amount);
         }

         request.getRequestDispatcher("/shoppingcart.jsp").forward();
      }
   }

   private void addItem (int cartIndex, int cakeID, int amount) {
      Cake cake = d.getCakeFromID(cakeID);
      LineItem lineItem = new LineItem(cake, amount);
      carts.get(cartIndex).addItem(lineItem);
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
