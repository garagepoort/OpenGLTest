package be.davidcorp.domain.sprite.item.weapon;

import static be.davidcorp.domain.sprite.SpriteType.ROCKET;
import be.davidcorp.domain.sprite.SpriteType;

public class Rocket extends Ammo {

	private static final long serialVersionUID = -2733831330575173222L;

	public Rocket(float x, float y) {
		super(x, y, 15, 57, 60);
		setSpeed(2f);
		setSpeed(2f);
		setDamage(50000);
	}

	@Override
	public SpriteType getType() {
		return ROCKET;
	}

}
