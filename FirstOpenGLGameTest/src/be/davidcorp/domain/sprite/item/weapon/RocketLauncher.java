package be.davidcorp.domain.sprite.item.weapon;

import be.davidcorp.texture.TextureBunch;

public class RocketLauncher extends RangedWeapon{

	
	public RocketLauncher(float x, float y, int aantalRockets) {
		super(x,y,64,64,aantalRockets);
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/weapons/rocketlauncher.png"));
		setTextureBunch(new TextureBunch());
		setInfoText("This is a rocketlauncher.\nIt deals a lot of damage but\nalso takes a long time to reload.");
		setAttackCooldown(360);
	}

	@Override
	public Ammo getAmmoInstance() {
		return new Rocket(getX(), getY());
	}

}
