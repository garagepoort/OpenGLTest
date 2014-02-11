package be.davidcorp.domain.attribute;

public class Attribute {

	public AttributeType attributeType;
	public float value;
	
	public Attribute(AttributeType attributeType, float value) {
		this.attributeType = attributeType;
		this.value = value;
	}
	
}
