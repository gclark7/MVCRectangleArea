/*
 *
 * Final Fall 2013
 */
package FileManagerSystem;

//import gac.parkinggaragepos.*;
import java.util.List;
import java.util.Map;

/**
 *  Interface for FileManger delimiter converters
 * 
 * @author Greg Clark <gclark7@my.wctc.edu>
 */
public interface FileConverterDelimit {
    
    /**
     * Decodes Map of data to String format for display in TextArea
     * @param m Map of data
     * @return String of data
     */
    public abstract String decodeToText(Map m);

    /**
     * Encodes String data into Mapped values
     * @param txt String of data to Map
     * @return Mapped of data
     */
    public abstract Map encodeFromText(String txt);

    //Getters & Setters
    /**
     * Character used to comment data in a file
     * @return comment character
     */
    public abstract char getComment();

    /**
     * Character used as delimeter between values
     * @return delimeter character
     */
    public abstract char getDelimiter();

    /**
     * String prefix for Map keys
     * @return prefix String used for Map keys
     */
    public abstract String getRec();

    /**
     * Integer used to determine number of records to work with from a file or a Map
     * @return integer count of records
     */
    public abstract int getRecNum();

    /**
     * Takes a List of Strings and Maps the data according to the delimiters.  Anticipated uses are to append any record in a file.<br/>
     * Append any value, or any line according to the Mapped data keys and values.
     * @param fileData List of Strings
     * @return Map<String,Map<String,String>> <RecordKey<RecordKey,DataValue>>
     *
     */
    public abstract Map<String, Map<String, String>> mapFileData(List<String> fileData);
    /**
     * Takes a List of line data and formats it for a text area GUI control
     * @param fileData List of line records
     * @return Map of line records
     */
    public abstract Map<String,Map<String,String>> mapFileDataForText(List<String> fileData);

    /**
     * Set the comment delimiter
     * @param comment delimit char used for comments in a file
     */
    public abstract void setComment(char comment);

    /**
     * Set the Delimiter char used to separate values
     * @param delimiter character used to separate values in the file
     */
    public abstract void setDelimiter(char delimiter);

    /**
     * Set the Map key prefix to find the record once it is mapped
     * @param rec key prefix for Mapped Data
     */
    public abstract void setRec(String rec);

    /**
     * Original use was to check and record the number of records (lines) in a file
     * @param recNum set the records to be checked in a file
     */
    public abstract void setRecNum(int recNum);
    
}
