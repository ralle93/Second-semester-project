<%--
    Made by Søren Diamant Skovgaard & Tommy Troest
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login test</title>
    <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>
<body>

    <h1 id="login-label">Login</h1>

    <form action="/Login" method="post" id="login-form">
        <p id="login-label">E-mail:</p><input type="text" name="email" width="30" id="form-textfield"/>
        <p id="login-label">Password:</p><input type="password" name="password" width="30" id="form-textfield"/>

        <input type="submit" value="Login" id="login-button"/>

    </form>
    <p style="color: red;">${errorMessage}</p>

    <div id="buttons-div">
        <button id="login-button"><a href="/send-reset-email.jsp" id="button-anchor">Glemt kodeord</a></button>
        <button id="login-button"><a href="/dropdown.jsp" id="button-anchor">Gå tilbage</a></button>
    </div>

</body>
</html>
