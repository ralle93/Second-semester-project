<%--
    Made by Søren Diamant Skovgaard & Tommy Troest
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dropdown</title>
    <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>
<body>
    <%
        // Refresh cart if necessary
        if (request.getAttribute("refreshCart") != null) {
            out.print("<script>window.open('/ShoppingCart', 'cart-iframe');</script>");
        }
    %>

    ${loggedInUser}<br>
    <a href="FillEditForm" target="index-iframe" id="button-anchor">Rediger bruger oplysninger</a><br>
    <a href="#" onclick="window.open('/Logout', 'dropdown-iframe');window.open('gallery.jsp', 'index-iframe');window.open('/ShoppingCart', 'cart-iframe');" id="button-anchor">Log ud</a>
</body>
</html>
