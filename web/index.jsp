<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
  <title>Pernille's Kager</title>
  <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>

<body>
<div class="top-menu-bar">
  <div id="logo">
    <img src="images/logo.png" alt="logo" id="logo-image">
  </div>
  <div id="navigation-menu">
    <a href="gallery.jsp" target="index-iframe">Start</a>
    <a href="cakes.jsp" target="index-iframe">Kager</a>
    <a href="custom-cakes.jsp" target="index-iframe">Byg Selv Kage</a>
    <a href="about.jsp" target="index-iframe">Om Pernille's Kager</a>
  </div>
  <div id="dropdown">
    <a id="dropbtn">Profil</a>
    <div id="dropdown-content">
      <iframe width="200px" height="200px" id="dropdown-iframe">
        <a href="login.jsp" target="dropdown-iframe">Login</a>
        <a href="create-user.jsp" target="dropdown-iframe">Opret ny Bruger</a>
      </iframe>
    </div>
  </div>
</div>

<iframe class="main-iframe" name="index-iframe" id="iframe" width="100%" height="600px" seamless src="gallery.jsp"></iframe>

</body>

<footer>
  <div class="bottom-bar">
    <a href="https://www.facebook.com/PernillesLaekkerier/">facebook</a>
    <i class="fa fa-facebook-official fa-2x" aria-hidden="true"></i>
    <a href="#mail">Email</a>
    <i class="fa fa-envelope fa-2x" aria-hidden="true"></i>
  </div>
</footer>

<script>
    $if (window.location == window.parent.location) {
        this.window.location = 'index.html';
    }
</script>

</html>
