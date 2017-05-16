package servlets;

import datalayer.Data;
import datalayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login")
public class Login extends HttpServlet {

   Data d = new Data();
   User user = null;

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String email = request.getParameter("email");
      String password = request.getParameter("password");

   }
}
