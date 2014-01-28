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
    public static enum FromPage{SHAPE_SELECTION,SHAPE_SETUP};
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
        String name="Shape_" + request.getParameter("shapeSelection")+".java";
        
        
         
        switch(page){
            case "SHAPE_SELECTION": 
                redirectPage="setup_shape.jsp";
                //request.setAttribute("welcomeUser", greetingText);
                htmlEntities=ShapeHtmlFactory.getHTML(name);
                if(htmlEntities!=null||!htmlEntities.isEmpty()){
                request.setAttribute("shapeSetupForm", htmlEntities);
                }
                break;
                
            case "SHAPE_SETUP": redirectPage="shapeResults.jsp";
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
