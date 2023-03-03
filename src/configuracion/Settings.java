/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuracion;

/**
 *
 * @author Dell
 */
import java.io.*;
import java.util.Properties;
import java.util.logging.*;
public class Settings {
    
    private static Logger logger =Logger.getLogger(configuracion.Settings.class.getName());
    
    public static String Get(String name,String defVal){
        File configFile = new File(Variables.SETTINGS_FILE);
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);
            reader.close();
            return props.getProperty(name);
        } catch (FileNotFoundException ex) {
            // file does not exist
            logger.log(Level.SEVERE,"file does not exist",ex);
            return defVal;
        } catch (IOException ex) {
            // I/O error
            logger.log(Level.SEVERE,"I/O error",ex);
            return defVal;
        } catch (Exception ex){
            logger.log(Level.SEVERE,"Your error message",ex);
            return defVal;
        }
    }
    public static Integer Get(String name,Integer defVal){
        File configFile = new File(Variables.SETTINGS_FILE);
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);
            reader.close();
            return Integer.valueOf(props.getProperty(name));
        } catch (FileNotFoundException ex) {
            // file does not exist
            logger.log(Level.SEVERE,"file does not exist",ex);
            return defVal;
        } catch (IOException ex) {
            // I/O error
            logger.log(Level.SEVERE,"I/O error",ex);
            return defVal;
        } catch (Exception ex){
            logger.log(Level.SEVERE,"Your error message",ex);
            return defVal;
        }
    }
    public static Boolean Get(String name,Boolean defVal){
        File configFile = new File(Variables.SETTINGS_FILE);
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);
            reader.close();
            return Boolean.valueOf(props.getProperty(name));
        } catch (FileNotFoundException ex) {
            // file does not exist
            logger.log(Level.SEVERE,"file does not exist",ex);
            return defVal;
        } catch (IOException ex) {
            // I/O error
            logger.log(Level.SEVERE,"I/O error",ex);
            return defVal;
        } catch (Exception ex){
            logger.log(Level.SEVERE,"Your error message",ex);
            return defVal;
        }
    }
    public static void Set(String name, String value){
        File configFile = new File(Variables.SETTINGS_FILE);
        try {
            Properties props = new Properties();
            FileReader reader = new FileReader(configFile);
            props.load(reader);
            props.setProperty(name, value.toString());
            FileWriter writer = new FileWriter(configFile);
            props.store(writer, Variables.SETTINGS_COMMENT);
            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
            logger.log(Level.SEVERE,"file does not exist",ex);
        } catch (IOException ex) {
            // I/O error
            logger.log(Level.SEVERE,"I/O error",ex);
        } catch (Exception ex){
            logger.log(Level.SEVERE,"Your error message",ex);
        }
    }
    public static void Set(String name, Integer value){
        File configFile = new File(Variables.SETTINGS_FILE);
        try {
            Properties props = new Properties();
            FileReader reader = new FileReader(configFile);
            props.load(reader);
            props.setProperty(name, value.toString());
            FileWriter writer = new FileWriter(configFile);
            props.store(writer,Variables.SETTINGS_COMMENT);
            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
            logger.log(Level.SEVERE,"file does not exist",ex);
        } catch (IOException ex) {
            // I/O error
            logger.log(Level.SEVERE,"I/O error",ex);
        } catch (Exception ex){
            logger.log(Level.SEVERE,"Your error message",ex);
        }
    }
    public static void Set(String name, Boolean value){
        File configFile = new File(Variables.SETTINGS_FILE);
        try {
            Properties props = new Properties();
            FileReader reader = new FileReader(configFile);
            props.load(reader);
            props.setProperty(name, value.toString());
            FileWriter writer = new FileWriter(configFile);
            props.store(writer,Variables.SETTINGS_COMMENT);
            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
            logger.log(Level.SEVERE,"file does not exist",ex);
        } catch (IOException ex) {
            // I/O error
            logger.log(Level.SEVERE,"I/O error",ex);
        } catch (Exception ex){
            logger.log(Level.SEVERE,"Your error message",ex);
        }
    }
}
