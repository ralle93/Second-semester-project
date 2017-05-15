<%--
  Created by IntelliJ IDEA.
  User: nitro
  Date: 5/15/17
  Time: 10:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
  <title>Pernille's Kager</title>
  <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>

<body>
<div class="top-menu-bar">
  <a href="#home">Home</a>
  <a href="#cakes">Cakes</a>
  <a href="#custom-cake">Custom Cake</a>
  <a href="#about">About</a>
</div>

<div class="center-area">

  <div class="slideshow-container">
    <div class="mySlides fade">
      <div class="numbertext">1 / 2</div>
      <img src="images/sample_image1.jpg" style="width:100%">
      <div class="text">Sample #1 of an image in our cake gallery.</div>
    </div>

    <div class="mySlides fade">
      <div class="numbertext">2 / 2</div>
      <img src="images/sample_image2.jpg" style="width:100%">
      <div class="text">Sample #2 of an image in our cake gallery.</div>
    </div>

    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>
  </div>

  <br>

  <div style="text-align:center">
    <span class="dot" onclick="currentSlide(1)"></span>
    <span class="dot" onclick="currentSlide(2)"></span>
    <span class="dot" onclick="currentSlide(3)"></span>
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

  <div id="main-text-area">
    <h1 id="main-header">Sample Text</h1>
    <p id="main-paragraph">
      Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec nisl dui, volutpat ac vestibulum vel, iaculis eu augue. Vestibulum laoreet ultricies metus, vel hendrerit nisl dignissim eget. Donec porta semper purus, vitae laoreet sem interdum ut. Nunc gravida mi sem, a ornare diam rhoncus eu. Proin dapibus arcu sed enim tincidunt semper. Curabitur placerat tristique ex, vitae rhoncus enim malesuada venenatis. Curabitur id nunc porttitor, dictum magna finibus, dapibus nisi. Pellentesque eu augue a nisl euismod posuere ac in mauris. Aenean tempor et ante eu scelerisque.
    </p>
    <p id="main-paragraph">
      Suspendisse justo elit, vestibulum eget diam vel, gravida vestibulum nulla. Nam in pretium purus. Integer id porttitor nunc. Etiam vestibulum libero quis aliquet pellentesque. Integer fermentum nulla vitae nunc bibendum, quis eleifend ex interdum. Sed rutrum odio at nunc dignissim rutrum. Integer malesuada velit vitae orci pharetra, in tristique neque dignissim. Praesent et ante hendrerit, dignissim leo nec, sodales ipsum. Praesent fringilla, nisl ac cursus semper, libero magna rhoncus lectus, at commodo ipsum sem feugiat odio. Nunc ligula lectus, vehicula ac sagittis id, dignissim eu erat. Aenean sodales, felis sit amet dictum molestie, nulla arcu aliquam urna, ac laoreet ex sapien et metus. Mauris vitae tortor suscipit, ultrices mauris ut, laoreet lacus. Aliquam accumsan ante at posuere porta. Suspendisse interdum congue lobortis. Mauris non semper magna, eget maximus sapien.
    </p>
    <p id="main-paragraph">
      Etiam interdum finibus nibh eget fermentum. Duis ac posuere purus. Curabitur fringilla egestas est. Donec lorem ligula, sodales imperdiet dui a, fringilla dapibus leo. Mauris quis malesuada nulla. Suspendisse fermentum, diam quis viverra semper, odio turpis commodo diam, at finibus ante arcu vitae neque. Donec imperdiet in massa faucibus condimentum. Morbi dictum risus eu velit imperdiet viverra. Integer mollis fringilla lacus sed tincidunt. Etiam gravida in elit non sagittis.
    </p>
  </div>
</div>
</body>

<footer>
  <div class="bottom-bar">
    <a href="#facebook">facebook</a>
    <i class="fa fa-facebook-official fa-2x" aria-hidden="true"></i>
    <a href="#mail">Email</a>
    <i class="fa fa-envelope fa-2x" aria-hidden="true"></i>
  </div>
</footer>

</html>
