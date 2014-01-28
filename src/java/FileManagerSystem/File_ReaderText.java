/*
 * Final Fall 2013
 */
package FileManagerSystem;

//import gac.parkinggaragepos.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This class is part of the FileManagement System.  It is designed to read from a text file.<Br/>
 * It uses a converter to encode and decode files.
 * @author Greg Clark <gclark7@my.wctc.edu>
 */
public class File_ReaderText implements Comparable{
    
    private final String MSG_NULL="Received Null Value from user";
    private final String MSG_FILE_PROBLEM="Problem! Cannot read file yo";
    private final String MSG_FILE_CLOSE_PROBLEM="Cannot Close File...May not have been opened or it has been moved.";
    private FileConverterDelimit fc;
    
    /**
     * Default constructor
     */
    public File_ReaderText(){
        fc=new File_ConverterReport();
    }
    
    /**
     * returns Map for the file data
     * @param f provide the File to read
     * @param fc provide the File_Converter to use: Delimited, Config, Custom_Specified
     * @return Map of Maps for the file data
     * 
     */
//    public final Map<String,Map<String,String>> readFile(File f, File_Converter fc) throws IOException{
     public final Map<String,Map<String,String>> readFile(File f) throws IOException{
        //Map<String,Map<String,String>> m=new LinkedHashMap<String,Map<String,String>>();//Base Map for converter
         
        BufferedReader fr=null;
        List fileRecordList= new ArrayList();
                try{
           fr= new BufferedReader(new FileReader(f));
           String line = fr.readLine();
           
           while(line!=null){//read file
               //Map record
               fileRecordList.add(line);
               //m.put(Integer.toString(i),fc.mapFile(line));
               line=fr.readLine();//need to continue reading file
              // System.out.println(this.getClass() + "readFile() Texting**********" + line);
           }
       } catch(IOException ioError){
          throw new IOException(this.getClass() + " readFile("+f+") " + MSG_FILE_PROBLEM + ioError);
        } finally {
            try {
                fr.close();
            } catch(Exception e) {
                throw new IOException(this.getClass() + " readFile("+f+") " +MSG_FILE_CLOSE_PROBLEM + e);
            }
        }
        return fc.mapFileDataForText(fileRecordList);
    }
     
     /**
      * Uses the "'\n'" String to delimit text into an ArrayList
      * @param txt Complete String of the text to be mapped
      * @return Map<String,Map<String,String>> <recordNumber<dataKey,recordData>>
      */
     public final Map<String,Map<String,String>> readFromText(String txt){
//      public final Map<String,Map<String,String>> readFromText(String txt, FileConverter fc){
         return fc.encodeFromText(txt);
        
      }

     /**
      * String representation of the Object
      * @return String description of the Object
      */
    @Override
    public String toString() {
        return  fc.toString();
    }

     
     /**
      * Used for comparison purposes
      * @return hash code integer
      */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.fc);
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
        final File_ReaderText other = (File_ReaderText) obj;
        if (!Objects.equals(this.fc, other.fc)) {
            return false;
        }
        return true;
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
