package be.davidcorp.domain.sprite.item.weapon;

import java.io.IOException;

import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.metric.Vector;

public abstract class RangedWeapon extends Weapon {
	private int ammoSize;
	private float startX;
	private float startY;

	public RangedWeapon(float x, float y, int width, int height, int aantalAmmo)
			throws SpriteException, IOException {
		super(x, y, width, height);
		initializeWeapon(aantalAmmo);
	}

	private void initializeWeapon(int aantalAmmo) throws SpriteException, IOException {
		this.startX = 16;
		this.startY = 16;
		this.ammoSize = aantalAmmo;
	}

	public abstract Ammo getAmmoInstance() throws SpriteException, IOException;

	@Override
	protected void attack(Vector p) {
		try {
			Ammo ammoInstance = getAmmoInstance();
			ammoInstance.setX(calculateAmmoStartPointX(ammoInstance));
			ammoInstance.setY(calculateAmmoStartPointY());
			ammoInstance.setDirectionVector(p);
			GameFieldManager.getCurrentGameField().addAmmoToWorld(ammoInstance);
			ammoSize--;
		} catch (SpriteException | IOException e) {
			e.printStackTrace();
		}
	}

	private float calculateAmmoStartPointY() {
		return getHitBox().getDownLeftPoint().y + startY;
	}

	private float calculateAmmoStartPointX(Ammo ammoInstance) {
		return getHitBox().getDownLeftPoint().x + startX - ammoInstance.getWidth() / 2;
	}

	public int getAmmoLeft() {
		return ammoSize;
	}

	@Override
	public WeaponType getWeaponType() {
		return WeaponType.RANGED;
	}

}
