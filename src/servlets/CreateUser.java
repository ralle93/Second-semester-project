package servlets;

import applayer.VerifyData;
import datalayer.Data;
import datalayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateUser")
public class CreateUser extends HttpServlet {

   private Data d = new Data();

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      // Get parameters from create/edit user form
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String name = request.getParameter("name");
      String phoneNumber = request.getParameter("number");

      String action = request.getParameter("action");

      // Check for errors
      if (!VerifyData.isValidEmail(email)) {
         request.setAttribute("errorMessage", "Invalid email");
      } else if (!VerifyData.isValidPass(password)) {
         request.setAttribute("errorMessage", "Password must be atleast 6 characters long");
      } else if (!VerifyData.isValidName(name)) {
         request.setAttribute("errorMessage", "Please enter both a first and last name");
      } else if (!VerifyData.isValidNumber(phoneNumber)) {
         request.setAttribute("errorMessage", "Please enter a phone number");
      }

      // Return to create/edit user page with error message
      if (request.getAttribute("errorMessage") != null) {
         // Return to create user page
         if (action.equals("create")) {
            request.getRequestDispatcher("/create-user.jsp").forward(request, response);

         } else { // Return to edit user page
            request.getRequestDispatcher("/edit-user.jsp").forward(request, response);
         }

      } else { // Create/edit user if no errors

         // Create user
         if (action.equals("create")) {
            User user = new User(email, password, name, phoneNumber);
            d.createUser(user);

            request.getRequestDispatcher("/dropdown.jsp").forward(request, response);

         } else { // Edit user
            // Find out first what user is logged in
            String session = request.getSession().getId();
            int id = d.fetchUserFromSession(session).getId();

            // Construct new user object and update the database
            User user = new User(id, email, password, name, phoneNumber);
            d.editUser(user);

            request.getRequestDispatcher("/gallery.jsp").forward(request, response);
         }
      }
   }
}
