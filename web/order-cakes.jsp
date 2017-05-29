<%--
    Made by Søren Diamant Skovgaard
--%>
<%@ page import="applayer.Cart" %>
<%@ page import="applayer.LineItem" %>
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
        <fieldset>

            <table>
                <tr>
                    <td><label for="address">Adresse:</label></td>
                    <td><input type="text" name="address" id="address" rows="3" cols="50" placeholder="Indæst adresse her."></td>
                </tr>
                <tr>
                    <td><label for="note">Kommentarer til bestilling:</label></td>
                    <td><input type="text" name="notes" rows="3" cols="50" id="note" placeholder="Indsæt evt. kommentarer her."></td>
                </tr>
                <tr>
                    <td><label for="date">Leverings dato:</label></td>
                    <td><input type="date" name="deliveryDate" id="date" placeholder="YYYY-MM-DD"/></td>
                </tr>
            </table>
            <br>

            <input type="hidden" name="action" value="order"/>
            <input type="submit" value="Bestil kager"/>
        </fieldset>
    </form>
    <p style="color: red">${errorMessage}</p>

</body>
</html>
