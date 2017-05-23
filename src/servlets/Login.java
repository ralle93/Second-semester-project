package servlets;

import datalayer.Data;
import applayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login")
public class Login extends HttpServlet {

   private Data d = new Data();

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Check if user is allready logged in
      String session = request.getSession().getId();
      User user = d.fetchUserFromSession(session);

      if (user == null) {
         // Not logged in forward user to default dropdown page
         request.getRequestDispatcher("/dropdown.jsp").forward(request, response);

      } else {

         // Logged in, set loggedInUser value and forward to logged in dropdown page
         request.setAttribute("loggedInUser", user.getEmail());
         request.setAttribute("refreshCart", true);
         request.getRequestDispatcher("/dropdown-loggedin.jsp").forward(request, response);
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String email = request.getParameter("email");
      String password = request.getParameter("password");

      User user = d.logInCheck(email, password);

      if (user == null) {
         request.setAttribute("errorMessage", "Ugyldig e-mail eller password!");
         request.getRequestDispatcher("/login.jsp").forward(request, response);
      } else if (!user.isActivated()) {
         request.setAttribute("errorMessage", "Bruger er ikke aktiveret endnu. Tjek e-mail for aktiveringslink!");
         request.getRequestDispatcher("/login.jsp").forward(request, response);
      } else {
         String session = request.getSession().getId();
         d.httpSessionAdd(user, session);
         request.setAttribute("loggedInUser", user.getEmail());
         request.setAttribute("refreshCart", true);
         request.getRequestDispatcher("/dropdown-loggedin.jsp").forward(request, response);
      }
   }
}
