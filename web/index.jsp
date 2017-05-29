<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
  <title>Pernille's Lækkerier</title>
  <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>

<body>
<div class="top-menu-bar">
  <div id="navigation-menu">
    <a href="gallery.jsp" target="index-iframe" id="logo"><img src="images/logo.png" alt="logo" id="logo-image"></a>
    <a href="/GetCakes" target="index-iframe">Kager</a>
    <a href="about.jsp" target="index-iframe">Om Pernille's Lækkerier</a>

  <div id="dropdown">
    <a id="dropbtn">Profil</a>
    <div id="dropdown-content">
      <iframe width="250px" height="350px" name="dropdown-iframe" id="dropdown-iframe" src="/Login" scrolling="no"></iframe>
    </div>
  </div>
    <div id="dropdown">
        <a id="dropbtn">Indkøbskurv</a>
        <div id="dropdown-content">
            <iframe width="250px" height="350px" name="cart-iframe" id="cart-iframe" src="/ShoppingCart" scrolling="yes"></iframe>
        </div>
    </div>
  </div>
</div>

<iframe class="main-iframe" name="index-iframe" id="iframe" width="100%" height="75%" src="gallery.jsp"></iframe>

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
