package be.davidcorp.domain.sprite.item.weapon;

import be.davidcorp.domain.sprite.organic.OrganicSprite;
import be.davidcorp.texture.TextureBunch;

public class Bullet extends Ammo{

	public Bullet(float x, float y){
		super(x, y, 4,4);
		initializeTextureBunch();
		setSpeed(2f);
		setSpeed(1f);
		setDamage(5000);
		setTTL(500);
	}

	public void initializeTextureBunch(){
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/ammo/bullet.png"));
	}
	@Override
	public void useItem(OrganicSprite organicSprite) {
		// TODO Auto-generated method stub
		
	}

}
