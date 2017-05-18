
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>

    <h1>Edit User:</h1>

    <form action="/CreateUser" method="post">
        E-mail: <input type="text" name="email" width="30" value="${email}"/>
        Password: <input type="password" name="password" width="30" value="${password}"/>
        Name: <input type="text" name="name" width="30" value="${name}"/>
        Phone Number: <input type="text" name="number" width="30" value="${number}"/>
        <input type="hidden" name="action" value="edit"></input>
        <input type="submit" value="Edit"/>
    </form>

    <a href="gallery.jsp">Cancel</a>
    <p style="color: red;">${errorMessage}</p>

</body>
</html>
