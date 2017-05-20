<%@ page import="java.util.ArrayList" %>
<%@ page import="applayer.Cake" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cakes</title>
    <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>
<body>

    <h1>Kager:</h1>

    <div class="products-table">
    <%
        ArrayList<Cake> cakes = (ArrayList<Cake>)request.getAttribute("cakes");

        for (Cake c : cakes) {
            out.print("<div class=\"product\">");
            out.print("<h3>Navn: " + c.getName() + "</h3>");
            out.print("<img src=\"images/cakes/cake_" + c.getId() + ".jpg\" class=\"product-image\"><br>");
            out.print("Beskrivelse: " + c.getDescription() + "<br>");
            out.print("Pris: " + c.getPrice() + "<br>");
            out.print("<form action=\"/ShoppingCart\" method=\"post\">" +
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
