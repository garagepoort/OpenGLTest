package be.davidcorp.config;

import static be.davidcorp.config.ConfigurationManager.Property.IMAGE_LOCATION_PROPERTY;
import static java.io.File.separator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import be.davidcorp.domain.sprite.SpriteType;

public class ImageLocationManager {

	private static final String BASEDIR = "BASEDIR";
	private static String baseDirectory;
	private static Properties prop = new Properties();

	public static void initializeProperties() {
		try {
			prop.load(new FileInputStream(ConfigurationManager.getProperty(IMAGE_LOCATION_PROPERTY)));
			baseDirectory = prop.getProperty(BASEDIR);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static String getImageLocation(SpriteType property){
		if(!prop.containsKey(property.toString())) return null;
		return baseDirectory + separator + prop.getProperty(property.toString());
	}
}