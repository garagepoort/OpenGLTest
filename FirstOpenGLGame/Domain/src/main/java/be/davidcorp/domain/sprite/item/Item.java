package be.davidcorp.domain.sprite.item;

import java.util.List;

import be.davidcorp.domain.attribute.Attribute;
import be.davidcorp.domain.attribute.Attributer;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.organic.OrganicSprite;

import com.google.common.collect.Lists;

public class Item extends Sprite implements Attributer {
	private static final long serialVersionUID = 2593471117706197801L;
	private int weight;
	private String infoText;
	private List<Attribute> attributes = Lists.newArrayList();
	private OrganicSprite owner;
	private UsableImplementation usableImplementation;
	private boolean onGround =true;

	public Item() {
		super();
	}
	public Item(float x, float y) {
		super(x, y, 32, 32);
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
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

	public void setUsableImplementation(UsableImplementation usableImplementation) {
		this.usableImplementation = usableImplementation;
	}

	public void useItem(Sprite usingSprite) {
		if (usableImplementation != null) {
			usableImplementation.useItem(this, usingSprite);
		}
	}

	@Override
	public List<Attribute> getAttributes() {
		return attributes;
	}
	
	public void addAttribute(Attribute attribute) {
		attributes.add(attribute);
	}
	
	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}
	
	public boolean isOnGround() {
		return onGround;
	}

	

}
