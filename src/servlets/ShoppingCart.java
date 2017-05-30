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
 *  Made by Søren Diamant Skovgaard & Tommy Troest
 */

@WebServlet(name = "ShoppingCart")
public class ShoppingCart extends HttpServlet {
   private Data d = new Data();
   private static ArrayList<Cart> carts = new ArrayList<>();

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      updateCartDisplay(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String session = request.getSession().getId();
      User user = d.fetchUserFromSession(session);

      if (user != null) {
         int cartIndex = getCartIndex(user);

         String action = request.getParameter("action");

         switch (action) {
            case "add":
               int cakeID = Integer.parseInt(request.getParameter("cakeID"));
               int amount = Integer.parseInt(request.getParameter("amount"));

               String error = addItem(cartIndex, cakeID, amount);

               if (error != null) {
                  request.setAttribute("errorMessage", error);
               }

               request.setAttribute("refreshCart", true);
               request.getRequestDispatcher("/GetCakes").forward(request, response);

               break;
            case "delete":
               int index = Integer.parseInt(request.getParameter("index"));
               carts.get(cartIndex).deleteItem(index);

               updateCartDisplay(request, response);

               break;
            case "clear":
               carts.get(cartIndex).clearCart();
               updateCartDisplay(request, response);

               break;
         }
      } else {
         request.setAttribute("errorMessage", "Log venlist ind eller opret en bruger for at bestille kager!");
         request.getRequestDispatcher("/GetCakes").forward(request, response);
      }
   }

   private void updateCartDisplay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String session = request.getSession().getId();
      User user = d.fetchUserFromSession(session);

      if (user != null) {
         int cartIndex = getCartIndex(user);
         request.setAttribute("cart", carts.get(cartIndex));
      } else {
         request.setAttribute("errorMessage", "Log venlist ind eller opret en bruger for at bestille kager!");
      }

      request.getRequestDispatcher("/shoppingcart.jsp").forward(request, response);
   }

   private String addItem (int cartIndex, int cakeID, int amount) {
      if (amount > 0) {
         Cake cake = d.getCakeFromID(cakeID);
         LineItem lineItem = new LineItem(cake, amount);
         return carts.get(cartIndex).addItem(lineItem);
      } else {
         return "Vælg venligst et antal højere end 0!";
      }
   }

   private static int getCartIndex(User user) {
      for (int i = 0; i < carts.size(); i++) {
         if (carts.get(i).getUserID() == user.getId()) {
            return i;
         }
      }

      Cart cart = new Cart(user.getId());
      carts.add(cart);
      return carts.size() - 1;
   }

   static Cart getCart(User user) {
      return carts.get(getCartIndex(user));
   }

   static void clearCart(User user) {
      int index = getCartIndex(user);
      carts.get(index).clearCart();
   }
}