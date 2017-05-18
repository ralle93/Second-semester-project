
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>

    <h1>Edit User:</h1>

    <form action="/CreateUser" method="post">
        E-mail: <input type="text" name="email" width="30" value="${email}"/>
        Name: <input type="text" name="name" width="30" value="${name}"/>
        Phone Number: <input type="text" name="number" width="30" value="${number}"/>
        <input type="hidden" name="action" value="edit"></input>
        <input type="submit" value="Edit user"/>
    </form>

    <p style="color: red;">${errorMessage}</p>

    <br><br><br>

    <h1>Change password:</h1>

    <form action="/CreateUser" method="post">
        Current password: <input type="password" name="currentPass" width="30"/>
        New password: <input type="password" name="newPass" width="30"/>
        <input type="hidden" name="action" value="editPass"></input>
        <input type="submit" value="Change password"/>
    </form>

    <p style="color: red;">${passError}</p>

    <a href="gallery.jsp">Cancel</a>

</body>
</html>
