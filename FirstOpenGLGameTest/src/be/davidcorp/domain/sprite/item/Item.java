package be.davidcorp.domain.sprite.item;

import java.util.HashMap;

import be.davidcorp.domain.attribute.Attributer;
import be.davidcorp.domain.attribute.Attributes;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.organic.OrganicSprite;

public abstract class Item extends Sprite implements Attributer{
	private int weight;
	private boolean stackable;
	private String infoText;
	private HashMap<Attributes, Float> attributes = new HashMap<>();
	private OrganicSprite owner;
	
	public Item(float x, float y) {
		super(x, y,32,32);
		setOwner(owner);
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public boolean isStackable() {
		return stackable;
	}

	public void setStackable(boolean stackable) {
		this.stackable = stackable;
	}

	public String getInfoText() {
		return infoText;
	}

	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}
	
	public Sprite getOwner() {
		return owner;
	}

	public void setOwner(OrganicSprite owner) {
		this.owner = owner;
	}
	
	public abstract void useItem(OrganicSprite organicSprite);
	
	@Override
	public void setAttributes(HashMap<Attributes, Float> attributes) {
		this.attributes=attributes;
	}
	
	@Override
	public HashMap<Attributes, Float> getAttributes() {
		return attributes;
	}
}
