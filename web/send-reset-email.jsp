<%--
  Created by IntelliJ IDEA.
  User: nitro
  Date: 5/20/17
  Time: 7:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nulstil password</title>
    <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>
<body>

    <h1>Nulstil password</h1>

    <form action="/SendResetEmail" method="post">
        E-mail: <input type="text" name="email" width="30"/>
        <input type="submit" value="Nulstil password"/>
    </form>
    <p style="color: green;">${message}</p>
    <p style="color: red;">${errorMessage}</p>

    <a href="/login.jsp">Gå tilbage</a>

</body>
</html>
