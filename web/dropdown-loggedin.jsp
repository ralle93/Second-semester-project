<%--
  Created by IntelliJ IDEA.
  User: nitro
  Date: 5/18/17
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dropdown</title>
    <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>
<body>
    ${loggedInUser}<br>
    <a href="FillEditForm" target="index-iframe" id="button-anchor">Edit user</a><br>
    <a href="#" onclick="window.open('/Logout', 'dropdown-iframe');window.open('gallery.jsp', 'index-iframe');window.open('/ShoppingCart', 'cart-iframe');" id="button-anchor">Log out</a>
</body>
</html>
