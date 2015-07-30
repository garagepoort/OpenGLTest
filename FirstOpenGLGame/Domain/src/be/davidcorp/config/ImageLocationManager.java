package be.davidcorp.config;

import static be.davidcorp.config.ConfigurationManager.getProperty;
import static be.davidcorp.config.ConfigurationManager.Property.IMAGE_LOCATION_PROPERTY;
import static java.io.File.separator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import be.davidcorp.domain.sprite.SpriteType;

public class ImageLocationManager {

    private static final String BASEDIR = "BASEDIR";
    private static String baseDirectory;
    private static ResourceBundle resourceBundle;

    public static void importProperties() {
        resourceBundle = ResourceBundle.getBundle(ConfigurationManager.getProperty(IMAGE_LOCATION_PROPERTY));
        baseDirectory = resourceBundle.getString(BASEDIR);
    }

    public static String getImageLocation(SpriteType property) {
        if (!resourceBundle.containsKey(property.toString())) return null;
        return baseDirectory + separator + resourceBundle.getString(property.toString());
    }
}