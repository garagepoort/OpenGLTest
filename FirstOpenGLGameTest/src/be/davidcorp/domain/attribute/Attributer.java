package be.davidcorp.domain.attribute;

import java.util.HashMap;
import java.util.List;
/**
 * 
 * @author david
 *
 * An interface for items that increase certain parameters of a sprite
 * For example a item can increase the maximum health of a sprite.
 * If you want this to happen you add a key value pair to the attributesHashmap with key {@link AttributeType} HEALTH and value the value it should increase.
 */
public interface Attributer {
	
	/**
	 * get the attributes.
	 * @return A {@link HashMap} with attributes.
	 */
	public List<Attribute> getAttributes();

}
