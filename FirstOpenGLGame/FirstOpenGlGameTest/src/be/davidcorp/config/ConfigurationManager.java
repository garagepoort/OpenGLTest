package be.davidcorp.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

	public static final Properties prop = new Properties();
	
	public static void importProperties(){
		 
    	try {
    		prop.load(new FileInputStream("resources/configuration/application.properties"));
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}

	public static String getProperty(Property property){
		return prop.getProperty(property.toString());
	}
	
	public static void main(String[] args){
		ConfigurationManager.importProperties();
	}
	
	public enum Property {
		IMAGE_LOCATION_PROPERTY("imageLocationProperties"),
		SAVEFILE_BASEDIR("saveFileBaseDir");
		
		private Property(final String text) {
	        this.propertyString = text;
	    }

	    private final String propertyString;

	    @Override
	    public String toString() {
	        return propertyString;
	    }
	}
}
