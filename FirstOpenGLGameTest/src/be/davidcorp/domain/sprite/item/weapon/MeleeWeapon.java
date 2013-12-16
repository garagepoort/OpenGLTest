package be.davidcorp.domain.sprite.item.weapon;

import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.metric.Vector;

public class MeleeWeapon extends Weapon {

	private int widthRadius;
	private int staminaCost;

	public MeleeWeapon(float x, float y, SwingArea area)
			throws SpriteException {
		super(x, y, 32, 32);
	}

	@Override
	public void attack(Vector vector) {
		if (isReadyToAttack()) {
		}
	}

	public int getWidthRadius() {
		return widthRadius;
	}

	public int getStaminaCost() {
		return staminaCost;
	}

	public void setStaminaCost(int staminaCost) {
		this.staminaCost = staminaCost;
	}

	@Override
	public WeaponType getWeaponType() {
		return WeaponType.MELEE;
	}

}
