package be.davidcorp.domain.sprite.item.potion;

import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.organic.OrganicSprite;
import be.davidcorp.texture.TextureBunch;

public class HealthPotion extends Item {
	private int health = 2000;

	public HealthPotion(float x, float y)  {
		super(x, y);
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/potions/healthPotion.png"));
		setInfoText("This potion heals you for 20 health.");
	}
	
	public HealthPotion()  {
		this(32, 32);
	}

	@Override
	public void useItem(OrganicSprite organicSprite) {
		organicSprite.addHealth(health);
		kill();
	}

}
