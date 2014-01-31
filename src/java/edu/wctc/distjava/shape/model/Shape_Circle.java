/*
 * Rectangle.java
 *  implements Shape
 *
 * Date: 1/25/2014 
 * Revision: Rev0 
 * Copy write: all rights reserved, may be used for educational purposes and is delivered "As is" with no guarantees for performance
 */

package edu.wctc.distjava.shape.model;

import edu.wctc.distjava.shape.controller.ShapeController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//import org.springframework.stereotype.Service;

/**
 *
 * @author gcDataTechnology
 */
//@Service
public class Shape_Circle implements Shape{

    private final boolean hasSides=false;
    private boolean is3D=false;//might be a good candidate for the Decorator Pattern and a Wrapper Class
    private Map dimensions;
    private final double DEF_RADIUS=5.00;
    //private final double DEF_SIDEB=6.00;
    
    //private final String FORM_ID="rectangleDimensions";
    private final String FORM_ID_NAME="circleDimensions";
    private final String ACTION="ShapeController.do";
    private final String INPUT_ID_NAME_RADIUS="radius";
    private final String DIAMETER="diameter";
    //private final String INPUT_ID_NAME_SIDEB="sideB";
    //private final String INPUT_ID_NAME_ANGLEA="angleA";
    private final String INPUT_HIDDEN_ID_NAME_PAGE="page";
    private final String INPUT_HIDDEN_PAGE_VALUE="SHAPE_SETUP";
    private final String INPUT_HIDDEN_SHAPE_SELECTION="Shape_Circle";
    private final String INPUT_ID_NAME_SUBMIT="btnSubmitCircle";
    
    //Constructors
    /**
     * List this Class in the shapeConfig.properties file for user selection to work properly
     */
    public Shape_Circle(){
        setDimensions(DEF_RADIUS);//default values
    }
    
    
    /**
     * Public Constructor must have a length and width
     *   Will construct a Rectangle with default values if dimension Exceptions occur
     * @param length double value 
     * @param width double value
     */
    public Shape_Circle(double radius){
        setDimensions(radius);
    }
    
    private void setDimensions(double radius){
        HashMap<String,Double> dimens = new HashMap();
        Double dRadius=null;
        
        if(radius>0 ){
            dRadius=new Double(radius);
            
            
        }else{
            dRadius=new Double(DEF_RADIUS);
            
            
        }
        dimens.put(INPUT_ID_NAME_RADIUS, dRadius);
        
        dimensions=dimens;
    }
    
     /**
     * Enables the web user to set specific values
     * @param dim Map of dimensions String key, Double values
     */
    @Override
    public void setDimensions(Map<String,Double> dim){
        for(Double d:dim.values()){
            if(d<0){
                d=Math.abs((double)d);
            }else if(d==0){
                d=DEF_RADIUS;
            }
        }
        
        dimensions=dim;
        
    }
    
    
    /**
     * True / false indication of a 3 Dimensional Object
     * @return true / false 
     */
    @Override
    public boolean is3D(){
        return is3D;
    }
    
    /**
     * Sets the variable is3D to indicate a 3Dimensional Object
     * @param is3D boolean true / false
     */
    @Override
    public void setIs3D(boolean is3D, double height){
        if(is3D){
            this.is3D=is3D;
        }else{this.is3D=false;}
        dimensions.put("height",height);
    }
    /**
     * In this class the circle is defined to Never have sides
     * @return true
     */
    @Override
    public boolean hasSides() {
        return hasSides;
    }

    /**
     * Getter method for dimensions Map
     * @return Map of key value dimensions
     */
    @Override
    public Map getDimensions() {
        Map<String,Double> m = dimensions;
        m.put(DIAMETER, (Double)dimensions.get(INPUT_ID_NAME_RADIUS)*2);
        return m;
    }

    
    
