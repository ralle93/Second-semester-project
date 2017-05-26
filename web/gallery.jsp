<%--
  Created by IntelliJ IDEA.
  User: tommytroest
  Date: 17/05/2017
  Time: 13.33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pernille's Kager</title>
    <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>
<body>
<div class="center-area">

    <div class="slideshow-container">
        <div class="mySlides fade">
            <div class="numbertext">1 / 6</div>
            <img src="images/cakes/cake_1.jpg" style="width:100%">
            <div class="text">Sample #1 of an image in our cake gallery.</div>
        </div>

        <div class="mySlides fade">
            <div class="numbertext">2 / 6</div>
            <img src="images/cakes/cake_2.jpg" style="width:100%">
            <div class="text">Sample #2 of an image in our cake gallery.</div>
        </div>

        <div class="mySlides fade">
            <div class="numbertext">3 / 6</div>
            <img src="images/cakes/cake_3.jpg" style="width:100%">
            <div class="text">Sample #3 of an image in our cake gallery.</div>
        </div>

        <div class="mySlides fade">
            <div class="numbertext">4 / 6</div>
            <img src="images/cakes/cake_4.jpg" style="width:100%">
            <div class="text">Sample #4 of an image in our cake gallery.</div>
        </div>

        <div class="mySlides fade">
            <div class="numbertext">5 / 6</div>
            <img src="images/cakes/cake_5.jpg" style="width:100%">
            <div class="text">Sample #5 of an image in our cake gallery.</div>
        </div>

        <div class="mySlides fade">
            <div class="numbertext">6 / 6</div>
            <img src="images/cakes/cake_6.jpg" style="width:100%">
            <div class="text">Sample #6 of an image in our cake gallery.</div>
        </div>

        <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
        <a class="next" onclick="plusSlides(1)">&#10095;</a>
    </div>

    <br>

    <div style="text-align:center">
        <span class="dot" onclick="currentSlide(1)"></span>
        <span class="dot" onclick="currentSlide(2)"></span>
        <span class="dot" onclick="currentSlide(3)"></span>
        <span class="dot" onclick="currentSlide(4)"></span>
        <span class="dot" onclick="currentSlide(5)"></span>
        <span class="dot" onclick="currentSlide(6)"></span>
    </div>

    <script>
        var slideIndex = 1;
        showSlides(slideIndex);

        function plusSlides(n) {
            showSlides(slideIndex += n);
        }

        function currentSlide(n) {
            showSlides(slideIndex = n);
        }

        function showSlides(n) {
            var i;
            var slides = document.getElementsByClassName("mySlides");
            var dots = document.getElementsByClassName("dot");

            if (n > slides.length) {
                slideIndex = 1
            }

            if (n < 1) {
                slideIndex = slides.length
            }

            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }

            for (i = 0; i < dots.length; i++) {
                dots[i].className = dots[i].className.replace(" active", "");
            }

            slides[slideIndex-1].style.display = "block";
            dots[slideIndex-1].className += " active";
        }
    </script>
</div>
</body>
</html>
