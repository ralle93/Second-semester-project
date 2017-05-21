package servlets;

import applayer.RNGString;
import applayer.SendGmail;
import applayer.User;
import datalayer.Data;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SendResetEmail")
public class SendResetEmail extends HttpServlet {

   Data d = new Data();

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      // Get email from form
      String email = request.getParameter("email");

      // Find out if a user has that email
      User user = d.getUserFromEmail(email);

      if (user == null) {
         // No user has that email
         request.setAttribute("errorMessage", "No user registered with that e-mail!");

      } else if (!user.isActivated()) {
         // Tell user to activate user first
         request.setAttribute("errorMessage", "User is not activated");

      } else { // User exist, send email to reset password
         // Send reset password email
         RNGString rng = new RNGString("reset");
         String link = rng.getLink();
         String message = "\n Click this link to reset your password on www.pernilleslaekkerier.dk";
         SendGmail.sendToCustomer(email,"Reset password mail", link + message);

         //link user and activation key in database
         String key = rng.getKey();
         d.insertActivationLink(key, user.getId());

         request.setAttribute("message", "E-mail sent with link to reset password!");
      }

      request.getRequestDispatcher("/send-reset-email.jsp").forward(request, response);
   }
}