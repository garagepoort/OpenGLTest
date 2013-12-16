package be.davidcorp.domain.sprite.item.weapon;

import java.io.IOException;

import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.sprite.organic.OrganicSprite;
import be.davidcorp.texture.TextureBunch;

public class Pistol extends RangedWeapon{


	public Pistol(float x, float y, int aantalBullets) throws  SpriteException, IOException{
		super(x,y,32,32,aantalBullets);
		initialize();
	}
	
	public Pistol(float x, float y, int aantalBullets, OrganicSprite organicSprite) throws SpriteException, IOException {
		super(x,y,32,32,aantalBullets);
		initialize();
		setOwner(organicSprite);
	}

	private void initialize() throws SpriteException {
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/weapons/pistol.png"));
		setAttackCooldown(40);
	}

	@Override
	public Ammo getAmmoInstance() throws SpriteException, IOException {
		return new Bullet(getX(), getY());
	}

}
