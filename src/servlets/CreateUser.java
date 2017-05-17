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

      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String name = request.getParameter("name");
      String phoneNumber = request.getParameter("number");

      if (!VerifyData.isValidEmail(email)) {
         request.setAttribute("errorMessage", "Invalid email");
         request.getRequestDispatcher("/create-user.jsp").forward(request, response);
      } else if (!VerifyData.isValidPass(password)) {
         request.setAttribute("errorMessage", "Password must be atleast 6 characters long");
         request.getRequestDispatcher("/create-user.jsp").forward(request, response);
      } else if (!VerifyData.isValidName(name)) {
         request.setAttribute("errorMessage", "Please enter both a first and last name");
         request.getRequestDispatcher("/create-user.jsp").forward(request, response);
      } else if (!VerifyData.isValidNumber(phoneNumber)) {
         request.setAttribute("errorMessage", "Please enter a phone number");
         request.getRequestDispatcher("/create-user.jsp").forward(request, response);
      } else {
         User user = new User(email, password, name, phoneNumber);
         d.createUser(user);
         request.getRequestDispatcher("/index.jsp").forward(request, response);
      }
   }
}
