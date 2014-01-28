/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utiities;

import edu.wctc.distjava.shape.model.Shape;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author gcDataTechnology
 */
public class ShapeHtmlFactory {
    
    //examples
   
    
    
    
    
    
//        try {
//            inputFile = new FileInputStream(file);
//            fileProperties.load(inputFile);
//            inputFile.close();
//    
//    ParkingFacilityStrategy p=null;
//                String className = fileProperties.getProperty(PARKING_FACILITY+i);
//                Class clss = Class.forName(className);
//                p = (ParkingFacilityStrategy)clss.newInstance();
//                
//            }
        
        
        
         
         public static String getHTML(String name){
            Properties fileProperties = new Properties();
            FileInputStream inputFile;
         
            String SEARCH_DIR="edu.wct.distjava.shape.model";
            File fd=new File(SEARCH_DIR);
            
            String htmlEntities="";
            Class clzz;
            
            Shape shape=null;
            String className="";
        
                        
                for(File f:fd.listFiles()){
                    if(f.getName().equals(name)){
                                               
                        try {
                            inputFile = new FileInputStream("src"+File.separatorChar+"shapeConfig.properties");
                            fileProperties.load(inputFile);
                            inputFile.close();
                            
                            className=fileProperties.getProperty(name);
                            clzz= Class.forName(className);
                            shape=(Shape)clzz.newInstance();
                            htmlEntities=shape.getHtmlForShapeSetup();
                        }catch(Exception e){}
                       
                    }//if file found
                }//for each file
                
                
                return htmlEntities;
         }
           
         
         
}
