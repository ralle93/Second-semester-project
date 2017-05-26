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

    <table id="order-table">
        <tr>
            <th>Navn:</th>
            <th>Pris per styk:</th>
            <th>Antal:</th>
            <th>Pris:</th>
        </tr>
    <%
        Cart cart = (Cart)request.getAttribute("cart");

        for (LineItem item : cart.getList()) {
           if (item != null) {
               out.print("<tr>\n" +
                       "            <td>" + item.getCake().getName() + "</td>\n" +
                       "            <td>" + item.getCake().getPrice() + "</td>\n" +
                       "            <td>" + item.getAmount() + "</td>\n" +
                       "            <td>" + item.getPrice() + "</td>\n" +
                       "        </tr>");
           }
        }

        out.print("</table><br>");
        out.print("Pris ialt: " + cart.getTotalPrice() + "<br><br>");
    %>

    <form action="/OrderCakes" method="post">
        <label for="address">Adresse:</label>
        <textfield name="address" id="address" rows="3" cols="50"></textfield><br><br>
        Kommentarer til bestilling: <br><textarea name="notes" rows="3" cols="50"></textarea><br><br>
        Leverings dato: <br><input type="date" name="deliveryDate"/><br><br>
        <input type="hidden" name="action" value="order"/>
        <input type="submit" value="Bestil kager"/>
    </form>
    <p style="color: red">${errorMessage}</p>

</body>
</html>
