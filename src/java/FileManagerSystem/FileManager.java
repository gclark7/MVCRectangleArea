/*
 * Final Fall 2013
 */
package FileManagerSystem;

//import gac.parkinggaragepos.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the FileManager utility class for the file system.<br/>
 * It is designed to be portable and independent of the application it is used in.<br/>
 * It handles the input and output of data from and to files.  
 * 
 * @author Greg Clark <gclark7@my.wctc.edu>
 */
public class FileManager implements Comparable{
    
    private String strRootPath="src";
    private File f;
    private File_ReaderText fr;
    private File_WriterText fw;
    
    //public FileManager(File_Reader r, FileWriter w){
     public FileManager(){
         fr=new File_ReaderText();
         fw=new File_WriterText();
         f=new File(strRootPath);
    }
     
     /**
      * Delegates to the FileReader.  
      * Reads a file and returns a Map of the file data
      * @param f File to read
      * @return Map of file data
      * @throws IOException back to the calling method
      */
     public Map ReadFile(File f) throws IOException{
         return fr.readFile(f);
     }
     /**
      * Delegates to the FileWriter.  
      * Creates a file or if not checked will overwrite an existing file.
      * @param content String value to write to file
      * @param path String path to the file directory
      * @param fileName String file name including extension
      * @throws IOException back to the calling method
      */
     public void createInitialFile(String content, String path, String fileName) throws IOException{
         fw.createInitialFile(content, path, fileName);
     }
     
     /**
      * Delegates to the File Writer.  
      * Writes a String value to a file
      * @param content String value to be saved in a file
      * @param path String path of the file directory
      * @param fileName String name of the file including extension
      * @throws IOException back to the calling method
      */
     public void writeRecordToFile(String content, String path, String fileName) throws IOException{
         fw.writeRecordToFile(content, path, fileName);
     }
         
     /**
      * Delegates to the FileWriter.  
      * Returns a String value of the file data to be presented in a text area of a GUI control
      * @param path String of the directory path including the file
      * @return String of the file data
      * @throws IOException to the calling method
      */
     public String printToTextArea(String path) throws IOException{
         File f=new File(path);
         Map m = new LinkedHashMap(fr.readFile(f));
         return fw.printToTextArea(m);
     }
     
     /**
      * Reads in the String from a text area or any String and writes to file<br/>
      * Uses the String '"\n"' as a delimiter for each line as a record
      * @param s String provided to be mapped and written to file
      */
     public void writeToFile(String s, String fileName) throws IOException{
            fw.writeFile(fr.readFromText(s), strRootPath, fileName);
     }

     /**
      * Stored String value of the root directory
      * @return String path of the root directory
      */
    public String getStrRootPath() {
        return strRootPath;
    }

    /**
     * Sets the root directory
     * @param strRootPath String path for root directory
     */
    public void setStrRootPath(String strRootPath) {
        this.strRootPath = strRootPath;
        f= new File(strRootPath);
    }

    /**
     * Gets the File_ReaderText Object used by the FileManager
     * @return File_ReaderText Object
     */
    public File_ReaderText getFr() {
        return fr;
    }

    /**
     * Sets the File_ReaderText Object used by the FileManager to read files
     * @param fr File_ReaderText Object
     */
    public void setFr(File_ReaderText fr) {
        this.fr = fr;
    }

    /**
     * Gets the File_WriterText Object used by the FileManager to write files
     * @return File_WriterText Object
     */
    public File_WriterText getFw() {
        return fw;
    }

    /**
     * Sets the File_WriterText Object used by the FileManager
     * @param fw File_WriterText
     */
    public void setFw(File_WriterText fw) {
        this.fw = fw;
    }
     
     /**
      * File stored in the FileManager to be read or written
      * @return File stored in the FileManager
      */
    public File getFile(){
        
        return f;
    }

    /**
     * Used for comparison purposes
     * @return the hash code integer of the Object
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.strRootPath);
        hash = 47 * hash + Objects.hashCode(this.fr);
        hash = 47 * hash + Objects.hashCode(this.fw);
        return hash;
    }

    /**
     * Used for comparison purposes <br/>
     * Compares FilePath, Writer & Reader
     * @param obj Object to compare
     * @return True of False
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileManager other = (FileManager) obj;
        if (!Objects.equals(this.strRootPath, other.strRootPath)) {
            return false;
        }
        if (!Objects.equals(this.fr, other.fr)) {
            return false;
        }
        if (!Objects.equals(this.fw, other.fw)) {
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
        return  strRootPath + " " + fr + " " + fw;
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
