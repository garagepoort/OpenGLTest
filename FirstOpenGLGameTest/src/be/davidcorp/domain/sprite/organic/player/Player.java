package be.davidcorp.domain.sprite.organic.player;

import java.io.IOException;
import java.util.Observer;

import be.davidcorp.WindDirection;
import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.mission.Mission;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.item.weapon.Pistol;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.OrganicSprite;
import be.davidcorp.texture.TextureBunch;

public class Player extends OrganicSprite implements Observer {

	private String name;
	private Mission currentMission;
	
	public Light flashLight;
	public Light LineOfSight;

	public Player(float x, float y) throws SpriteException, IOException {
		super(x, y, 32, 32);
		Pistol pistol = new Pistol(getX(), getY(), 100, this);
		setEquippedWeapon(pistol);

		TextureBunch bunch = new TextureBunch()
			.withDefaultTexture("resources/images/player/playerstanding.png")
			.withTextureAtSecond("resources/images/player/rightFoot.png", 1)
			.withTextureAtSecond("resources/images/player/leftFoot.png", 20)
			.withLastTextureTime(40);
		setTextureBunch(bunch);
		setSpeed(0.05f);

		flashLight = new Light(getCenter().x, getCenter().y, new Color(255, 246, 133), 150, false);
		LineOfSight = new Light(getCenter().x, getCenter().y, new Color(255, 246, 133), 50, true);

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
		return currentMission.checkAllCriterias();
	}
	
	public void setCurrentMission(Mission currentMission) {
		this.currentMission = currentMission;
	}

	@Override
	protected void updateTexture() {
		if (getTextureBunch() != null) {
			if (isMoving()) {
				getTextureBunch().startLoop();
			} else {
				getTextureBunch().stopLoop();
			}
			getTextureBunch().updateTexture();
		}
	}
	
	
}