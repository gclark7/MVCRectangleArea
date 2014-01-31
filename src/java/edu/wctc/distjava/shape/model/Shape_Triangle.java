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
public class Shape_Triangle implements Shape{

    private final boolean hasSides=true;
    private boolean is3D=false;//might be a good candidate for the Decorator Pattern and a Wrapper Class
    private Map dimensions;
    private final double DEF_SIDEA=5.00;
    private final double DEF_SIDEB=6.00;
    
    //private final String FORM_ID="rectangleDimensions";
    private final String FORM_ID_NAME="triangleDimensions";
    private final String ACTION="ShapeController.do";
    private final String INPUT_ID_NAME_SIDEA="sideA";
    private final String INPUT_ID_NAME_SIDEB="sideB";
    private final String INPUT_ID_NAME_ANGLEA="angleA";
    private final String INPUT_HIDDEN_ID_NAME_PAGE="page";
    private final String INPUT_HIDDEN_PAGE_VALUE="SHAPE_SETUP";
    private final String INPUT_HIDDEN_SHAPE_SELECTION="Shape_Triangle";
    private final String INPUT_ID_NAME_SUBMIT="btnSubmitTriangle";
    
    //Constructors
    /**
     * List this Class in the shapeConfig.properties file for user selection to work properly
     */
    public Shape_Triangle(){
        setDimensions(5.0,5.0);//default values
    }
    
    
    /**
     * Public Constructor must have a length and width
     *   Will construct a Rectangle with default values if dimension Exceptions occur
     * @param length double value 
     * @param width double value
     */
    public Shape_Triangle(double sideA, double sideB){
        setDimensions(sideA,sideB);
    }
    
    private void setDimensions(double sideA, double sideB){
        HashMap<String,Double> dimens = new HashMap();
        Double dSideA=null;
        Double dSideB = null;
        if(sideA>0 && sideB>0){
            dSideA=new Double(sideA);
            dSideB = new Double(sideB);
            
        }else{
            dSideA=new Double(DEF_SIDEA);
            dSideB = new Double(DEF_SIDEB);
            
        }
        dimens.put(INPUT_ID_NAME_SIDEA, dSideA);
        dimens.put(INPUT_ID_NAME_SIDEB, dSideB);
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
                d=DEF_SIDEA;
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
        dimensions.put("Height",height);
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
        Map<String,Double> m = dimensions;
        m.put("sideC", calculateSideC());
        return m;
    }

    private Double calculateSideC(){
        return Math.sqrt((Math.pow((double)dimensions.get(INPUT_ID_NAME_SIDEA),2.0) + Math.pow((double)dimensions.get(INPUT_ID_NAME_SIDEB),2.0)));
     }
    
    /**
     * Getter method for calculated measurements: volume, area, diameter
     * @return Map of key value measurements
     */
    @Override
    public Map<String, Double> getCalculatedMeasurments() {
        HashMap<String,Double> calculations = new HashMap();
        
        Double dArea= (Double)dimensions.get("Length")*(Double)dimensions.get("Width");
        calculations.put("Area", dArea);
        if(is3D){
            calculations.put("Volume", dArea*(Double)dimensions.get("Height"));
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
        final Shape_Triangle other = (Shape_Triangle) obj;
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
"        <form id=\"rectangleDimensions\" name=\"rectangleDimensions\" method=\"POST\" action=\"ShapeController.do\">\n" +
"            <p>If values are unrecognized or not supplied a default Rectangle will be created</p>\n" +
"            <label for=\"length\">Length</label>\n" +
"            <input id=\"length\" name=\"length\" type=\"number\" value=\"0.00\"/>\n" +
"            \n" +
"            <label for=\"width\">Width</label>\n" +
"            <input id=\"width\" name=\"width\" type=\"number\" value=\"0.00\"/>\n" +
"            <input type=\"text\" name=\"page\" id=\"page\" value='" +
               INPUT_HIDDEN_PAGE_VALUE +"' />" + 
                "<input type='hidden' id='shapeSelection' name='shapeSelection' value='" + INPUT_HIDDEN_SHAPE_SELECTION + "'/>"+
"            <input type=\"submit\" id=\"btnSubmitShape\" name=\"btnSubmitShape\" value=\"Setup Shape\"/>"+
"        </form>";
        
        return htmlEntities;
        
    }

    public static String staticGetHtmlForShapeSetup() {
        String htmlEntities=" <h1>Setup Your Rectangle</h1>\n" +
"        <form id=\"rectangleDimensions\" name=\"rectangleDimensions\" method=\"POST\" action=\"ShapeController.do\">\n" +
"            <p>If values are unrecognized or not supplied a default Rectangle will be created</p>\n" +
"            <label for=\"length\">Length</label>\n" +
"            <input id=\"length\" name=\"length\" type=\"number\" value=\"0.00\"/>\n" +
"            \n" +
"            <label for=\"width\">Width</label>\n" +
"            <input id=\"width\" name=\"width\" type=\"number\" value=\"0.00\"/>\n" +
"            <input type=\"hidden\" name=\"page\" id=\"page\" value=\n" +
"                <%\n" +
"                out.println(\"'\" + ShapeController.FromPage.SHAPE_SETUP + \"'\");\n" +
"                %>\n" +
"            />\n" +
"            <input type=\"submit\" id=\"btnSubmitRectangle\" name=\"btnSubmitRectangle\" value=\"Setup Rectangle\"/>\n" +
"            \n" +
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
        parms.add(this.INPUT_ID_NAME_SIDEA);
        parms.add(this.INPUT_ID_NAME_SIDEB);
        parms.add(this.INPUT_ID_NAME_ANGLEA);
        
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
     * Used to retrieve ONLY the number values for Shape Dimension
     * @return 
     */
     @Override
    public List<String> getHtmlParametersFORShapeSetup() {
        List parms=new ArrayList();
       
        parms.add(this.INPUT_ID_NAME_SIDEA);
        parms.add(this.INPUT_ID_NAME_SIDEB);
        parms.add(this.INPUT_ID_NAME_ANGLEA);
              
        return parms;
    }
    
}
