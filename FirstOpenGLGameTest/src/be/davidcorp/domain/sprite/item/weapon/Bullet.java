package be.davidcorp.domain.sprite.item.weapon;

import static be.davidcorp.domain.sprite.SpriteType.BULLET;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.organic.OrganicSprite;
import be.davidcorp.texture.TextureBunch;

public class Bullet extends Ammo {

	public Bullet(float x, float y) {
		super(x, y, 4, 4, 500);
		initializeTextureBunch();
		setSpeed(2f);
		setSpeed(1f);
		setDamage(5000);
	}

	public void initializeTextureBunch() {
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/ammo/bullet.png"));
	}
	@Override
	public void useItem(OrganicSprite organicSprite) {
		// TODO Auto-generated method stub

	}

	@Override
	public SpriteType getType() {
		return BULLET;
	}

}
