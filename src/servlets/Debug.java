package servlets;

import applayer.*;
import security.SendGmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "Debug")
public class Debug extends HttpServlet {
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      User user = new User(666, "soren.ds@gmail.com", "testpass", "lort", "lortnumber", true);
      Cake cake = new Cake(5, 55, "testcake", "test description");
      LineItem[] list = new LineItem[5];
      LineItem item = new LineItem(cake, 5);
      list[0] = item;

      Order order = new Order(666, list, "test address", "test note", LocalDate.now());
      // Create reciept
      ReceiptCreator rc = new ReceiptCreator(order, user);
      String pdfFilePath = rc.newReceipt();

      // Send email with receipt to customer
      String title = "Kvitering for bestilling hos Pernilles Lækkerier";
      String message = "Tak for din bestilling hos Pernilles Lækkerier.\n Vedhæftet er din kvitering.";
      SendGmail.sendWithAttach(user.getEmail(), title, message, pdfFilePath);
   }
}
