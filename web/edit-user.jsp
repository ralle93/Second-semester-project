
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rediger bruger oplysninger</title>
    <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>
<body>

    <h1>Rediger bruger oplysninger:</h1>

    <form action="/CreateUser" method="post">
        E-mail: <input type="text" name="email" width="30" value="${email}"/>
        Navn: <input type="text" name="name" width="30" value="${name}"/>
        Telefon nr: <input type="text" name="number" width="30" value="${number}"/>
        <input type="hidden" name="action" value="edit"></input>
        <input type="submit" value="Rediger bruger oplysninger"/>
    </form>

    <p style="color: red;">${errorMessage}</p>

    <br><br><br>

    <h1>Skift password:</h1>

    <form action="/CreateUser" method="post">
        Nuværende password: <input type="password" name="currentPass" width="30"/>
        Nyt password: <input type="password" name="newPass" width="30"/>
        <input type="hidden" name="action" value="editPass"></input>
        <input type="submit" value="Skift password"/>
    </form>

    <p style="color: red;">${passError}</p>

    <a href="gallery.jsp">Annuller</a>

</body>
</html>
