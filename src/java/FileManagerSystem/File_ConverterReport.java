/*
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
 * File converter for report files
 * The Converters are designed to work with Readers and Writers for File Input and Output.<br/>
 * The Converters work to encode and decode file data and string values into Mapped data for text output<br/>
 * and back again for file writing.
 * 
 * @author Greg Clark <gclark7@my.wctc.edu>
 */
public class File_ConverterReport implements FileConverterDelimit,
                                             Comparable{
    
     private char comment=35;//avoid config comments
     private char delimiter=44;//variables and values are separated by a ','
     private final char SPACE=32;
     private String rec="Rec";
     private int recNum=0;
    /**
     * Default Constructor
     */
    public File_ConverterReport(){
        
    }
    
    /**
     * Takes a List of line data and formats it for a text area GUI control
     * @param fileData List of line records
     * @return Map of line records
     */
    @Override
    public Map mapFileDataForText(List<String> fileData){
        //Map<String,String> m = new LinkedHashMap<String,String>();
        Map<String,String> ma = new LinkedHashMap<String,String>();
       
        for( int i=0;i<fileData.size();i++){
          // for(String )
            ma.put(rec+i, fileData.get(i));
            //System.out.println(fileData.get(i));
           // m.put(rec+i, ma);
             
        }
        /*//prints all records once to the console...That was the goal.
         for(String a:m.keySet()){
            System.out.println(a + " From converterReport ") ;
        }
        * */
        return ma;
    }
    
    /**
     * Decodes the Map of data to a String value.  Typical use is to write to a text field.
     * @param m Map of data 
     * @return String of data values separated by the delimiter
     */
    @Override
    public String decodeToText(Map m){
        String s="";
           //LinkedHashMap<String,LinkedHashMap> mapped = (LinkedHashMap)m;
        for(Object a:m.keySet()){
           //for(String md:mapped.keySet()){
               s+=m.get(a)+"\n";
                //Map<String,String> toPrint = (Map)md; 
               //for (Object k:mapped.get(md).keySet()){
                //for(Object data:){
                    //System.out.println(toFile.get(data).toString());
                    //s+=data.toString()+"\n";
                   
                    //s+=mapped.get(md).get(k)+"\n";//was using this until 12/16/2013 duplicate results
                    
                    //s+=data.toString() +SPACE+ toFile.get(data)+ "\n";//producing redundant results
                    
                    
                    //writer.write(toFile.get(data).toString());
                    //writer.write("\n");
                    //writer.print(toFile.get(data));
                    //writer.print(s);
                    //writer.print("\n");
               // } 
           }
           
           //System.out.println(s);
           return s;
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
       
        
        //System.out.println(this.getClass() + " " +dataMap);//testing file not being written
        return dataMap;
    }
    
    
    
    /**
     * Encodes String value to a Map of data records.<br/>
     * Uses the '\n' String to separate records from each other.  Uses the delimiter to separate values.
     * @param txt String value to Map
     * @return Map of records
     */
    @Override
    public Map encodeFromText(String txt){
         List list=new ArrayList();
          String nl="\n";
          int foundNL=txt.indexOf(nl);
          int lastNL=txt.lastIndexOf(nl);
          int x=0;
          //System.out.println("From FIle_REaderText: readFromText()" +x +" "+foundNL + " "+ lastNL);
          String s=txt.substring(x, foundNL);//first line
          list.add(s);
//          System.out.println(s);
          
          while(foundNL<lastNL){
              x=foundNL+1;
              foundNL=txt.indexOf(nl, x);
              
              //txt.substring(foundNL, foundNL)
              //System.out.println("From FIle_REaderText: readFromText " + x +" " + foundNL);
              s=txt.substring(x,foundNL);
              list.add(s);
          }
          if(foundNL<=lastNL && lastNL<txt.length()-1){
            s=txt.substring(lastNL, txt.length());//Last line
            list.add(s);
          }
          //System.out.println("");
          return encodeDataForFile(list);
    }
    
    private Map encodeDataForFile(List list){
        Map m=new HashMap();
        int i=0;
        for(Object l:list){
            m.put(rec+i, l);
           // System.out.println(l);
            i++;
        }
            
        return m;
    }
    
    //Getters & Setters

    /**
     * Getter for the comment character of a file
     * @return character value for comments
     */
    @Override
    public char getComment() {
        return comment;
    }

    /**
     * Set character used to make comments in a file
     * @param comment char indicating a comment sentence
     */
    @Override
    public void setComment(char comment) {
        this.comment = comment;
    }

    /**
     * Get the delimiter for the record separator
     * @return delimiter used to separate records in a file
     */
    @Override
    public char getDelimiter() {
        return delimiter;
    }

    /**
     * Set the delimiter for the record separator
     * @param delimiter record separator
     */
    @Override
    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Get the leading String for Map keys
     * @return prefix for Map keys
     */
    @Override
    public String getRec() {
        return rec;
    }

    /**
     * Set the leading string value for Map keys.  This system adds an incremented value to the recNum
     * @param rec prefix for the Map key
     */
    @Override
    public void setRec(String rec) {
        this.rec = rec;
    }

    /**
     * Getter for the recNum number of records in a file
     * @return number of records
     */
    @Override
    public int getRecNum() {
        return recNum;
    }

    /**
     * Set the number of records to search for or work with.  Also used to find the number of records in a file.
     * @param recNum number of records
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
        final File_ConverterReport other = (File_ConverterReport) obj;
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
