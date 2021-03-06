package be.davidcorp.domain.sprite.item.weapon;

import static be.davidcorp.domain.game.CurrentGameFieldManager.getCurrentGameField;
import be.davidcorp.metric.Vector;
import be.davidcorp.repository.DefaultSpriteRepository;

public class RangedWeapon extends Weapon {
	private static final long serialVersionUID = -5999676273584667631L;
	private int ammoSize;
	private float startX;
	private float startY;

	public RangedWeapon(float x, float y, int width, int height, int aantalAmmo) {
		super(x, y, width, height);
		initializeWeapon(aantalAmmo);
	}

	private void initializeWeapon(int aantalAmmo) {
		this.startX = 16;
		this.startY = 16;
		this.ammoSize = aantalAmmo;
	}

	@Override
	protected void attack(Vector p) {
		Ammo ammoInstance = new Bullet(getX(), getY());
		ammoInstance.setX(calculateAmmoStartPointX(ammoInstance));
		ammoInstance.setY(calculateAmmoStartPointY());
		ammoInstance.setDirectionVector(p);
		DefaultSpriteRepository.getInstance().createSprite(ammoInstance);
		getCurrentGameField().addSpriteToWorld(ammoInstance);
		ammoSize--;
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

	public void setMaximumAmountOfAmmo(int amount) {
		this.ammoSize = amount;
	}
	
	public int getMaximumAmountOfAmmo() {
		return ammoSize;
	}
	
	@Override
	public WeaponType getWeaponType() {
		return WeaponType.RANGED;
	}

}
