package be.davidcorp.domain.sprite.item.weapon;

import static be.davidcorp.domain.sprite.SpriteType.PISTOL;

import java.io.IOException;

import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.organic.OrganicSprite;
import be.davidcorp.texture.TextureBunch;

public class Pistol extends RangedWeapon{


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
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/weapons/pistol.png"));
		setAttackCooldown(40);
	}

	@Override
	public Ammo getAmmoInstance() {
		return new Bullet(getX(), getY());
	}

	@Override
	public SpriteType getType() {
		return PISTOL;
	}

}
