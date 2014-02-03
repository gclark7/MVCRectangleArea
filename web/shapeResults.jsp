<%-- 
    Document   : shapeResults
    Created on : Jan 27, 2014, 6:18:37 PM
    Author     : gcDataTechnology
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Java Shape</title>
    </head>
    
    <body>
        <h1>Your Shape</h1>
        <%
            out.print(request.getAttribute("dimensions"));
            out.print(request.getAttribute("calculations"));
        
        %>
        <footer><%out.print(request.getAttribute("emailValue"));%></footer>  
    </body>
</html>
