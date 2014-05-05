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
		String value = prop.getProperty(property.toString());
		if(value == null){
			throw new RuntimeException("Property [" + property + "] can not be found. Are you sure you have configured it in application.properties file?");
		}
		return value;
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
