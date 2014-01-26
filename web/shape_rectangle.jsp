<%-- 
    Document   : rectangle
    Created on : Jan 25, 2014, 3:29:45 PM
    Author     : gcDataTechnology
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Java Rectangle</title>
    </head>
    <body>
        <h1>Setup Your Rectangle</h1>
        <form id="rectangleDimensions" name="rectangleDimensions" method="POST" action="SetupRectangle.do">
            <label for="length">Length</label>
            <input id="length" type="number" value="0.00"/>
            
            <label for="width">Width</label>
            <input id="width" type="number" value="0.00"/>
            
            <input type="submit" id="btnSubmitRectangle" name="btnSubmitRectangle" value="Setup Rectangle"/>
            
        </form>
        
    </body>
</html>
