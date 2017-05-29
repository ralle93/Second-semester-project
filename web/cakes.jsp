<%@ page import="java.util.ArrayList" %>
<%@ page import="applayer.Cake" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Kager</title>
    <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>
<body>

    <h1>Kager</h1>
    <label style="color: red;">${errorMessage}</label>

    <div class="products-table">
    <%
        // Refresh cart if necessary
        if (request.getAttribute("refreshCart") != null) {
            out.print("<script>window.open('/ShoppingCart', 'cart-iframe');</script>");
        }

        // Load cakes
        ArrayList<Cake> cakes = (ArrayList<Cake>)request.getAttribute("cakes");

        for (Cake c : cakes) {
            out.print("<div class=\"product\">");
            out.print("<h3>Navn: " + c.getName() + "</h3>");
            out.print("<img src=\"images/cakes/cake_" + c.getId() + ".jpg\" class=\"product-image\"><br>");
            out.print("<p id=\"cake-description\">Beskrivelse: " + c.getDescription() + "</p>");
            out.print("Pris: " + c.getPrice() + "<br>");
            out.print("<form action=\"/ShoppingCart\" method=\"post\">" +
                    "<input type=\"hidden\" name=\"action\" value=\"add\"/>" +
                    "<input type=\"hidden\" name=\"cakeID\" value=\"" + c.getId() + "\"/>" +
                    "Antal: <input type=\"number\" name=\"amount\" value=\"1\" width=\"10\"/><br>" +
                    "<input type=\"submit\" value=\"Tilføj til kurv\" button id=\"add-button\"/>" +
                    "</form>");
            out.print("</div>");
        }
    %>
    </div>
</body>
</html>
