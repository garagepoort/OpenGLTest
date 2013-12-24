package be.davidcorp.domain.sprite.item.weapon;

import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.texture.TextureBunch;

public class Ax extends MeleeWeapon{

	public Ax(float x, float y) {
		super(x, y, new SwingArea(x, y, 64, 20, 60));
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/weapons/ax.png"));
		setAttackCooldown(180);
		setStaminaCost(20);
	}

	@Override
	public SpriteType getType() {
		return SpriteType.AX;
	}
}
