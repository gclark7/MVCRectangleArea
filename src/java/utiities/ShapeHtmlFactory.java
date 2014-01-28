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
         
            //String SEARCH_DIR="MVCRectangleArea" + File.separatorChar+"Source Packages" +File.separatorChar + "edu.wctc.distjava.shape.model/";
             String SEARCH_DIR= "src"+File.separatorChar+"java"+File.separatorChar +"edu"+File.separatorChar+
                    "wctc"+File.separatorChar+"distjava"+
                    File.separatorChar+"shape"+File.separatorChar+"model";
            
            //String SEARCH_FN="src\\java\\edu\\wctc\\distjava\\shape\\model";
            File fd=new File(SEARCH_DIR);
             
             
            String htmlEntities="";
            
            if(name!=null){
            
                File[] f=fd.listFiles();
                for(File s:f){
                    //for(int i=0;i<f.length;i++){
                    //if (f1.getName().equals(name)) {
                    if (s.isFile()&&s.getName().equals(name)) {
                        try {
                            Class clzz;
                            Shape shape=null;
                            String className="";
                            
                            inputFile = new FileInputStream("src"+File.separatorChar+"java"+File.separatorChar+"shapeConfig.properties");
                            fileProperties.load(inputFile);
                            inputFile.close();
                            
                            className=fileProperties.getProperty(name);
                            clzz= Class.forName(className);
                            shape=(Shape)clzz.newInstance();
                            htmlEntities=shape.getHtmlForShapeSetup();
                        }catch(Exception e){}
                    } //if file found
                }//for each file
             }//if NOT Empty
                
                
                return htmlEntities;
         }
           
         
         
         
         public static void main(String[] args) throws Exception{
   

             //String SEARCH_DIR= "src/java";
            String SEARCH_DIR= "src"+File.separatorChar+"java"+File.separatorChar +"edu"+File.separatorChar+
                    "wctc"+File.separatorChar+"distjava"+
                    File.separatorChar+"shape"+File.separatorChar+"model";
            
             File fd=new File(SEARCH_DIR);
             
             
            String htmlEntities="";
            
           
            File[] f=fd.listFiles();
            for(File s:f){
                System.out.println(s.getName());
                System.out.println(s.getName().equals("Shape_Rectangle.java"));
            }
}
         
}




