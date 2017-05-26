package servlets;

import security.Hash;
import applayer.VerifyData;
import datalayer.Data;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ActivateReset")
public class ActivateReset extends HttpServlet {
   private Data d = new Data();

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
         request.setAttribute("errorMessage", "Ugyldigt aktiveringslink!");

      } else {
         // Activating user
         d.activateUser(userID);

         // Remove activation key from database
         d.removeActivationLink(userID);

         request.setAttribute("message", "Bruger er aktiveret!");
      }

      // Forward to activate user results page
      request.getRequestDispatcher("/activate-reset-user-result.jsp").forward(request, response);
   }

   // Forward to reset password page
   private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String resetKey = request.getParameter("reset");

      // Get user id from key
      int userID = d.getUserIdFromKey(resetKey);

      if (userID == -1) {
         // Invalid link
         request.setAttribute("errorMessage", "Ugyldigt password nulstillings link!");
         request.getRequestDispatcher("/activate-reset-user-result.jsp").forward(request, response);

      } else {
         // Forwarding to reset password page
         request.setAttribute("resetKey", resetKey);
         request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
      }
   }

   // Confirm new password
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String resetKey = request.getParameter("resetKey");
      request.setAttribute("resetKey", resetKey);

      // Get passwords from form
      String pass = request.getParameter("pass");
      String confirmPass = request.getParameter("confirmPass");

      // Check if passwords matches
      if (!pass.equals(confirmPass)) { // Passwords has to match error
         request.setAttribute("errorMessage", "Indtast det samme kodeord to gange!");
         request.getRequestDispatcher("/reset-password.jsp").forward(request, response);

      } else if (!VerifyData.isValidPass(pass)) {
         // Not a valid password
         request.setAttribute("errorMessage", "Password skal være mindst 6 tegn!");
         request.getRequestDispatcher("/reset-password.jsp").forward(request, response);

      } else { // Change password
         // Get user id from reset key
         int userID = d.getUserIdFromKey(resetKey);

         if (userID == -1) {
            // Invalid reset key (this should never happen)!!!
            request.setAttribute("errorMessage", "Ugyldig reset link, dette burde aldrig ske!!!");

         } else {
            // Hash the new password and change it
            pass = Hash.hashPW(pass);
            d.changeUserPass(userID, pass);

            // Remove activation key from database
            d.removeActivationLink(userID);

            request.setAttribute("message", "Password er ændret!");
         }

         request.getRequestDispatcher("/activate-reset-user-result.jsp").forward(request, response);
      }
   }
}