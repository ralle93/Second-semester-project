<%--
    Made by Søren Diamant Skovgaard & Tommy Troest
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rediger bruger oplysninger</title>
    <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
</head>
<body>

    <h1>Rediger bruger oplysninger:</h1>

    <form action="/CreateUser" method="post">
        <fieldset id="bios-field">
        <label for="email">E-mail:</label>
        <input type="text" name="email" id="email" width="30" value="${email}"/>
        <br>
        <label for="name">Navn:</label>
        <input type="text" name="name" id="name" width="30" value="${name}"/>
        <br>
        <label for="number">Telefon nr:</label>
        <input type="text" name="number" id="number" width="30" value="${number}"/>
        <br>
        <input type="hidden" name="action" value="edit">
        <input type="submit" value="Rediger bruger oplysninger"/>
        </fieldset>
    </form>

    <p style="color: red;">${errorMessage}</p>

    <br><br><br>

    <h1>Skift password:</h1>

    <form action="/CreateUser" method="post">
        <fieldset>
            <label>Nuværende password:</label>
            <input type="password" name="currentPass" width="30"/>
            <br>
            <label>Nyt password:</label>
            <input type="password" name="newPass" width="30"/>
            <br>
            <input type="hidden" name="action" value="editPass">
            <br>
            <input type="submit" value="Skift password"/>
            <p style="color: red;">${passError}</p>
            <a href="gallery.jsp">Annuller</a>
        </fieldset>
    </form>



</body>
</html>
