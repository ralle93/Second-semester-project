<%--
  Test class for creating a user
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create user test</title>
</head>
<body>

    <h1>Create user:</h1>

    <form action="/CreateUser" method="post">
        E-mail: <input type="text" name="email" width="30"/>
        Password: <input type="password" name="password" width="30"/>
        Name: <input type="text" name="name" width="30"/>
        Phone Number: <input type="text" name="number" width="30"/>
        <input type="hidden" name="action" value="create"></input>
        <input type="submit" value="Register"/>
    </form>

    <a href="dropdown.jsp">Back</a>
    <p style="color: red;">${errorMessage}</p>

</body>
</html>
