package servlets;

import datalayer.Data;
import datalayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FillEditForm")
public class FillEditForm extends HttpServlet {

   Data d = new Data();

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Get user data from session db table
      String session = request.getSession().getId();
      User user = d.fetchUserFromSession(session);

      // Get data ready to be send to form
      request.setAttribute("email", user.getEmail());
      request.setAttribute("password", user.getPassword());
      request.setAttribute("name", user.getName());
      request.setAttribute("number", user.getPhoneNumber());

      // Forward data to edit user page
      request.getRequestDispatcher("/edit-user.jsp").forward(request, response);
   }
}
