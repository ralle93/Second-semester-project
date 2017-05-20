package servlets;

import applayer.Cake;
import datalayer.Data;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetCakes")
public class GetCakes extends HttpServlet {

   Data d = new Data();

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      ArrayList<Cake> cakes = d.getCakes();

      request.setAttribute("cakes", cakes);
      request.getRequestDispatcher("cakes.jsp").forward(request, response);
   }
}
