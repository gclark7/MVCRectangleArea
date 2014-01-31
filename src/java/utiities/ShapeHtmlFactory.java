/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utiities;

import FileManagerSystem.FileManager;
import edu.wctc.distjava.shape.model.Shape;
import edu.wctc.distjava.shape.model.Shape_Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author gcDataTechnology
 */
public class ShapeHtmlFactory {
    
         public static String getHTML(String name) throws IOException,
                                                     InstantiationException,
                                                     ClassNotFoundException,
                                                     IllegalAccessException{
            Properties fileProperties = new Properties();
            FileInputStream inputFile;
            FileManager fm=new FileManager();
            //File fd;
            //File[] f;
         
            //String SEARCH_DIR="MVCRectangleArea" + File.separatorChar+"Source Packages" +File.separatorChar + "edu.wctc.distjava.shape.model/";
             String SEARCH_DIR= "src"+File.separatorChar+"java"+File.separatorChar +"edu"+File.separatorChar+
                    "wctc"+File.separatorChar+"distjava"+
                    File.separatorChar+"shape"+File.separatorChar+"model"+File.separatorChar+"Shape_Rectangle.java";
            
            //String SEARCH_FN="src\\java\\edu\\wctc\\distjava\\shape\\model";
            //fd=new File(SEARCH_DIR);
             fm.setStrRootPath(SEARCH_DIR);
             //f=fm.getFile().listFiles();
             
            String htmlEntities="";
            
            if(name!=null){
            
                //f=fd.listFiles();
                //for(File s:f){
          ////for(int i=0;i<f.length;i++){
                    //if (f1.getName().equals(name)) {
                    //if (s.isFile()&&s.getName().equals(name)) {
          //// if (f[i].isFile()&&f[i].getName().equals(name)) {
                //if(fm.getFile().getName().equals(name)){
                        try {
                            Class clzz;
                            Shape shape=null;
                            String className="";
                            
                            //inputFile = new FileInputStream("MVCRectangleArea" + File.separatorChar+"src"+File.separatorChar+"java"+File.separatorChar+"shapeConfig.properties");
                           // inputFile = new FileInputStream("src"+File.separatorChar+"shapeConfig.properties");
                            
                            //fileProperties.load(inputFile);
                            fileProperties.load(ShapeHtmlFactory.class.getResourceAsStream("shapeConfig.properties"));
                            //inputFile.close();
                            
                            className=fileProperties.getProperty(name);
                            //className=name;
                            clzz= Class.forName(className);
                            shape=(Shape)clzz.newInstance();
                            htmlEntities=shape.getHtmlForShapeSetup();
                            
                            if(htmlEntities==null||htmlEntities.isEmpty()){
                                htmlEntities="<h1>TroubleShoot HTMLFactory</h1>";
                            }
                            
                        }finally{
                            if(htmlEntities==null||htmlEntities.isEmpty()){
                                htmlEntities="<h1>TroubleShoot HTMLFactory</h1>";
                            }
                        }
                    //} //if file found
               // }//for each file
             }//if NOT Empty
                
                
                return htmlEntities;
         }
           
         /**
          * Used to instantiate and return ALL available shapes
          *   MUST have a default constructor
          * 
          * @return Map of Shape Objects
          */
         public static Map<String, String> getShapesAvailable(){
             HashMap m = new HashMap();
             //Shape r= new Shape_Rectangle();
            
             m.put("Shape_Rectangle", "Rectangle");
             m.put("Shape_Circle", "Circle");
             return m;
         }
         
         
         /**
          * Gets a shape from a name that matches a properties variable in the shapeConfig.properties file
          * 
          * @param name String properties file variable
          * @return Shape Object
          * @throws IOException
          * @throws InstantiationException
          * @throws ClassNotFoundException
          * @throws IllegalAccessException 
          */
          public static Shape getShape(String name) throws IOException,
                                                     InstantiationException,
                                                     ClassNotFoundException,
                                                     IllegalAccessException{
            Properties fileProperties = new Properties();
            Shape shape=null;
            
            if(name!=null){
                        try {
                            Class clzz;
                            String className="";
                            
                            //inputFile = new FileInputStream("MVCRectangleArea" + File.separatorChar+"src"+File.separatorChar+"java"+File.separatorChar+"shapeConfig.properties");
                           // inputFile = new FileInputStream("src"+File.separatorChar+"shapeConfig.properties");
                            
                            //fileProperties.load(inputFile);
                            fileProperties.load(ShapeHtmlFactory.class.getResourceAsStream("shapeConfig.properties"));
                            //inputFile.close();
                            
                            className=fileProperties.getProperty(name);
                            //className=name;
                            clzz= Class.forName(className);
                            shape=(Shape)clzz.newInstance();
                           
                            
                            
                        }finally{
                            if(shape==null){
                                shape= new Shape_Rectangle();
                            }
                        }
                    //} //if file found
               // }//for each file
             }//if NOT Empty
                
                
                return shape;
         }
         
         
         
         
         /*******************************************
          * TESTING
          * @param args
          * @throws Exception 
          */
         
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




