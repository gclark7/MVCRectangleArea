/*
 * Shape.java
 *
 * Date: 1/25/2014 
 * Revision: Rev0 
 * Copy write: all rights reserved, may be used for educational purposes and is delivered "As is" with no guarantees for performance
 */

package edu.wctc.distjava.shape.model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author gcDataTechnology
 */
public interface Shape extends Comparable{
    /**
     * True / false indication of a 3 Dimensional Object
     * @return true / false 
     */
    public abstract boolean is3D();
    
    /**
     * Sets the variable is3D to indicate a 3Dimensional Object
     * @param is3D boolean true / false
     * @param height double value for the height of the Object
     */
   
    public abstract void setIs3D(boolean is3D, double height);
    
    /**
     * indicates if the shape has sides to determine round & spherical from rectangle, cylinder, cone and pyramid
     * @return true / false
     */
    public abstract boolean hasSides();
    
    /**
     * Sets the dimensions Map
     * @param dim Map String,Double key value pairs
     */
    public abstract void setDimensions(Map<String,Double> dim);
            
    /***
     * Intended for the developer of the implementing Class to write private helper methods to set dimension variables
     * Generic dimensions getter method for any shape, returning a Map of key value pairs
     * @param dimensions Map of String key, Double value dimensions
     */
    public abstract Map getDimensions();
    
    /**
     * Generic calculation method intended for volume, Area, Diameter and other necessary measurements that can be calculated
     * @return Map String calculation name, Double value
     */
    public abstract Map<String,Double> getCalculatedMeasurments();
    
    /**
     * HTML STEP 1
     * 
     * Designed to write the necessary form elements so user can provide dimensions for Shape Object
     * 
     *  Provides the HTML to be written so the user can provide the necessary dimensions for the Shape
     *  Works with the readHtmlParametersFromShapeSetup() method
     * @return 
     */
    public abstract String getHtmlForShapeSetup();
    
    /**
     * HTML STEP 2
     * 
     * Designed to read the HTML parameters using request.getParameters("elementName") where the element names
     * are defined in the Implementing Shape Class and provided by the getHtmlForShapeSetup() method
     */
    public abstract List<String> getHtmlParametersFromShapeSetup();
    
    /***
     * Used to retrieve ONLY the number parameters for shape setup
     * @return String key value constants for HTML parameters
     */
    public abstract List<String> getHtmlParametersFORShapeSetup();
             
    /**
     * Provides the html entities to display a shape error
     * @return 
     */
    public abstract String getShapeErrorHTML();
    
    /**
     * Intended to check user values to ensure dimension are correct and calculations can be performed
     * @param htmlDimensionParameters Map of request.getParameters() from user setup form
     * @return 
     */
    public abstract boolean correctDimensions(Map<String,String> htmlDimensionParameters);
}
