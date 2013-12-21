package be.davidcorp.domain.sprite.item.weapon;

import be.davidcorp.domain.sprite.organic.OrganicSprite;
import be.davidcorp.texture.TextureBunch;

public class Rocket extends Ammo{
	
	public Rocket(float x, float y) {
		super(x, y, 15,57);
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/ammo/rocketSmall.png"));
		setSpeed(2f);
		setSpeed(2f);
		setDamage(50000);
		setTTL(60);
	}

	@Override
	public void useItem(OrganicSprite organicSprite) {
		// TODO Auto-generated method stub
		
	}

}
