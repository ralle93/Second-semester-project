package servlets;

import applayer.Cart;
import applayer.User;
import applayer.VerifyData;
import datalayer.Data;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet(name = "OrderCakes")
public class OrderCakes extends HttpServlet {

   Data d = new Data();

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Get user that is logged in
      String session = request.getSession().getId();
      User user = d.fetchUserFromSession(session);

      // Get input from form
      String address = request.getParameter("address");
      String notes = request.getParameter("notes");
      LocalDate deliveryDate;

      try {
         deliveryDate = LocalDate.parse(request.getParameter("deliveryDate"));
      } catch (DateTimeParseException ex) {
         deliveryDate = LocalDate.of(1, 1, 1);
      }

      // Check for errors
      String errorMessage = null;
      if (!VerifyData.isValidAddress(address)) {
         errorMessage = "Venligst indtast en addresse!";
      } else if (!VerifyData.isValidDeliveryDate(deliveryDate)) {
         errorMessage = "Venligst vælg en dato der er minimum en uge fra nuværende dato!";
      }

      if (errorMessage != null) {
         request.setAttribute("errorMessage", errorMessage);
         returnWithCart(user, request, response);
      } else {
         // TODO: Confirm order and add to database
      }
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Get user that is logged in
      String session = request.getSession().getId();
      User user = d.fetchUserFromSession(session);

      returnWithCart(user, request, response);
   }

   private void returnWithCart(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Get the users shopping cart from the ShoppingCart servlet
      Cart cart = ShoppingCart.getCart(user);

      // Check if cart is empty before forwarding to the order page
      if (cart.isEmpty()) {
         request.setAttribute("errorMessage", "Indkøbskurv er tom, intet at bestille!");
         request.getRequestDispatcher("/GetCakes").forward(request, response);
      } else {
         request.setAttribute("cart", cart);
         request.getRequestDispatcher("/order-cakes.jsp").forward(request, response);
      }
   }
}
