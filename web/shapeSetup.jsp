<%-- 
    Document   : rectangle
    Created on : Jan 25, 2014, 3:29:45 PM
    Author     : gcDataTechnology
--%>

<%@page import="edu.wctc.distjava.shape.controller.ShapeController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Java Shape</title>
    </head>
    <body>
        <%
            out.print(request.getAttribute("shapeSetupForm"));
            out.print("<input type='hidden' id='shapeSelection' name='shapeSelection' value='"+ (String)request.getAttribute("shapeSelection") +"' />");
        %>
        
    </body>
</html>
