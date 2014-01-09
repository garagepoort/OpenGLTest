package be.davidcorp.domain.sprite.item.weapon;

import static be.davidcorp.domain.sprite.SpriteType.BULLET;

public class Bullet extends Ammo {

	private static final long serialVersionUID = 4030507961673652655L;

	public Bullet(float x, float y) {
		super(x, y, 4, 4, 500);
		setSpeed(2f);
		setSpeed(1f);
		setDamage(5000);
		setSpriteType(BULLET);
	}

}
