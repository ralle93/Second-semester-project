<%@ page import="applayer.Cart" %>
<%@ page import="applayer.LineItem" %><%--
  Created by IntelliJ IDEA.
  User: nitro
  Date: 5/23/17
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bestil kager</title>
    <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>
<body>

    <h1>Bestil kager</h1><br>

    <%
        Cart cart = (Cart)request.getAttribute("cart");

        for (LineItem item : cart.getList()) {
           if (item != null) {
               out.print("Navn: " + item.getCake().getName() + "<br>");
               out.print("Pris per styk: " + item.getCake().getPrice() + "<br>");
               out.print("Antal: " + item.getAmount() + "<br>");
               out.print("Pris: " + item.getPrice() + "<br>");
               out.print("<br>");
           }
        }

        out.print("Pris ialt: " + cart.getTotalPrice() + "<br><br>");
    %>

    <form action="/OrderCakes" method="post">
        Adresse: <br><input type="text" name="address" width="30"/><br>
        Kommentarer til bestilling: <br><textarea name="notes" rows="5" cols="50"></textarea><br>
        Leverings dato: <br><input type="date" name="deliveryDate"/><br>
        <input type="hidden" name="action" value="order"/>
        <input type="submit" value="Bestil kager"/>
    </form>
    <p style="color: red">${errorMessage}</p>

</body>
</html>
