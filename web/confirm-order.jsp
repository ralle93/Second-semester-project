<%--
    Made by Søren Diamant Skovgaard
--%>

<%@ page import="applayer.Order" %>
<%@ page import="applayer.LineItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Bekræft bestilling</title>
    <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>
<body>

    <h1>Bekræft bestilling</h1><br>

    <table id="order-table">
        <tr>
            <th>Navn:</th>
            <th>Pris per styk:</th>
            <th>Antal:</th>
            <th>Pris:</th>
        </tr>
            <%
        Order order = (Order)request.getAttribute("order");

        for (LineItem item : order.getList()) {
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
        out.print("Pris ialt: " + order.getTotalPrice() + "<br><br>");
        out.print("Leverings addresse:<br>" + order.getAddress() + "<br><br>");
        out.print("Leverings dato:<br>" + order.getDeliveryDate() + "<br><br>");
        out.print("Kommentarer til levering:<br>" + order.getNote() + "<br><br>");
    %>

    <form action="/OrderCakes" method="post">
        Jeg accepterer <a href="/about.jsp">handelsbetingelserne </a><input type="checkbox" name="terms"/><br>
        <input type="hidden" name="action" value="confirmOrder">
        <input type="submit" value="Bekræft bestilling"/>
    </form>
    <p style="color: red">${errorMessage}</p>

</body>
</html>
