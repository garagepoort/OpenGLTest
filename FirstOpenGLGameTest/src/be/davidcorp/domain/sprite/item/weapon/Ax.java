package be.davidcorp.domain.sprite.item.weapon;

import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.texture.TextureBunch;

public class Ax extends MeleeWeapon{

	public Ax(float x, float y) throws SpriteException {
		super(x, y, new SwingArea(x, y, 64, 20, 60));
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/weapons/ax.png"));
		setAttackCooldown(180);
		setStaminaCost(20);
	}
}
