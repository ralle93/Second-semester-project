<%--
  Created by IntelliJ IDEA.
  User: tommytroest
  Date: 19/05/2017
  Time: 06.39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table>
<form action="addtocart">
    <th>Navn</th><th>Pris</th><th>Tilføj</th>
    <tr>
        <td>TestKage1</td><td>500</td>
        <td>
            <img src="images/sample_image1.jpg" width="300">
            <input type="hidden" name="name" value="TestKage1">
            <input type="hidden" name="Pris" value="500">
            <input type="submit" value="Tilføj">
        </td>
    </tr>
</form>
<form action="addtocart">
    <th>Navn</th><th>Pris</th><th>Tilføj</th>
    <tr>
        <td>TestKage2</td><td>600</td>
        <td>
            <img src="images/sample_image2.jpg" width="300">
            <input type="hidden" name="name" value="TestKage2">
            <input type="hidden" name="Pris" value="600">
            <input type="submit" value="Tilføj">
        </td>
    </tr>
</form>
</table>
