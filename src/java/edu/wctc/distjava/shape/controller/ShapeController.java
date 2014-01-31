/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wctc.distjava.shape.controller;

import edu.wctc.distjava.shape.model.Shape_Rectangle;
import edu.wctc.distjava.shape.model.Shape;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utiities.ShapeHtmlFactory;

/**
 *
 * @author gcDataTechnology
 */
//@WebServlet(name = "ShapeController", urlPatterns = {"/ShapeController"})
public class ShapeController extends HttpServlet {
    public static enum FromPage{SHAPE_SELECTION,SHAPE_SETUP};//Used on the corresponding jsp pages
    private final String SHAPE_SELECTION="SHAPE_SELECTION";
    private final String SHAPE_SETUP="SHAPE_SETUP";
    
    private String page="";
    private String redirectPage="shapeSelection.jsp";
    Class userShape=null;
    private Shape shape;
    
    /*
    public ShapeController(){
        System.out.println("Instantiated ShapeController");
    }
    */
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        response.setContentType("text/html;charset=UTF-8");
        
         //need to add exception handling
        Shape rectangle= new Shape_Rectangle((double)Double.parseDouble((String)request.getParameter("length")),(double)Double.parseDouble((String)request.getParameter("width")));
        //String dimension=(String)request.getAttribute("Length");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RectangleController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RectangleController at " + request.getContextPath() + 
                    "<br/><br/> First Servlet</h1>");
           
        
       
         out.println("<p>"+rectangle.getCalculatedMeasurments()+"</p>");
            
            
        
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * 
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String htmlEntities="";
        page=request.getParameter("page");
        String name= request.getParameter("shapeSelection");
        String calcResult="";
        String pattern="";
         
        switch(page){
            
            case SHAPE_SELECTION: 
                redirectPage="shapeSetup.jsp";
                try{
                //request.setAttribute("welcomeUser", greetingText);
                    shape=ShapeHtmlFactory.getShape(name);
                    //htmlEntities=ShapeHtmlFactory.getHTML(name);
                    htmlEntities=shape.getHtmlForShapeSetup();
                    
                }catch(Exception e){
                    htmlEntities="Exception "+e + " in ShapeController<br/><a href='shapeSelection.jsp'>Try Again</a>";
                }
                if(htmlEntities==null||htmlEntities.isEmpty()){
                    htmlEntities="<h1>Null Entities</h1><a href='shapeSelection.jsp'>Try Again</a>";
                }
                 request.setAttribute("shapeSetupForm", htmlEntities);
                 request.setAttribute("shapeSelection",name);
                 //request.setAttribute("shape", shape);
                 
                break;
                
            case SHAPE_SETUP: redirectPage="shapeResults.jsp";
                //name=request.getParameter("shapeSelection");
                //shape=(Shape)request.getAttribute("shape");
                try{
                    
                    shape=ShapeHtmlFactory.getShape(name);
                }catch(Exception e){
                    
                }
                //set shape dimesions
                HashMap<String,Double> userDim=new HashMap<>();
                Double d=0.00;
                for(String s:shape.getHtmlParametersFORShapeSetup()){
                    try{
                        String parm = (String)request.getParameter(s);
                        if(parm!=null){
                            d = Double.parseDouble(parm);
                             userDim.put(s, d);
                        }
                        
                    }catch(NumberFormatException n){
                        //setup error -->wrong values, text not nums
                        redirectPage="shapeSetup.jsp";
                        d=0.00;
                         userDim.put(s, d);
                        request.setAttribute("shapeSetupForm", shape.getHtmlForShapeSetup() + shape.getShapeErrorHTML());
                    }
                   
                }
                
                shape.setDimensions(userDim);
                
                //get calculated values from Shape
                calcResult="<p><h3>Shape Calculations</h3><br/>";
                pattern=": ";
                Map<String,Double> m=shape.getCalculatedMeasurments();
                    for(String s: m.keySet()){
                        
                        calcResult+=s+pattern + Double.toString(m.get(s)) + "<br/>";
                    }
                    calcResult+="</p><a href='index.html'>Do it Again</a>";
                    request.setAttribute("calculations",calcResult );
                break;
            default: redirectPage="shapeSelection.jsp";
        }
         
        
        //processRequest(request, response);
        
        
        RequestDispatcher view = request.getRequestDispatcher(redirectPage);
        view.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
