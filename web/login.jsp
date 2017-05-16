<%--
  Test class for login function
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login test</title>
</head>
<body>

<h1>Login test:</h1>

<form action="/Login" method="post">
    E-mail: <input type="text" name="email" width="30"/>
    Password: <input type="password" name="password" width="30"/>
    <input type="submit" value="Login"/>
</form>

<p style="color: red;">${errorMessage}</p>

</body>
</html>
