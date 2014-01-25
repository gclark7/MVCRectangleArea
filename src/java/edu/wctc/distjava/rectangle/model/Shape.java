/*
 * Shape.java
 *
 * Date: 1/25/2014 
 * Revision: Rev0 
 * Copy write: all rights reserved, may be used for educational purposes and is delivered "As is" with no guarantees for performance
 */

package edu.wctc.distjava.rectangle.model;

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
    
    /***
     * Intended for the developer of the implementing Class to write private helper methods to set dimension variables
     * Generic dimensions getter method for any shape, returning a Map of key value pairs
     * @param dimensions Map of String key, Double value dimensions
     */
    public abstract Map getDimension();
    
    /**
     * Generic calculation method intended for volume, Area, Diameter and other necessary measurements that can be calculated
     * @return Map String calculation name, Double value
     */
    public abstract Map<String,Double> getCalculatedMeasurments();
}
