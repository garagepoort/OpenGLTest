package be.davidcorp.domain.attribute;

import java.util.HashMap;
/**
 * 
 * @author david
 *
 * An interface for items that increase certain parameters of a sprite
 * For example a item can increase the maximum health of a sprite.
 * If you want this to happen you add a key value pair to the attributesHashmap with key {@link Attributes} HEALTH and key the value it should increase.
 */
public interface Attributer {
	
	/**
	 * Set the attributes
	 * @param attributes
	 */
	public void setAttributes(HashMap<Attributes, Float> attributes);
	
	/**
	 * get the attributes.
	 * @return A {@link HashMap} with attributes.
	 */
	public HashMap<Attributes, Float> getAttributes();

}
