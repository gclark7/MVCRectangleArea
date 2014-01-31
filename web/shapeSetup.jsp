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
        
        
        <!--h1>Setup Your Rectangle</h1>
        <form id="rectangleDimensions" name="rectangleDimensions" method="POST" action="ShapeController.do">
            <p>If values are unrecognized or not supplied a default Rectangle will be created</p>
            <label for="length">Length</label>
            <input id="length" name="length" type="number" value="0.00"/>
            
            <label for="width">Width</label>
            <input id="width" name="width" type="number" value="0.00"/>
            <input type="hidden" name="page" id="page" value=
                <%
                //out.println("'" + ShapeController.FromPage.SHAPE_SETUP + "'");
                %>
            />
            <input type="submit" id="btnSubmitRectangle" name="btnSubmitRectangle" value="Setup Rectangle"/>
            
        </form-->
        <%
            out.print(request.getAttribute("shapeSetupForm"));
            out.print("<input type='text' id='shapeSelection' name='shapeSelection' value='"+ (String)request.getAttribute("shapeSelection") +"' />");
        %>
        
    </body>
</html>
