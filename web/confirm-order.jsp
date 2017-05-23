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

    <%
        Order order = (Order)request.getAttribute("order");

        for (LineItem item : order.getList()) {
            if (item != null) {
                out.print("Navn: " + item.getCake().getName() + "<br>");
                out.print("Pris per styk: " + item.getCake().getPrice() + "<br>");
                out.print("Antal: " + item.getAmount() + "<br>");
                out.print("Pris: " + item.getPrice() + "<br>");
                out.print("<br>");
            }
        }

        out.print("Pris ialt: " + order.getTotal() + "<br><br>");
        out.print("Leverings addresse:<br>" + order.getAddress() + "<br><br>");
        out.print("Leverings dato:<br>" + order.getDeliveryDate() + "<br><br>");
        out.print("Kommentarer til levering:<br>" + order.getNote() + "<br><br>");
    %>

    <form action="/OrderCakes" method="post">
        Jeg accepterer <a href="http://www.google.dk">handelsbetingelserne <br></a><input type="checkbox" name="terms"/>
        <input type="hidden" name="action" value="confirmOrder">
        <input type="submit" value="Bekræft bestilling"/>
    </form>
    <p style="color: red">${errorMessage}</p>

</body>
</html>
