/*
 * Midterm FAll 2013
 * Final FALL 2013
 */
package FileManagerSystem;

//import gac.parkinggaragepos.*;
import java.util.Objects;

/**
 * Utilities class for common error checking and operations
 * Examples: checks for null values, 0-length, array expansion and collapse
 * 
 * Would like to develop this more in the future
 * 
 * @author Greg Clark <gclark7@my.wctc.edu>
 */
public class FileManagerUtilities implements Comparable{
   /**
    * intentionally public static attribute
    * used to ensure single instance of GarageUtilities
    */    
    public static FileManagerUtilities instance;
    public static final String CUSTOM_EXCEPTION_COMPARABLE="Not Comparable";
    public static final String CUSTOM_EXCEPTION_NOFILE="File not found or does not exist";
    public static final String CUSTOM_EXCEPTION_NULL="Null value received...No information to show";
    private Class iClass;//for equals, toString and hashcode...No public setter -readonly
    private final String MSG_STRING="Utilities Class= ";
    
    
    /**
     * creates a single instance of GarageUtilities
     * @return a single instance of the GarageUtility
     */
    public final FileManagerUtilities getInstance(){
         if(instance==null){
            instance=new FileManagerUtilities();
            iClass=instance.getClass();
        }
         
         return instance;
    }
    
    /**
     * private constructor forces single instantiation
     */
    private FileManagerUtilities(){}
    
    /**
     * Checks Object for null
     * @param o Object to check
     * @return boolean true / false
     */
    public static final boolean isNull(Object o){
        boolean isNull=true;
        if(o==null){
            isNull=true;
        }
        else{isNull=false;}
        
        return isNull;
    }
    
    /**
     * checks for null values in an object array
     * @param o Object array to check for a null value
     * @return boolean true /false
     */
     public static final boolean hasNull(Object[] o){
        boolean isNull=false;
        for(int i=0;i<o.length;i++){
            //only 1 needs to be null to be bad
            if(o[i]==null || o.length==0){
                isNull=true;
            }
        }
        return isNull;
    }
    
     /**
      * Expands an array by 1 index
      * This is intended to reduce redundant code - DRY<br/>
      * 
      * Array is ONLY expanded - no new values are added
      * May have to remove...Not working at this time, returning array is Object[], not original Objects[]<br/>
      * 
      * Would like to develop this in the future as we learn about Generics 
      * @param o object array to increase in size
      * @return object array 1 index larger - preserves original array objects
      */
     public static final Object[] expandObjectArray(Object[] o){
         int expand=o.length + 1;
         int index=0;
         
         Object[] temp = new Object[expand];
         System.arraycopy(o, index, temp, index, o.length);
         o=temp;
         
         return o;
     }

    
     /**
      * Compares the GarageUtilities Object to other Objects
      * @param oC Other Object to compare with
      * @return int value -1 if before, 0 if same, 1 if after
      */
    @Override
    public int compareTo(Object oC) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        final int BEFORE = -1;
	final int SAME = 0;
	final int AFTER = 1;
        int compare=-1;
        FileManagerUtilities o = (FileManagerUtilities)oC;
	 
	 if(this.getiClass() == o.getiClass()) {
		compare = SAME;
	 } else {
             compare = this.getiClass().toString().compareTo(o.getiClass().toString());
	 }
	 
	 return compare;
        
    }

    /**
     * Must be overridden for comparison purposes
     * @return hash integer
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.iClass);
        return hash;
    }

    /**
     * Must be overridden for comparison purposes
     * @param obj Object to be compared with
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileManagerUtilities other = (FileManagerUtilities) obj;
        if (!Objects.equals(this.iClass, other.iClass)) {
            return false;
        }
        return true;
    }

    /**
     * String representation of the Object
     * @return String descriptor of this Object
     */
    @Override
    public String toString() {
        return MSG_STRING + iClass;
    }

    /**
     * getter for Custom exception messages
     * @return String message for custom exception
     */
    public static String getCUSTOM_EXCEPTION_COMPARABLE() {
        return CUSTOM_EXCEPTION_COMPARABLE;
    }

    /**
     * getter for Custom exception messages
     * @return String message for custom exception
     */
    public static String getCUSTOM_EXCEPTION_NOFILE() {
        return CUSTOM_EXCEPTION_NOFILE;
    }

    /**
     * helper method: getter for the iClass variable used to compare Objects
     * @return GarageUtilities Class 
     */
    private Class getiClass() {
        return iClass;
    }
     
     /**
      * Testing purposes
      * @param args command line arguments
      */
     public static void main(String[] args) {
        FileManagerUtilities g = new FileManagerUtilities();
        
    }
     
}
