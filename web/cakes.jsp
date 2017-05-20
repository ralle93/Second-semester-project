<%@ page import="java.util.ArrayList" %>
<%@ page import="applayer.Cake" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cakes</title>
</head>
<body>

    <h1>Kager:</h1><br>

    <%
        ArrayList<Cake> cakes = (ArrayList<Cake>)request.getAttribute("cakes");

        for (Cake c : cakes) {
            out.print("Navn: " + c.getName() + "<br>");
            out.print("<img src=\"images/cakes/cake_" + c.getId() + ".jpg\" width=\"300\"><br>");
            out.print("Beskrivelse: " + c.getDescription() + "<br>");
            out.print("Pris: " + c.getPrice() + "<br>");
            out.print("<form action=\"/ShoppingCart\" method=\"post\">\n" +
                    "<input type=\"hidden\" name=\"cakeID\" value=\"" + c.getId() + "\"/>" +
                    "Antal: <input type=\"number\" name=\"amount\" value=\"1\" width=\"10\"/>" +
                    "<input type=\"submit\" value=\"Køb kage\" />\n" +
                    "</form>");
        }
    %>

</body>
</html>
