<%--
    Made by Søren Diamant Skovgaard & Tommy Troest
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nulstil Password</title>
    <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>
<body>

    <h1>Reset password</h1>

    <form action="/ActivateReset" method="post">
        Nyt password: <input type="password" name="pass" width="30"/>
        Bekræft nyt password: <input type="password" name="confirmPass" width="30"/>
        <input type="hidden" name="resetKey" value="${resetKey}"/>
        <input type="submit" value="Nulstil password"/>
    </form>
    <p style="color: red;">${errorMessage}</p>

</body>
</html>
