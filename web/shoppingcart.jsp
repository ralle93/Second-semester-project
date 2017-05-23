<%@ page import="applayer.LineItem" %>
<%@ page import="applayer.Cart" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: tommytroest
  Date: 19/05/2017
  Time: 06.39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Indkøbskurv</title>
        <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
    </head>

    <body>
        <div class="shopping-cart">
            <h1>Indkøbskurv</h1>
            <%
                Cart cart = (Cart)request.getAttribute("cart");
                if (cart != null) {
                    for (int i = 0; i < cart.getList().length; i++ ) {
                       if (cart.getList()[i] != null) {
                           out.print("<p>" + cart.getList()[i].getCake().getName() + "</p>");
                           out.print("<p>" + cart.getList()[i].getAmount() + "</p>");
                           out.print("<p>" + cart.getList()[i].getPrice() + "</p>");
                           out.print("<form action=\"/ShoppingCart\" method=\"post\">" +
                                   "<input type=\"hidden\" name=\"action\" value=\"delete\"/>" +
                                   "<input type=\"hidden\" name=\"index\" value=\"" + i + "\"/>" +
                                   "<input type=\"submit\" value=\"delete\" button id=\"delete-button\"/>" +
                                   "</form>");
                       }
                    }
                } else {
                    out.print("<p>Indkøbskurven er tom.</p>");
                };
            %>
        </div>
    </body>
</html>