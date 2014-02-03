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
import java.util.Iterator;
import java.util.List;
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
        redirectPage=request.getParameter("back");
        //request.setAttribute(fromPage, redirectPage);
        RequestDispatcher view = request.getRequestDispatcher(redirectPage);
        view.forward(request, response);
        
        
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
        //processRequest(request, response);
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
        String dimResult="";
        String fromPage="";
        
        String emailValue=getServletConfig().getInitParameter("adminMail");
        request.setAttribute("emailValue", emailValue);
        
        switch(page){
            
            case SHAPE_SELECTION: 
                redirectPage="shapeSetup.jsp";
                fromPage="shapeSelection.jsp";
                
                try{
                //request.setAttribute("welcomeUser", greetingText);
                    shape=ShapeHtmlFactory.getShape(name);
                    //htmlEntities=ShapeHtmlFactory.getHTML(name);
                    htmlEntities=shape.getHtmlForShapeSetup();
                    
                }catch(Exception e){
                    htmlEntities="Exception "+e + " in ShapeController<br/><a href='shapeSelection.jsp'>Try Again</a>"+
                            "<footer>" + getServletConfig().getInitParameter("adminMail")+"</footer>";
                }
                if(htmlEntities==null||htmlEntities.isEmpty()){
                    htmlEntities="<h1>Null Entities</h1><a href='shapeSelection.jsp'>Try Again</a>"+
                            "<footer>" + getServletConfig().getInitParameter("adminMail")+"</footer>";
                }
                 request.setAttribute("shapeSetupForm", htmlEntities);
                 request.setAttribute("shapeSelection",name);
                 //request.setAttribute("shape", shape);
                 
                break;
                
            case SHAPE_SETUP: redirectPage="shapeResults.jsp";
                fromPage="shapeSetup.jsp";
                //name=request.getParameter("shapeSelection");
                //shape=(Shape)request.getAttribute("shape");
                 
                try{
                    
                    shape=ShapeHtmlFactory.getShape(name);
                }catch(Exception e){
                    
                }
                //set shape dimesions & Check for correct values types and needed input
                HashMap<String,String> userParameters = new HashMap<>();
                HashMap<String,Double> userDim=new HashMap<>();
                Double d=0.00;
                List htmlDimensionParameters = shape.getHtmlParametersFORShapeSetup();
                Iterator val = htmlDimensionParameters.iterator();
                Object p;
                
                //populate dimensions from user --> send for verification
                
                while(val.hasNext()){   
                    p= val.next();
                    userParameters.put((String)p, request.getParameter((String)p));
                }
                //rest iterator
                val=htmlDimensionParameters.iterator();
                    //Procede with calculations if correct
                if(shape.correctDimensions(userParameters)){
                    //cast Strings to Doubles
                    try{
                        while(val.hasNext()){
                             p= val.next();
                            userDim.put((String)p,(Double)Double.parseDouble(userParameters.get((String)p)));
                        }
                    }catch(NumberFormatException e){
                        redirectPage="shapeSetup.jsp";
                        fromPage="shapeSelection.jsp";
                        request.setAttribute("shapeSetupForm", shape.getHtmlForShapeSetup() + shape.getShapeErrorHTML());
                    }
                    //set Shape dimensions
                    shape.setDimensions(userDim);
                }else{
                    //setup error -->wrong values, text not nums
                    redirectPage="shapeSetup.jsp";
                    fromPage="shapeSelection.jsp";
                    request.setAttribute("shapeSetupForm", shape.getHtmlForShapeSetup() + shape.getShapeErrorHTML());
                }
                
                //get dimensions from Shape
                dimResult="<p><h3>Shape Dimensions</h3><br/>";
                pattern=": ";
                Map<String,Double> md=shape.getDimensions();
                    for(String s: md.keySet()){
                        
                        dimResult+=s+pattern + Double.toString(md.get(s)) + "<br/>";
                    }
                    
                    request.setAttribute("dimensions",dimResult );
                    
                //get calculated values from Shape
                calcResult="<p><h3>Shape Calculations</h3><br/>";
                pattern=": ";
                Map<String,Double> m=shape.getCalculatedMeasurments();
                    for(String s: m.keySet()){
                        
                        calcResult+=s+pattern + Double.toString(m.get(s)) + "<br/>";
                    }
                    calcResult+="</p><a href='shapeSelection.jsp'>Do it Again</a>";
                    request.setAttribute("calculations",calcResult );
                    
                break;
                
            default: redirectPage="shapeSelection.jsp";
                fromPage="index.html";
        }
         
        
        //processRequest(request, response);
        
        request.setAttribute("fromPage", fromPage);
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
