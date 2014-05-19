package be.davidcorp.domain.sprite.organic.player;

import static be.davidcorp.domain.sprite.SpriteType.PLAYER;

import java.io.IOException;

import be.davidcorp.WindDirection;
import be.davidcorp.component.HealthRegenerationComponent;
import be.davidcorp.component.UsingComponent;
import be.davidcorp.domain.mission.Mission;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.item.weapon.RangedWeapon;
import be.davidcorp.domain.sprite.item.weapon.WeaponFactory;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.OrganicSprite;

public class Player extends OrganicSprite  {

	private static final long serialVersionUID = -173771074309250104L;
	
	private String name;
	private Mission currentMission;
	
	public Light flashLight;
	public Light LineOfSight;

	public Player(float x, float y) throws  IOException {
		super(x, y, 32, 32);
		setSpriteType(PLAYER);
		RangedWeapon pistol = WeaponFactory.createPistol(x, y);
		setEquippedWeapon(pistol);
		setSpeed(0.05f);
		flashLight = new Light(getCenter().x, getCenter().y, new Color(255, 246, 133), 150, false);
		LineOfSight = new Light(getCenter().x, getCenter().y, new Color(255, 246, 133), 50, true);
		addComponent(new HealthRegenerationComponent(5));
		addComponent(new UsingComponent());
	}

	@Override
	public void move(WindDirection direction, float distance) {
		super.move(direction, distance);
		// TODO change the flaslight implementation.
		flashLight.setX(getCenter().x);
		flashLight.setY(getCenter().y);
		LineOfSight.setX(getCenter().x);
		LineOfSight.setY(getCenter().y);
	}

	public void setX(float x) {
		super.setX(x);
		if (flashLight != null) {
			flashLight.setX(x);
		}
		if (LineOfSight != null) {
			LineOfSight.setX(x);
		}
	}

	public void setY(float y) {
		super.setY(y);
		if (flashLight != null) {
			flashLight.setY(y);
		}
		if (LineOfSight != null) {
			LineOfSight.setY(y);
		}
	}

	public void turnFlaslightOnOff() {
		flashLight.setLightOn(!flashLight.isLightOn());
	}

	public boolean isFlashLightOn() {
		return flashLight.isLightOn();
	}

	public String getName() {
		return name;
	}

	public Light getFlashLight() {
		return flashLight;
	}

	public Light getLineOfSight() {
		return LineOfSight;
	}
	
	public boolean isCurrentMissionDone() {
		if(currentMission == null) return false;
		return currentMission.checkAllCriterias();
	}
	
	public void setCurrentMission(Mission currentMission) {
		this.currentMission = currentMission;
	}

//	@Override
//	protected void updateTexture() {
//		if (getTextureBunch() != null) {
//			if (isMoving()) {
//				getTextureBunch().startLoop();
//			} else {
//				getTextureBunch().stopLoop();
//			}
//			getTextureBunch().updateTexture();
//		}
//	}

}
