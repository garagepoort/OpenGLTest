package be.davidcorp.domain.sprite.item;

import static be.davidcorp.domain.sprite.SpriteType.HEALTHNECKLACE;

import java.io.IOException;

import be.davidcorp.domain.attribute.Attributes;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.organic.OrganicSprite;
import be.davidcorp.texture.TextureBunch;


public class HealthNecklace extends Item{
	
	public HealthNecklace(float x, float y) throws IOException {
		super(x, y);
		initializeNecklace();
	}


	private void initializeNecklace() {
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/accesoires/necklace.png"));
		setInfoText("This necklace increases your \nhealth by 20 health");
		getAttributes().put(Attributes.HEALTH, 50f);
	}


	@Override
	public void useItem(OrganicSprite organicSprite) {
		// TODO Auto-generated method stub
	}


	@Override
	public SpriteType getType() {
		return HEALTHNECKLACE;
	}



}
