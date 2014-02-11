package be.davidcorp.domain.sprite.item.weapon;

import static be.davidcorp.domain.sprite.SpriteType.PISTOL;

import java.io.IOException;

import be.davidcorp.domain.sprite.organic.OrganicSprite;

public class Pistol extends RangedWeapon{

	private static final long serialVersionUID = -8206836557019819368L;

	public Pistol() {
		this(0,0,0);
	}
	
	public Pistol(float x, float y, int aantalBullets) {
		super(x,y,32,32,aantalBullets);
		initialize();
	}
	
	public Pistol(float x, float y, int aantalBullets, OrganicSprite organicSprite) throws IOException {
		super(x,y,32,32,aantalBullets);
		initialize();
		setOwner(organicSprite);
	}

	private void initialize() {
		setSpriteType(PISTOL);
		setAttackCooldown(40);
	}

	@Override
	public Ammo getAmmoInstance() {
		return new Bullet(getX(), getY());
	}

}
