package servlets;

import security.Hash;
import security.RNGString;
import security.SendGmail;
import applayer.VerifyData;
import datalayer.Data;
import applayer.User;

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

      String action = request.getParameter("action");

      if (action.equals("editPass")) {
         editUserPass(request, response);
      } else {
         createEditUser(request, response);
      }
   }

   private void editUserPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Get parameters from edit pass form
      String currentPass = request.getParameter("currentPass");
      String newPass = request.getParameter("newPass");

      // Get session and user info
      String session = request.getSession().getId();
      User user = d.fetchUserFromSession(session);

      // Check if current password is correct
      if (currentPass.equals(user.getPassword())) {
         // Check if new password is valid
         if (VerifyData.isValidPass(newPass)) {
            // Change password
            user.setPassword(newPass);
            d.editUser(user);

            request.getRequestDispatcher("/gallery.jsp").forward(request, response);

         } else { // Return user with new pass error
            request.setAttribute("passError", "Nyt password skal være på mindst 6 tegn!");
            request.getRequestDispatcher("/FillEditForm").forward(request, response);
         }

      } else { // Return user with current pass error
         request.setAttribute("passError", "Forkert nuværende password indtastet!");
         request.getRequestDispatcher("/FillEditForm").forward(request, response);
      }
   }

   private void createEditUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Get parameters from create/edit user form
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String name = request.getParameter("name");
      String phoneNumber = request.getParameter("number");
      String action = request.getParameter("action");

      // Check if email is allready registered
      User userEmailCheck = d.getUserFromEmail(email);

      // Check for errors
      if (userEmailCheck != null) {
         // Find out what user is logged in
         String session = request.getSession().getId();
         User user = d.fetchUserFromSession(session);

         if (user == null || user.getId() != userEmailCheck.getId()) {
            request.setAttribute("errorMessage", "E-mail er allerede registreret!");
         }
      }
      if (!VerifyData.isValidEmail(email)) {
         request.setAttribute("errorMessage", "Ugyldig e-mail!");
      } else if (action.equals("create") && !VerifyData.isValidPass(password)) {
         request.setAttribute("errorMessage", "Password skal være på mindst 6 tegn!");
      } else if (!VerifyData.isValidName(name)) {
         request.setAttribute("errorMessage", "Venligst indtast både et for- og efternavn!");
      } else if (!VerifyData.isValidNumber(phoneNumber)) {
         request.setAttribute("errorMessage", "Venligst indtast et telefon nummer!");
      }

      // Return to create/edit user page with error message
      if (request.getAttribute("errorMessage") != null) {
         // Return to create user page
         if (action.equals("create")) {
            request.getRequestDispatcher("/create-user.jsp").forward(request, response);

         } else { // Return to edit user page
            request.getRequestDispatcher("/FillEditForm").forward(request, response);
         }

      } else { // Create/edit user if no errors

         // Create user
         if (action.equals("create")) {
            // Create user in database and retrieve id assigned
            User user = new User(email, Hash.hashPW(password), name, phoneNumber);
            int userID = d.createUser(user);

            //send activation email
            RNGString rng = new RNGString("activate");
            String link = rng.getLink();
            String message = "\n Tryk på dette link for at aktivere bruger på www.pernilleslaekkerier.dk";
            SendGmail.sendToCustomer(user.getEmail(),"Aktiverings Mail", link + message);

            //link user and activation key in database
            String key = rng.getKey();
            d.insertActivationLink(key, userID);

            // Forward user to default dropdown page
            request.setAttribute("message", "Bruger er oprettet, tjek e-mail for aktiveringslink!");
            request.getRequestDispatcher("/dropdown.jsp").forward(request, response);

         } else { // Edit user
            // Find out what user is logged in
            String session = request.getSession().getId();
            User user = d.fetchUserFromSession(session);

            // Construct new user object and update the database
            user = new User(user.getId(), email, Hash.hashPW(user.getPassword()), name, phoneNumber);
            d.editUser(user);

            // Forward user to main page
            request.getRequestDispatcher("/gallery.jsp").forward(request, response);
         }
      }
   }
}
