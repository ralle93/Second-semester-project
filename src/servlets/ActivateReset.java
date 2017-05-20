package servlets;

import datalayer.Data;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ActivateReset")
public class ActivateReset extends HttpServlet {

   Data d = new Data();

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      // Check if the request is to activate user or to reset password
      if (request.getParameter("activate") != null) {
         activateUser(request, response);

      } else if (request.getParameter("reset") != null) {
         resetPassword(request, response);
      } else {
         // If both values are null the servlet has nothing to do and forwards to front page
         request.getRequestDispatcher("/index.jsp").forward(request, response);
      }
   }

   private void activateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Activation key from get request
      String activationKey = request.getParameter("activate");

      // Get user id from key
      int userID = d.getUserIdFromKey(activationKey);

      if (userID == -1) {
         // Invalid link
         request.setAttribute("errorMessage", "Invalid activation link!");

      } else {
         // Activating user
         d.activateUser(userID);
         request.setAttribute("message", "User activated!");
      }

      // Forward to activate user results page
      request.getRequestDispatcher("/activate-user.jsp").forward(request, response);
   }

   private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String resetKey = request.getParameter("reset");

   }
}
