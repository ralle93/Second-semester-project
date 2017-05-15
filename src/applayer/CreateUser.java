package applayer;

import datalayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateUser")
public class CreateUser extends HttpServlet {
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String name = request.getParameter("name");
      String phoneNumber = request.getParameter("number");

      if (!isValidEmail(email)) {
         request.setAttribute("errorMessage", "Invalid email");
         request.getRequestDispatcher("/create-user.jsp").forward(request, response);
      } else if (!isValidPass(password)) {
         request.setAttribute("errorMessage", "Password must be atleast 6 characters long");
         request.getRequestDispatcher("/create-user.jsp").forward(request, response);
      } else if (!isValidName(name)) {
         request.setAttribute("errorMessage", "Please enter both a first and last name");
         request.getRequestDispatcher("/create-user.jsp").forward(request, response);
      } else if (!isValidNumber(phoneNumber)) {
         request.setAttribute("errorMessage", "Please enter a phone number");
         request.getRequestDispatcher("/create-user.jsp").forward(request, response);
      } else {
         User user = new User(email, password, name, phoneNumber);

         // TODO: Send user object to class that handles adding the user to the database
         System.out.println(user);
      }
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("y u do dis?");
   }

   // Method to determine if the user has entered a valid looking e-mail address
   private boolean isValidEmail(String email) {
      // Check that e-mail contains '@', '.' and don't have spaces
      if (email.contains("@") && email.contains(".") && !email.contains(" ")) {
         // Check that '@' isn't in the start or end of the e-mail
         if (email.charAt(0) != '@' && email.charAt(email.length() - 1) != '@') {
            // Check that '.' isn't in the start or end of the e-mail
            if (email.charAt(0) != '.' && email.charAt(email.length() - 1) != '.') {
               String str = email.replaceAll("@", "");

               // Check if there is more than one '@'
               if (str.length() == email.length() - 1) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   // Method to check if the user has entered a strong enough password
   private boolean isValidPass(String password) {
      if (password.length() < 6) {
         return false;
      }

      return true;
   }

   // Simple method to determine if the user has entered a valid looking name
   private boolean isValidName(String name) {
      // Check that the name is not so short that there can't be a first and last name
      if (name.length() >= 3) {
         // Check that the name contains a space and dosn't start or end with one
         if (name.contains(" ") && name.charAt(0) != ' ' && name.charAt(name.length() - 1) != ' ') {
            return true;
         }
      }

      return false;
   }

   // Method to check if the user entered a valid phone number
   private boolean isValidNumber(String phoneNumber) {
      return !phoneNumber.isEmpty();
   }
}
