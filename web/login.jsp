<%--
  Test class for login function
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
        <button href="/send-reset-email.jsp" id="login-button">Forgot password</button>
        <button id="login-button"> <a href="/dropdown.jsp" id="button-anchor">Back</button>
    </div>

</body>
</html>
