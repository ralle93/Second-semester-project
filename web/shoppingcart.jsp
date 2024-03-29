<%@ page import="applayer.LineItem" %>
<%@ page import="applayer.Cart" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: tommytroest
  Date: 19/05/2017
  Time: 06.39
  To change this template use File | Settings | File Templates.
--%>

<!-- Made by Tommy Troest -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Indkøbskurv</title>
        <link rel="stylesheet" type="text/css" href="css/shoppingcart-css.css">
        <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">

    </head>

    <body>
        <div class="shopping-cart">
            <h1>Indkøbskurv</h1>
            <label style="color: red;">${errorMessage}</label>

            <%
                Cart cart = (Cart)request.getAttribute("cart");
                int totalPrice = 0;
                if (cart != null) {
                    for (int i = 0; i < cart.getList().length; i++ ) {
                       if (cart.getList()[i] != null) {
                           out.print("<fieldset id=\"lineitem-fieldsset\">");
                           out.print("<label>Navn: " + cart.getList()[i].getCake().getName() + "</label><br>");
                           out.print("<label>Pris per styk: " + cart.getList()[i].getCake().getPrice() + "</label><br>");
                           out.print("<label>Antal: " + cart.getList()[i].getAmount() + "</label><br>");
                           out.print("<label>Pris: " + cart.getList()[i].getPrice() + "</label><br>");
                           out.print("<form action=\"/ShoppingCart\" method=\"post\">" +
                                   "<input type=\"hidden\" name=\"action\" value=\"delete\"/>" +
                                   "<input type=\"hidden\" name=\"index\" value=\"" + i + "\"/>" +
                                   "<input type=\"submit\" value=\"Fjern\" button id=\"delete-button\"/>" +
                                   "</form>");
                           out.print("</fieldset>");
                       }
                    }
                    totalPrice += cart.getTotalPrice();
                    out.print("<fieldset>");
                    out.print("<label>Total Pris: " + totalPrice + "</label>");
                    out.print("</fieldset>");
                    out.print("<form action=\"/ShoppingCart\" method=\"post\">" +
                            "<input type=\"hidden\" name=\"action\" value=\"clear\"/>" +
                            "<input type=\"submit\" value=\"Tøm indkøbskurv\" id=\"clear-button\"/></form>");
                    out.print("<form method=\"get\" action=\"/OrderCakes\" target=\"index-iframe\">\n" +
                            "    <button type=\"submit\">Bestil kager</button>\n" +
                            "</form>");
                };
            %>
        </div>
    </body>
</html>