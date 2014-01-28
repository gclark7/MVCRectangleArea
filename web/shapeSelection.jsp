<%-- 
    Document   : shapes
    Created on : Jan 25, 2014, 4:56:28 PM
    Author     : gcDataTechnology
--%>

<%@page import="edu.wctc.distjava.shape.controller.ShapeController"%>
<%@page import="javax.swing.JOptionPane"%>
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
        <form id="frmShapeSelection" name="frmShapeSelection" method="POST" action="ShapeController.do">
            <select id="shapeSelection" name="shapeSelection">
            
                <% 
                    /*
                    String ops="";
                    out.print("BeforeFile");
                    File f= new File("Web Pages");
                    out.print(f.getPath());
                    for(File fs:f.listFiles()){
                        out.print(fs.getName());
                        if(fs.getName().contains("shape_")){
                            ops+="<option><a href='" + fs.getName() +"' >" + fs.getName() +"</a></option>";
                        }
                    }
                    out.print(ops);
                        */

                %>
                <option id="rectangle" value="Rectangle">Rectangle</option>
            </select>
            <input type="text" name="page" id="page" 
                <%
                out.print(" value='" + ShapeController.FromPage.SHAPE_SELECTION + "' />");
                %>
            
            <input type="submit" id="btnSubmit" name="btnSubmit" value="Select Shape"/>
        </form>
    </body>
</html>
