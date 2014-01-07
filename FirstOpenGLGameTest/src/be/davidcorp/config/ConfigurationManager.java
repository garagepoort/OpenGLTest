package be.davidcorp.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

	private static final String IMAGE_LOCATION_PROPERTIES = "imageLocationProperties";

	public static String imageLocationPropertyFile;
	
	public static void importProperties(){
		Properties prop = new Properties();
		 
    	try {
    		prop.load(new FileInputStream("resources/configuration/application.properties"));
            imageLocationPropertyFile = prop.getProperty(IMAGE_LOCATION_PROPERTIES);
            
            ImageLocationManager.initializeProperties();
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}
	
	public static void main(String[] args){
		ConfigurationManager.importProperties();
	}
}
