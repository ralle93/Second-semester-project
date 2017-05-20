package servlets;

import datalayer.Data;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Logout")
public class Logout extends HttpServlet {

   Data d = new Data();

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Get user session id
      String session = request.getSession().getId();

      // Delete session from database
      d.deleteSession(session);

      // Forward user to default dropdown page
      request.getRequestDispatcher("/dropdown.jsp").forward(request, response);
   }
}
