<%--
  Created by IntelliJ IDEA.
  User: tommytroest
  Date: 15/05/2017
  Time: 11.30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pernille's Kager</title>
    <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<body>
<div class="top-menu-bar">
    <a href="index.jsp">Start</a>
    <a href="cakes.jsp">Kager</a>
    <a href="custom-cakes.jsp">Byg Selv Kage</a>
    <a href="about.jsp">Om Pernille's Kager</a>
</div>

<div class="products-table">
    <div class="product">
        <img src="images/sample_image1.jpg" id="product-image-1" class="product-image">
        <div id="product-description-1">
            <h1>Navn</h1>
            <p>Beskrivelse</p>
            <button id="add-button">Tilføj</button>
        </div>
    </div>
    <div class="product">
        <img src="images/sample_image2.jpg" id="product-image-2" class="product-image">
        <div id="product-description-2">
            <h1>Navn</h1>
            <p>Beskrivelse</p>
            <button id="add-button">Tilføj</button>
        </div>
    </div>
</div>

<script>
    $("#product-image-1").click(function() {
     $("#product-description-1").toggle();
    });
</script>

<script>
    $("#product-image-2").click(function() {
        $("#product-description-2").toggle();
    });
</script>
</body>

<footer>
    <div class="bottom-bar">
        <a href="https://www.facebook.com/PernillesLaekkerier/">facebook</a>
        <i class="fa fa-facebook-official fa-2x" aria-hidden="true"></i>
        <a href="#mail">Email</a>
        <i class="fa fa-envelope fa-2x" aria-hidden="true"></i>
    </div>
</footer>
</html>
