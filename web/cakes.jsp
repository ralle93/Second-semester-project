<%@ page import="java.util.ArrayList" %>
<%@ page import="applayer.Cake" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cakes</title>
</head>
<body>

    <h1>Cakes:</h1><br>

    <%
        ArrayList<Cake> cakes = (ArrayList<Cake>)request.getAttribute("cakes");

        for (Cake c : cakes) {
            out.println(c);
            out.println("<form action=\"/ShoppingCart\" method=\"post\">\n" +
                    "<input type=\"hidden\" name=\"cakeID\" value=\"" + c.getId() + "\"></input>" +
                    "    <input type=\"submit\" value=\"Buy cake\" />\n" +
                    "</form>");
        }
    %>

</body>
</html>
