<%--
    Made by Søren Diamant Skovgaard & Tommy Troest
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rediger bruger oplysninger</title>
    <link rel="stylesheet" type="text/css" href="css/mainstylesheet.css">
    <link rel="stylesheet" type="text/css" href="css/edit-user-stylesheet.css">
</head>
<body>

    <h1>Rediger bruger oplysninger:</h1>

    <form action="/CreateUser" method="post">
        <fieldset id="bios-field">
        <label for="email" id="email-label">E-mail:</label>
            <br>
        <input type="text" name="email" id="email" width="300" value="${email}"/>
        <br>
        <label for="name" id="name-label">Navn:</label>
            <br>
        <input type="text" name="name" id="name" width="300" value="${name}"/>
        <br>
        <label for="number" id="phone-label">Telefon nr:</label>
            <br>
        <input type="text" name="number" id="number" width="300" value="${number}"/>
        <br>
        <input type="hidden" name="action" value="edit">
        <input type="submit" value="Rediger bruger oplysninger" id="bios-submit-button"/>
        </fieldset>
    </form>

    <p style="color: red;">${errorMessage}</p>

    <br><br><br>

    <h1>Skift password:</h1>

    <form action="/CreateUser" method="post">
        <fieldset id="change-password-field">
            <label for="current-password-field">Nuværende password:</label>
            <input type="password" name="currentPass" id="current-password-field" width="30"/>
            <br>
            <label for="new-password-field">Nyt password:</label>
            <input type="password" name="newPass" id="new-password-field" width="30"/>
            <br>
            <input type="hidden" name="action" value="editPass">
            <br>
            <input type="submit" value="Skift password" id="password-submit-button"/>
            <p style="color: red;">${passError}</p>
            <a href="gallery.jsp">Annuller</a>
        </fieldset>
    </form>



</body>
</html>