    /**
     * Getter method for calculated measurements: volume, area, diameter
     * @return Map of key value measurements
     */
    @Override
    public Map<String, Double> getCalculatedMeasurments() {
        HashMap<String,Double> calculations = new HashMap();
        
        Double dCirc= (Double)Math.pow((double)dimensions.get(INPUT_ID_NAME_RADIUS),2)*2*Math.PI;
        calculations.put("Circumference", dCirc);
        calculations.put("Area",(Double)Math.pow((double)dimensions.get(INPUT_ID_NAME_RADIUS),2)*Math.PI );
        if(is3D){
            //calculations.put("Volume", dArea*(Double)dimensions.get("Height"));
        }
        
        return calculations;
    }

    /**
     * Compares an Object to this Rectangle
     * All non equal Objects will be indicated as before this Rectangle
     * @param t Object to compare
     * @return int -1 indicates before, 0 indicates equal, 1 indicates after
     */
    @Override
    public int compareTo(Object t) {
        int compare=-1;
        if(this.getClass()==t.getClass()){
            if(this.toString().equals(t.toString())){
                compare=0;
            }
                
        }
        return compare;
    }

    /**
     * String representation of the Rectangle Object
     * @return String description of Object
     */
    @Override
    public String toString() {
        return "Shape_Circle{" + "hasSides=" + hasSides + ", is3D=" + is3D + ", dimensions=" + dimensions + '}';
    }

    

    /**
     * int value of the HashCode used for comparison purposes
     * @return int HashCode value
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + (this.hasSides ? 1 : 0);
        hash = 61 * hash + (this.is3D ? 1 : 0);
        hash = 61 * hash + Objects.hashCode(this.dimensions);
        return hash;
    }

    /**
     * Comparison method to compare Objects to this Rectangle Object
     * @param obj Object to compare
     * @return boolean true / false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Shape_Circle other = (Shape_Circle) obj;
        if (this.hasSides != other.hasSides) {
            return false;
        }
        if (this.is3D != other.is3D) {
            return false;
        }
        if (!Objects.equals(this.dimensions, other.dimensions)) {
            return false;
        }
        return true;
    }

    /**
     * MUST have the Implementing Shape Class listed in the shapeConfig.properties file
     * HTML STEP 1
     * @return 
     */
    @Override
    public String getHtmlForShapeSetup() {
        String htmlEntities=" <h1>Setup Your Rectangle</h1>\n" +
"        <form id='"+ FORM_ID_NAME + "' name='" +FORM_ID_NAME+"' method=\"POST\" action='" + ACTION +"'>\n" +
"            <p>If values are unrecognized or not supplied a default Rectangle will be created</p>\n" +
"            <label for=\"radius\">Radius</label>\n" +
"            <input id=\"radius\" name=\"radius\" type=\"number\" value=\"0.00\"/>\n" +
"            \n" +
"            <input type=\"text\" name='"+INPUT_HIDDEN_ID_NAME_PAGE+"' id=\"page\" value='" +
               INPUT_HIDDEN_PAGE_VALUE +"' />" + 
                "<input type='hidden' id='shapeSelection' name='shapeSelection' value='" + INPUT_HIDDEN_SHAPE_SELECTION + "'/>"+
"            <input type=\"submit\" id=\"btnSubmitShape\" name=\"btnSubmitShape\" value=\"Setup Shape\"/>"+
"        </form>";
        
        return htmlEntities;
        
    }

    /**
     * Used to retrieve ALL HTML parameters
     */
    @Override
    public List<String> getHtmlParametersFromShapeSetup() {
        List parms=new ArrayList();
        parms.add(this.INPUT_HIDDEN_ID_NAME_PAGE);
        parms.add(this.INPUT_ID_NAME_RADIUS);
              
        return parms;
    }
    
    /**
     * Used to retrieve ONLY the number values for Shape Dimension
     * @return 
     */
     @Override
    public List<String> getHtmlParametersFORShapeSetup() {
        List parms=new ArrayList();
       
        parms.add(this.INPUT_ID_NAME_RADIUS);
              
        return parms;
    }
    
     /**
     * Error message for parse String to number
     * @return String error message
     */
    @Override
    public String getShapeErrorHTML() {
        return "<br/><p>Wrong input value - Text instead of Number</p></br>";
    }
    
}
