<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
  <title>Pernille's Kager</title>
  <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>

<body>
<div class="top-menu-bar">
  <a href="gallery.jsp" target="iframe">Start</a>
  <a href="cakes.jsp" target="iframe">Kager</a>
  <a href="custom-cakes.jsp" target="iframe">Byg Selv Kage</a>
  <a href="about.jsp" target="iframe">Om Pernille's Kager</a>
  <div id="dropdown">
    <a id="dropbtn">Profil</a>
    <div id="dropdown-content">
      <a href="login.jsp">Login</a>
      <a href="create-user.jsp">Opret ny Bruger</a>
    </div>
  </div>
</div>

<iframe class="main-iframe" id="iframe" width="100%" height="600px" seamless src="gallery.jsp"></iframe>

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
