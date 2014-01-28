/*
 * Final Fall 2013
 */
package FileManagerSystem;

//import gac.parkinggaragepos.*;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

/**
 * File_WriterText is designed to work with the FileManager to write data from files and to files
 * 
 * @author Greg Clark <gclark7@my.wctc.edu>
 */
public class File_WriterText implements Comparable{
     private String MSG_NULL="Null or empty value was given.";
     private FileConverterDelimit fc=new File_ConverterReport();
 
     
    /**
     * Default Constructor
     */
    public File_WriterText(){
        
    }
    
    /**
     * Writes a Map of data values to a file
     * @param m Map of data records
     * @param path String path of file Directory
     * @param fileName String file name
     * @throws IOException back to the calling method
     */
    public void writeFile(Map m, String path, String fileName) throws IOException{
        
        if(m.isEmpty()|| path.isEmpty() || fileName.isEmpty()){
            throw new UnsupportedOperationException(this.getClass() + " writeFile() " +MSG_NULL);
        }else{
            File f = new File(path+fileName);
           PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(f, false)));
           String s="";
           Map<String,Map> mapped = m;
           for(Object md:mapped.values()){
                Map toFile = (Map)md; 
                for(Object data:toFile.keySet()){
                    //System.out.println("From writeFile()" + data.toString() + " " + toFile.get(data).toString());
                    s=data.toString() + toFile.get(data);
                    //writer.write(toFile.get(data).toString());
                    //writer.write("\n");
                    //writer.print(toFile.get(data));
                    writer.print(s);
                    writer.print("\n");
                } 
                writer.close();
           }
        }
    }

    
    /**
     * Returns a String value from Mapped data
     * @param m Map of data to write to a String
     * @return String of data values
     */
    public String printToTextArea(Map m){
           return fc.decodeToText(m);
    }
    
    /**
     * Creates a file that does not exist...Will overwrite an existing file with provided content
     * @param content String value to write to file
     * @param path String path for the file directory
     * @param fileName String name of the file to create
     * @throws IOException back to the calling method
     */
    public void createInitialFile(String content, String path, String fileName) throws IOException{
        File f = new File(path+File.separatorChar+fileName);
           PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(f, false)));
           writer.print(content);
           writer.close();
    }
    
    /**
     * Appends a single String record to a file
     * @param content String record with values separated by delimeter
     * @param path String path to the file directory
     * @param fileName String file name with extension to be appended
     * @throws IOException back to the calling method
     */
    public void writeRecordToFile(String content, String path, String fileName) throws IOException{
        File f = new File(path+File.separatorChar+fileName);
        PrintWriter writer=null;
        try{
           writer = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));
           writer.print(content);
        }catch(IOException e){
            throw new UnsupportedOperationException(this.getClass() + "writer.print " +e);
        }finally{
            try{
                writer.close();
            }catch(Exception e){
                throw new UnsupportedOperationException(this.getClass() + " writer.close" +e);
            }
        }
    }

    /**
     * Used for comparison purposes
     * 
     * @return hash code integer 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.fc);
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
        final File_WriterText other = (File_WriterText) obj;
        if (!Objects.equals(this.fc, other.fc)) {
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
        return this.getClass().toString()+" " + fc ;
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
