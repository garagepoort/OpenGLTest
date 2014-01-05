package be.davidcorp.domain.sprite.item.weapon;

import static be.davidcorp.domain.sprite.SpriteType.ROCKET;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.texture.TextureBunch;

public class Rocket extends Ammo {

	public Rocket(float x, float y) {
		super(x, y, 15, 57, 60);
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/ammo/rocketSmall.png"));
		setSpeed(2f);
		setSpeed(2f);
		setDamage(50000);
	}

	@Override
	public SpriteType getType() {
		return ROCKET;
	}

}
