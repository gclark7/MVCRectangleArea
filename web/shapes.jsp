<%-- 
    Document   : shapes
    Created on : Jan 25, 2014, 4:56:28 PM
    Author     : gcDataTechnology
--%>

<%@page import="java.nio.file.Files"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shape Selection</title>
    </head>
    <body>
        <h1>Select a Shape</h1>
        <select>
            
            <% 
                String ops="<option>";
                File f= new File("Web Pages");
                for(File fs:f.listFiles()){
                    if(fs.getName().contains("shape_")){
                        ops+="<a href='" + fs.getName() +"' >" + fs.getName() +"</a></option>";
                    }
                }
            %>
            
        </select>
    </body>
</html>
