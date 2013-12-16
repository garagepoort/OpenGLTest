package be.davidcorp.domain.sprite.item.weapon;

import java.io.IOException;

import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.texture.TextureBunch;

public class RocketLauncher extends RangedWeapon{

	
	public RocketLauncher(float x, float y, int aantalRockets) throws  SpriteException, IOException{
		super(x,y,64,64,aantalRockets);
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/weapons/rocketlauncher.png"));
		setTextureBunch(new TextureBunch());
		setInfoText("This is a rocketlauncher.\nIt deals a lot of damage but\nalso takes a long time to reload.");
		setAttackCooldown(360);
	}

	@Override
	public Ammo getAmmoInstance() throws SpriteException, IOException {
		return new Rocket(getX(), getY());
	}

}
