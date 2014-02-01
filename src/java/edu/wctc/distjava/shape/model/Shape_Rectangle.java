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
public class Shape_Rectangle implements Shape{

    private final boolean hasSides=true;
    private boolean is3D=false;//might be a good candidate for the Decorator Pattern and a Wrapper Class
    private Map dimensions;
    private final double DEF_LENG=5.00;
    private final double DEF_WID=6.00;
     private final String SHAPE_NAME="Rectangle";
    //private final String FORM_ID="rectangleDimensions";
    private final String FORM_ID_NAME="rectangleDimensions";
    private final String ACTION="ShapeController.do";
    
    private final String INPUT_ID_NAME_LENGTH="length";
    private final String INPUT_ID_NAME_WIDTH="width";
    
    private final String HEIGHT="height";
    private final String INPUT_HIDDEN_ID_NAME_PAGE="page";
    private final String INPUT_HIDDEN_PAGE_VALUE="SHAPE_SETUP";
    private final String INPUT_HIDDEN_SHAPE_SELECTION="Shape_Rectangle";
    private final String INPUT_ID_NAME_SUBMIT="btnSubmitRectangle";
    
    //Constructors
    /**
     * List this Class in the shapeConfig.properties file for user selection to work properly
     */
    public Shape_Rectangle(){
        setDimensions(5.0,5.0);//default values
    }
    
    
    /**
     * Public Constructor must have a length and width
     *   Will construct a Rectangle with default values if dimension Exceptions occur
     * @param length double value 
     * @param width double value
     */
    public Shape_Rectangle(double length, double width){
        setDimensions(length,width);
    }
    
     /**
     * Public Constructor must have a length and width
     *   Will construct a Rectangle with default values if dimension Exceptions occur
     * @param length double value 
     * @param width double value
     */
    public Shape_Rectangle(Map<String,Double> dim){
        setDimensions(dim);
    }
    
    private void setDimensions(double length, double width){
        HashMap<String,Double> dimens = new HashMap();
        Double dLength=null;
        Double dWidth = null;
        if(length>0 && width>0){
            dLength=new Double(length);
            dWidth = new Double(width);
            
        }else{
            dLength=new Double(DEF_LENG);
            dWidth = new Double(DEF_WID);
            
        }
        dimens.put(INPUT_ID_NAME_LENGTH, dLength);
        dimens.put(INPUT_ID_NAME_WIDTH, dWidth);
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
                d=DEF_LENG;
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
        dimensions.put(HEIGHT,height);
    }
    /**
     * In this class the rectangle is defined to always have sides
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
        
        return dimensions;
    }

    /**
     * Getter method for calculated measurements: volume, area, diameter
     * @return Map of key value measurements
     */
    @Override
    public Map<String, Double> getCalculatedMeasurments() {
        HashMap<String,Double> calculations = new HashMap();
        
        Double dArea= (Double)dimensions.get(INPUT_ID_NAME_LENGTH)*(Double)dimensions.get(INPUT_ID_NAME_WIDTH);
        calculations.put("Area", dArea);
        if(is3D){
            calculations.put("Volume", dArea*(Double)dimensions.get(HEIGHT));
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
        return "Rectangle{" + "hasSides=" + hasSides + ", is3D=" + is3D + ", dimensions=" + dimensions + '}';
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
        final Shape_Rectangle other = (Shape_Rectangle) obj;
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
        String htmlEntities=" <h1>Setup Your " +SHAPE_NAME + "</h1>\n" +
"        <form id=\"rectangleDimensions\" name=\"rectangleDimensions\" method=\"POST\" action=\"ShapeController.do\">\n" +
"            <p>If values are unrecognized or not supplied a default Shape will be created</p>\n" +
"            <label for=\"length\">Length</label>\n" +
"            <input id=\"length\" name=\"length\" type=\"number\" value=\"0.00\"/>\n" +
"            \n" +
"            <label for=\"width\">Width</label>\n" +
"            <input id=\"width\" name=\"width\" type=\"number\" value=\"0.00\"/>\n" +
"            <input type=\"hidden\" name=\"page\" id=\"page\" value='" +
               INPUT_HIDDEN_PAGE_VALUE +"' />" + 
                "<input type='hidden' id='shapeSelection' name='shapeSelection' value='" + INPUT_HIDDEN_SHAPE_SELECTION + "'/>"+
"            <input type=\"submit\" id=\"btnSubmitShape\" name=\"btnSubmitShape\" value=\"Setup Shape\"/>"+
"        </form>";
        
        return htmlEntities;
        
    }

   
    
    /**
     * MUST have the Implementing Shape Class listed in the shapeConfig.properties file
     * HTML STEP 2
     */
    @Override
    public List<String> getHtmlParametersFromShapeSetup() {
        List parms=new ArrayList();
        parms.add(this.INPUT_HIDDEN_ID_NAME_PAGE);
        parms.add(this.INPUT_ID_NAME_LENGTH);
        parms.add(this.INPUT_ID_NAME_WIDTH);
        
        return parms;
    }
    
      /**
     * Used to retrieve ONLY the number values for Shape Dimension
     * @return 
     */
     @Override
    public List<String> getHtmlParametersFORShapeSetup() {
        List parms=new ArrayList();
       
        parms.add(this.INPUT_ID_NAME_LENGTH);
        parms.add(this.INPUT_ID_NAME_WIDTH);
                    
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
    
    /**
     * Dimension test for user provided values
     * Ensures all values are provided and of the correct data type cast
     * @param htmlDimensionParameters Map of user Dimensions
     * @return 
     */
    @Override
    public boolean correctDimensions(Map<String,String> htmlDimensionParameters){
        boolean correct=false;
        boolean noErrors=true;//if 1 error then correct stays false;
        
        //Generic check for numbers -- All Values
        for(String s:htmlDimensionParameters.values()){
            if(s!=null){
                try{
                    Double.parseDouble(s);
                }catch(NumberFormatException e){
                    noErrors=false;
                }
            }else{
                    noErrors=false;
                }
        }
        
        //Check needed values for calculations and dimensions
        if(htmlDimensionParameters.get(INPUT_ID_NAME_LENGTH)==null ||
           htmlDimensionParameters.get(INPUT_ID_NAME_WIDTH)==null){
            noErrors=false;
        }
        
        //Offical ruling
        if(noErrors){
            correct=true;
        }else{correct=false;}
        return correct;
    }
    
    
}
