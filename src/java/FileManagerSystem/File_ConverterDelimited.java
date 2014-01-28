/*
 * 
 * Final Fall 2013
 */
package FileManagerSystem;

//import gac.parkinggaragepos.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * File converter for delimited files
 * 
 * @author Greg Clark <gclark7@my.wctc.edu>
 */
public class File_ConverterDelimited implements Comparable,
                                                FileConverterDelimit{
    
     private char comment=35;//avoid config comments
     private char delimiter=44;//variables and values are separated by a single space
     private final char SPACE=32;
     private String rec="Rec";
     private int recNum=0;
    /**
     * Default Constructor
     */
    public File_ConverterDelimited(){
        
    }
    
    /**
     * Takes a List of line data and formats it for a text area GUI control
     * @param fileData List of line records
     * @return Map of line records
     */
    @Override
    public Map<String,Map<String,String>> mapFileDataForText(List<String> fileData){
        Map m = new LinkedHashMap();
        int i=0;
        for(String s:fileData){
           
            m.put(rec+i, s);
             i++;
        }
        return m;
    }
    
    /**
     * Takes a List of Strings and Maps the data according to the delimiters.  Anticipated uses are to append any record in a file.<br/>
     * Append any value, or any line according to the Mapped data keys and values.
     * @param fileData List of Strings
     * @return Map<String,Map<String,String>> <RecordKey<RecordKey,DataValue>>
     * 
     */
    @Override
    public Map<String,Map<String,String>> mapFileData(List<String> fileData){
        Map<String,Map<String,String>> dataMap = new HashMap<String,Map<String,String>>();
        Map<String,String> valuesMap= new HashMap<String,String>();
        int recSize=fileData.size();
        String rec="Rec";
        
        if(!fileData.isEmpty()){
            int headerCount=0;
            //iterate over the first line in the list to find field count
            String lineOne=fileData.get(0);
            for(int i=0;i<lineOne.length();i++){
                if(lineOne.charAt(i)==delimiter){
                    headerCount++;
                }
            }
            //create the headers to avoid iterating over the first line again and again
            String[] headerFields = new String[headerCount+1];
            
            char[] c=lineOne.toCharArray();
            //loop through c
            //place values into headerFields
            int x=0;
            String value="";
            for(int i=0;i<c.length;i++){
               if(c[i]==delimiter){
                   headerFields[x]=value;
                   x++;
                   value="";
               }else{value+=c[i];}
                
            }
            //place the last value into headerFields[] because value after last comma is ignored prior to this
            headerFields[x]=value;
            value="";
            x=0;
            
            //map the data starting on 1st line of data values
            for(int i=1;i<recSize;i++){
                c=fileData.get(i).toCharArray();
                for(int j=0;j<c.length;j++){
                    if(c[j]==delimiter){
                        valuesMap.put(headerFields[x], value);
                        x++;
                        value="";
                    }else{value+=c[j];}
                
                }
                //put the last value into valuesMap then reset variables
                valuesMap.put(headerFields[x], value);
                value="";
                x=0;
                dataMap.put(rec+i, valuesMap);
            }
            
        }
        
        return dataMap;
    }
    
    /**
     * Decodes a Map of data to a String Object
     * @param m Map of data values
     * @return String of data
     */
    @Override
    public String decodeToText(Map m){
        String s="";
           Map<String,Map> mapped = m;
           for(Object md:mapped.values()){
                Map toFile = (Map)md; 
                for(Object data:toFile.keySet()){
                    //System.out.println(toFile.get(data).toString());
                    s+=data.toString() +SPACE+ toFile.get(data)+ "\n";
                    //writer.write(toFile.get(data).toString());
                    //writer.write("\n");
                    //writer.print(toFile.get(data));
                    //writer.print(s);
                    //writer.print("\n");
                } 
           }
           return s;
    }
    
    /**
     * Encodes a String to a Map of values
     * @param txt String of data values to be Mapped
     * @return Mapped values
     */
    @Override
    public Map encodeFromText(String txt){
         List list=new ArrayList();
          String nl="\n";
          int foundNL=txt.indexOf(nl);
          int lastNL=txt.lastIndexOf(nl);
          int x=0;
          System.out.println("From FIle_REaderText: readFromText()" +x +" "+foundNL + " "+ lastNL);
          String s=txt.substring(x, foundNL);//first line
          list.add(s);
//          System.out.println(s);
          
          while(foundNL<lastNL){
              x=foundNL+1;
              foundNL=txt.indexOf(nl, x);
              
              //txt.substring(foundNL, foundNL)
              System.out.println("From FIle_REaderText: readFromText " + x +" " + foundNL);
              s=txt.substring(x,foundNL);
              list.add(s);
          }
          if(foundNL<=lastNL && lastNL<txt.length()-1){
            s=txt.substring(lastNL, txt.length());//Last line
            list.add(s);
          }
          
          return encodeDataForFile(list);
    }
    
    private Map encodeDataForFile(List list){
        Map m=null;
        
        return m;
    }
    
    //Getters & Setters
    /**
     * Gets the character used for comments in a file
     * @return comment character
     */
    @Override
    public char getComment() {
        return comment;
    }
    /**
     * Sets the comment character used in files
     * @param comment character to comment lines in a file
     */
    @Override
    public void setComment(char comment) {
        this.comment = comment;
    }
    /**
     * Gets the delimiter character used to separate values in a file
     * @return delimiter character
     */
    @Override
    public char getDelimiter() {
        return delimiter;
    }
    /**
     * Sets the delimiter character used to separate values in a file
     * @param delimiter character
     */
    @Override
    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }
    /**
     * String prefix for Map keys
     * @return String prefix for map keys
     */
    @Override
    public String getRec() {
        return rec;
    }
    /**
     * Sets the String prefix used for Map keys
     * @param rec String prefix used for Map keys
     */
    @Override
    public void setRec(String rec) {
        this.rec = rec;
    }
    /**
     * int number of records to work with in a file or Map
     * @return int number of records
     */
    @Override
    public int getRecNum() {
        return recNum;
    }
    /**
     * sets the number of records to work with in a file or Map
     * @param recNum int number of records
     */
    @Override
    public void setRecNum(int recNum) {
        this.recNum = recNum;
    }
    
 
     /**
     * Used for comparison purposes
     * @return hash code integer
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.comment;
        hash = 53 * hash + this.delimiter;
        hash = 53 * hash + Objects.hashCode(this.rec);
        return hash;
    }

    /**
     * Used for comparison purposes
     * @param obj Object to compare
     * @return true / false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final File_ConverterDelimited other = (File_ConverterDelimited) obj;
        if (this.comment != other.comment) {
            return false;
        }
        if (this.delimiter != other.delimiter) {
            return false;
        }
        if (!Objects.equals(this.rec, other.rec)) {
            return false;
        }
        return true;
    }

    /**
     * String representation of the Object
     * @return String description of the Object
     */
    @Override
    public String toString() {
        return  comment + " " + delimiter + " " + rec ;
    }

    
    /**
     * Must override to correctly compare
     * @param o Object to compare with
     * @return int value -1 if before, 0 if same, 1 if after
     */
    @Override
    public int compareTo(Object o) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int compare=0;
        if(!FileManagerUtilities.isNull(o)){
            compare=this.toString().compareTo(o.toString());
        }
        
        return compare;
    }
}
