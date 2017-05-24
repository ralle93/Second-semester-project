package servlets;

import applayer.*;
import datalayer.Data;
import security.SendGmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

@WebServlet(name = "OrderCakes")
public class OrderCakes extends HttpServlet {

   private Data d = new Data();
   private ArrayList<Order> orders = new ArrayList<>();

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Get user that is logged in
      String session = request.getSession().getId();
      User user = d.fetchUserFromSession(session);

      String action = request.getParameter("action");
      if (action.equals("order")) {
         orderCakes(user, request, response);
      } else if (action.equals("confirmOrder")) {
         confirmCakeOrder(user, request, response);
      }
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Get user that is logged in
      String session = request.getSession().getId();
      User user = d.fetchUserFromSession(session);

      returnWithCart(user, request, response);
   }

   private void orderCakes(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

      // If error return to order page with error
      if (errorMessage != null) {
         request.setAttribute("errorMessage", errorMessage);
         returnWithCart(user, request, response);

      } else { // If no error, add order to arraylist and forward to confirm order page
         Cart cart = ShoppingCart.getCart(user);
         Order order = new Order(user.getId(), cart.getList(), address, notes, deliveryDate);
         int orderIndex = getOrderIndex(user);

         // Remove order from list if user allready has one
         if (orderIndex != -1) {
            orders.remove(orderIndex);
         }

         // Add new order to list
         orders.add(order);

         // Forward to confirm order page with order object
         request.setAttribute("order", order);
         request.getRequestDispatcher("/confirm-order.jsp").forward(request, response);
      }
   }

   private void confirmCakeOrder(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Order order = orders.get(getOrderIndex(user));

      // Tjek om kunden har bekræftet betingelserne
      if (request.getParameter("terms") == null) {
         // Forward with order and error message
         request.setAttribute("order", order);

         request.setAttribute("errorMessage", "Venligst bekræft handelsbetingelserne!");
         request.getRequestDispatcher("/confirm-order.jsp").forward(request, response);

      } else { // Complete order
         // Add order to database
         d.createOrder(order);

         // Create reciept
         ReceiptCreator rc = new ReceiptCreator(order, user);
         String pdfFilePath = rc.newReceipt();

         // Send email with receipt to customer
         String title = "Kvitering for bestilling hos Pernilles Lækkerier";
         String message = "Tak for din bestilling hos Pernilles Lækkerier.\n Vedhæftet er din kvitering.";
         SendGmail.sendWithAttach(user.getEmail(), title, message, pdfFilePath);

         // Remove order from orders array
         orders.remove(getOrderIndex(user));

         // Forward user to a page that confirms the order and clears the shopping cart
         ShoppingCart.clearCart(user);
         request.setAttribute("message", "Bestillingen er modtaget!<br>En kvittering er sendt til din e-mail.");
         request.getRequestDispatcher("/order-recieved.jsp").forward(request, response);
      }
   }

   private int getOrderIndex(User user) {
      for (int i = 0; i < orders.size(); i++) {
         if (orders.get(i).getUserID() == user.getId()) {
            return i;
         }
      }

      return -1;
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
