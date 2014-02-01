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
import java.util.LinkedHashMap;
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
    private final double DEF_SIDEB=5.00;
    private final double DEF_SIDEC=5.00;
    private final double DEF_ANGLE=30;
    private final double DEG=180;
    
     private final String SHAPE_NAME="Triangle";
    //private final String FORM_ID="rectangleDimensions";
    private final String FORM_ID_NAME="triangleDimensions";
    private final String ACTION="ShapeController.do";
    private final String INPUT_ID_NAME_SIDEA="sideA";
    private final String INPUT_ID_NAME_SIDEB="sideB";
    private final String INPUT_ID_NAME_SIDEC="sideC";
    private final String INPUT_ID_NAME_ANGLEA="angleA";
    private final String INPUT_ID_NAME_ANGLEB="angleB";
    private final String INPUT_ID_NAME_ANGLEC="angleC";
    private String missingSide="";
    private String missingAngle1="";
    private String missingAngle2="";
    private final String INPUT_HIDDEN_ID_NAME_PAGE="page";
    private final String INPUT_HIDDEN_PAGE_VALUE="SHAPE_SETUP";
    private final String INPUT_HIDDEN_SHAPE_SELECTION="Shape_Triangle";
    private final String INPUT_ID_NAME_SUBMIT="btnSubmitShape";
    
    private static enum UseMethod{SAS,SSA}
    private UseMethod methodToUse;
    private Map<String,Double> mapTemp;
    
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
        Map<String,String> m= new HashMap<>();
        for(String s:dim.keySet()){
            m.put(s, dim.get(s).toString());
        }
        if(correctDimensions(m)){
            dimensions=this.mapTemp;//temporary Map
        }
                
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
        
        if(this.methodToUse==UseMethod.SAS){
            m.put(missingSide,(Double)calculateSide() );
        }else {
        
        }
        return m;
    }

    private Double calculateSide(){
        Double c =new Double(0.00);
        if(missingSide.equals(INPUT_ID_NAME_SIDEC)){
            double p=2.0;
            Double aSq=new Double(Math.pow(mapTemp.get(INPUT_ID_NAME_SIDEA), p));
            Double bSq=new Double(Math.pow(mapTemp.get(INPUT_ID_NAME_SIDEB), p));
            Double twoABcosC=new Double(p*mapTemp.get(INPUT_ID_NAME_SIDEA)*mapTemp.get(INPUT_ID_NAME_SIDEA)*Math.cos(mapTemp.get(INPUT_ID_NAME_ANGLEC)));
            c = (Double)(Math.sqrt((double)aSq+bSq-twoABcosC));
        }
        return c;
     }
    
    /**
     * Getter method for calculated measurements: volume, area, diameter
     * @return Map of key value measurements
     */
    @Override
    public Map<String, Double> getCalculatedMeasurments() {
        HashMap<String,Double> calculations = new HashMap();
        
        
        if(is3D){
           
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
        return "Triangle{" + "hasSides=" + hasSides + ", is3D=" + is3D + ", dimensions=" + dimensions + '}';
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
        String htmlEntities=" <h1>Setup Your " + SHAPE_NAME +"</h1>\n" +
"        <form id='"+FORM_ID_NAME + "' name='"+FORM_ID_NAME+"' method=\"POST\" action='"+
                ACTION + "'>\n" +
"            <p>If values are unrecognized or not supplied a default Shape will be created</p>\n" +
"            <label for='"+ INPUT_ID_NAME_SIDEA + "'>"+INPUT_ID_NAME_SIDEA+"</label> &nbsp;" +
"            <input id='"+ INPUT_ID_NAME_SIDEA + "' name='"+ INPUT_ID_NAME_SIDEA + "' type=\"number\" value=\"0.00\" />\n" +
"            <label for='"+ INPUT_ID_NAME_SIDEB + "'>"+ INPUT_ID_NAME_SIDEB + "</label> &nbsp;" +
"            <input id='"+ INPUT_ID_NAME_SIDEB + "' name='"+ INPUT_ID_NAME_SIDEB + "' type=\"number\" value=\"0.00\"/>\n" +
             "<label for='"+ INPUT_ID_NAME_SIDEC + "'>"+ INPUT_ID_NAME_SIDEC + "</label> &nbsp;" +
"            <input id='"+ INPUT_ID_NAME_SIDEC + "' name='"+ INPUT_ID_NAME_SIDEC + "' type=\"number\" value=\"0.00\"/>\n" + 
                
             "<label for='"+ INPUT_ID_NAME_ANGLEA + "'>"+ INPUT_ID_NAME_ANGLEA + "</label> &nbsp;" +
"            <input id='"+ INPUT_ID_NAME_ANGLEA + "' name='"+ INPUT_ID_NAME_ANGLEA + "' type=\"number\" value='"+DEF_ANGLE + "'/>\n" +
             "<label for='"+ INPUT_ID_NAME_ANGLEB + "'>"+ INPUT_ID_NAME_ANGLEB + "</label> &nbsp;" +
"            <input id='"+ INPUT_ID_NAME_ANGLEB + "' name='"+ INPUT_ID_NAME_ANGLEB + "' type=\"number\" value=\"0.00\"/>\n" +
             "<label for='"+ INPUT_ID_NAME_ANGLEC + "'>"+ INPUT_ID_NAME_ANGLEC + "</label> &nbsp;" +
"            <input id='"+ INPUT_ID_NAME_ANGLEC + "' name='"+ INPUT_ID_NAME_ANGLEC + "' type=\"number\"  value=\"0.00\"/>\n" +
                
"            <input type=\"hidden\" name='"+INPUT_HIDDEN_ID_NAME_PAGE+"' id='" + INPUT_HIDDEN_ID_NAME_PAGE + "' value='" +
               INPUT_HIDDEN_PAGE_VALUE + "' />" + 
                "<input type='hidden' id='shapeSelection' name='shapeSelection' value='" + INPUT_HIDDEN_SHAPE_SELECTION + "'/>"+
"            <input type=\"submit\" id='"+INPUT_ID_NAME_SUBMIT+"' name='"+INPUT_ID_NAME_SUBMIT+"' value='Setup Shape'/>"+
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
        parms.add(INPUT_ID_NAME_SIDEA);
        parms.add(INPUT_ID_NAME_SIDEB);
        parms.add(INPUT_ID_NAME_SIDEC);
        parms.add(INPUT_ID_NAME_ANGLEA);
        parms.add(INPUT_ID_NAME_ANGLEB);
        parms.add(INPUT_ID_NAME_ANGLEC);
    
        parms.add(INPUT_HIDDEN_ID_NAME_PAGE);
        parms.add(INPUT_HIDDEN_PAGE_VALUE);
        
        return parms;
    }
    
     /**
     * Error message for parse String to number
     * @return String error message
     */
    @Override
    public String getShapeErrorHTML() {
        return "<br/><p>Wrong input value - Text instead of Number<br/>"+
                "Be sure Unused values are set to Zero</p></br>";
    }
    
     /**
     * Used to retrieve ONLY the number values for Shape Dimension
     * @return 
     */
     @Override
    public List<String> getHtmlParametersFORShapeSetup() {
        List parms=new ArrayList();
        parms.add(INPUT_ID_NAME_SIDEA);
        parms.add(INPUT_ID_NAME_SIDEB);
        parms.add(INPUT_ID_NAME_SIDEC);
        parms.add(INPUT_ID_NAME_ANGLEA);
        parms.add(INPUT_ID_NAME_ANGLEB);
        parms.add(INPUT_ID_NAME_ANGLEC);
              
        return parms;
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
        boolean noErrors=false;//if 1 error then correct stays false;
        this.mapTemp= new LinkedHashMap();       
        //Check needed values for calculations and dimensions
        //First Check is for any combination of Side-Angle-Side (SAS)
        
        //Sa,Sb,Ac
        if((htmlDimensionParameters.get(INPUT_ID_NAME_SIDEA)!=null && Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_SIDEA))!=0) &&
           (htmlDimensionParameters.get(INPUT_ID_NAME_SIDEB)!=null && Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_SIDEB))!=0) &&
           (htmlDimensionParameters.get(INPUT_ID_NAME_ANGLEC)!=null && Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_ANGLEC))!=0)){
                noErrors=true;
                this.methodToUse=UseMethod.SAS;
                
                mapTemp.put(INPUT_ID_NAME_SIDEA, (Double)Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_SIDEA)));
                mapTemp.put(INPUT_ID_NAME_SIDEB, (Double)Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_SIDEB)));
                mapTemp.put(INPUT_ID_NAME_ANGLEC, (Double)Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_ANGLEC)));
                mapTemp.put(INPUT_ID_NAME_SIDEC, new Double(0.00));
                missingSide=INPUT_ID_NAME_SIDEC;
                
        }else if((htmlDimensionParameters.get(INPUT_ID_NAME_SIDEA)!=null && Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_SIDEA))!=0) &&
           (htmlDimensionParameters.get(INPUT_ID_NAME_SIDEC)!=null && Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_SIDEC))!=0) &&
           (htmlDimensionParameters.get(INPUT_ID_NAME_ANGLEB)!=null && Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_ANGLEB))!=0)){
                noErrors=true;
                this.methodToUse=UseMethod.SAS;
                
                mapTemp.put(INPUT_ID_NAME_SIDEA, (Double)Math.abs(Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_SIDEA))));
                mapTemp.put(INPUT_ID_NAME_SIDEC, (Double)Math.abs(Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_SIDEC))));
                mapTemp.put(INPUT_ID_NAME_ANGLEB, (Double)Math.abs(Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_ANGLEB))));
                mapTemp.put(INPUT_ID_NAME_SIDEB, new Double(0.00));
                missingSide=INPUT_ID_NAME_SIDEB;
                
        }else if((htmlDimensionParameters.get(INPUT_ID_NAME_SIDEB)!=null && Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_SIDEB))!=0) &&
           (htmlDimensionParameters.get(INPUT_ID_NAME_SIDEC)!=null && Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_SIDEC))!=0) &&
           (htmlDimensionParameters.get(INPUT_ID_NAME_ANGLEA)!=null && Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_ANGLEA))!=0)){
                noErrors=true;
                this.methodToUse=UseMethod.SAS;
               
                mapTemp.put(INPUT_ID_NAME_SIDEB, (Double)Math.abs(Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_SIDEB))));
                mapTemp.put(INPUT_ID_NAME_SIDEC, (Double)Math.abs(Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_SIDEC))));
                mapTemp.put(INPUT_ID_NAME_ANGLEA, (Double)Math.abs(Double.parseDouble(htmlDimensionParameters.get(INPUT_ID_NAME_ANGLEA))));
                mapTemp.put(INPUT_ID_NAME_SIDEA, new Double(0.00));
                missingSide=INPUT_ID_NAME_SIDEA;
        }
        
        //Offical ruling
        correct=noErrors;
        
        return correct;
    }
    
    
}
