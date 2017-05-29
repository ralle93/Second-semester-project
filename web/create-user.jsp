<%--
    Made by Søren Diamant Skovgaard
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Opret bruger</title>
    <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>
<body>

    <h1>Opret bruger</h1>

    <form action="/CreateUser" method="post">
        E-mail: <input type="text" name="email" width="30"/>
        Password: <input type="password" name="password" width="30"/>
        Navn: <input type="text" name="name" width="30"/>
        Telefon nr: <input type="text" name="number" width="30"/>
        <input type="hidden" name="action" value="create"></input>
        <input type="submit" value="Register"/>
    </form>

    <a href="dropdown.jsp">Gå tilbage</a>
    <p style="color: red;">${errorMessage}</p>

</body>
</html>
