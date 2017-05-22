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
                    for (LineItem item : cart.getList()) {
                       out.print("<p>" + item.toString() + "</p>");
                    }
                } else {
                    out.print("<p>Indkøbskurven er tom.</p>");
                };
            %>
            <button id="login-button"><a href="/dropdown-loggedin.jsp" id="button-anchor">Back</a></button>
        </div>
    </body>
</html>