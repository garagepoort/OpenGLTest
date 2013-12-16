package be.davidcorp.domain.sprite;

import static be.davidcorp.domain.trigger.TriggerWhen.ONDESTROY;
import static be.davidcorp.domain.trigger.TriggerWhen.ONHEALTHGAIN;
import static be.davidcorp.domain.trigger.TriggerWhen.ONHEALTHLOSS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import be.davidcorp.WindDirection;
import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.trigger.Trigger;
import be.davidcorp.domain.trigger.TriggerWhen;
import be.davidcorp.domain.trigger.Triggerable;
import be.davidcorp.domain.utilities.sprite.SpriteMovingUtility;
import be.davidcorp.metric.Point;
import be.davidcorp.metric.Vector;
import be.davidcorp.texture.TextureBunch;

public class Sprite extends Triggerable {

	private int ID;
	private Color color;
	private float defaultSpeed = 0.35f;

	private boolean alive = true;
	private int healthPoints = 10000;
	private int maxHealthPoints = 10000;
	private float rotationAngle;
	private boolean isMoving;

	private int TTL = -1;
	private int TTLProgress;
	private int healthRegen;

	private TextureBunch textureBunch;
	private Vector directionVector = new Vector(new Point(0, 0, 0), new Point(0, 300, 0));

	private Map<TriggerWhen, ArrayList<Trigger>> triggers = new HashMap<>();

	private HitBox hitbox = new HitBox();

	public Sprite(float x, float y, int width, int height)
			throws SpriteException {
		initializeSprite(x, y, width, height);
	}

	public Sprite() {
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void move(WindDirection direction, float distance) {
		SpriteMovingUtility.moveSpriteInGameField(this, direction, distance);
	}

	public float getRotationAngle() {
		return rotationAngle;
	}

	public void setRotationAngle(float rotationAngle) {
		this.rotationAngle = rotationAngle;
	}

	public HitBox getHitBox() {
		return hitbox;
	}

	public float getX() {
		return hitbox.getDownLeftPoint().x;
	}

	public void setX(float x) {
		float diff = x - getX();
		for (Trigger t : getAllTriggers()) {
			t.setX(x);
		}
		hitbox.moveHorizontally(diff);
	}

	public float getY() {
		return hitbox.getDownLeftPoint().y;
	}

	public void setY(float y) {
		float diff = y - getY();
		getAllTriggers();
		for (Trigger t : getAllTriggers()) {
			t.setY(y);
		}
		hitbox.moveVertically(diff);
	}

	public int getWidth() {
		return (int) hitbox.getWidth();
	}

	public void setWidth(int width) throws SpriteException {
		if (width < 1) {
			throw new SpriteException("The width must be 1 or greater");
		}
		if (getRotationAngle() == 0) {
			hitbox.getDownRightPoint().x = hitbox.getDownLeftPoint().x + width;
			hitbox.getUpperRightPoint().x = hitbox.getUpperLeftPoint().x + width;
		}
	}

	public int getHeight() {
		return (int) hitbox.getHeight();
	}

	public void setHeight(int height) throws SpriteException {
		if (height < 1) {
			throw new SpriteException("The height must be 1 or greater");
		}
		if (getRotationAngle() == 0) {
			hitbox.getUpperRightPoint().y = hitbox.getDownRightPoint().y + height;
			hitbox.getUpperLeftPoint().y = hitbox.getDownLeftPoint().y + height;
		}
	}

	public float getSpeed() {
		return defaultSpeed;
	}

	public void setSpeed(float defaultSpeed) {
		this.defaultSpeed = defaultSpeed;
	}

	public boolean isAlive() {
		return alive;
	}

	public void kill() {
		this.alive = false;
		onDeath();
		checkTriggers(ONDESTROY, null);
	}

	public void onDeath() {
		if (GameFieldManager.getCurrentGameField() != null)
			GameFieldManager.getCurrentGameField().removeSpriteFromWorld(this);
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) throws SpriteException {
		if (healthPoints > getMaxHealthPoints()) {
			throw new SpriteException("The healthpoints can't be greater then the maximum healthpoints");
		}
		if (healthPoints <= 0) {
			kill();
		}
		this.healthPoints = healthPoints;
	}

	public int getMaxHealthPoints() {
		return maxHealthPoints;
	}

	public void setMaxHealthPoints(int maxHealthPoints) {
		this.maxHealthPoints = maxHealthPoints;
		try {
			setHealthPoints(maxHealthPoints);
		} catch (SpriteException e) {
			e.printStackTrace();
		}
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public void updateSprite(int secondsMovedInGame) {
		updateTTL();
		updateTexture();
		addHealth(healthRegen);
		setMoving(false);
	}

	public void addHealth(int hp) {
		try {
			if (getHealthPoints() + hp >= getMaxHealthPoints()) {
				setHealthPoints(getMaxHealthPoints());
			} else {
				setHealthPoints(getHealthPoints() + hp);
			}
			checkTriggers(ONHEALTHGAIN, null);
		} catch (SpriteException e) {
		}
	}

	public void removeHealth(int hp) {
		try {
			if (getHealthPoints() - hp <= 0) {
				setHealthPoints(0);
			} else {
				setHealthPoints(getHealthPoints() - hp);
			}
			checkTriggers(ONHEALTHLOSS, null);
		} catch (SpriteException e) {
		}
	}

	private void updateTTL() {
		if (TTL != -1) {
			if (TTLProgress != TTL) {
				TTLProgress++;
			} else {
				kill();
			}
		}
	}

	public Point getCenter() {
		return hitbox.getCenter();
	}

	public Vector getDirectionVector() {
		return directionVector;
	}

	public void setDirectionVector(Vector directionVector) {
		this.directionVector = directionVector;
	}

	public void checkTriggers(TriggerWhen trigType, Sprite sprite) {
		if (triggers.containsKey(trigType)) {
			for (Trigger t : this.triggers.get(trigType)) {
				t.trigger(sprite);
			}
		}
	}

	public void addTrigger(Trigger trigger) {
		trigger.setX(getCenter().x);
		trigger.setY(getCenter().y);
		if (!this.triggers.containsKey(trigger.triggerWhen)) {
			this.triggers.put(trigger.triggerWhen, new ArrayList<Trigger>());
		}
		this.triggers.get(trigger.triggerWhen).add(trigger);
	}

	public void removeTrigger(Trigger trigger) {
		triggers.remove(trigger);
	}
	
	protected void updateTexture() {
		if (textureBunch != null) {
			textureBunch.updateTexture();
		}
	}

	protected void setTTL(int tTL) {
		TTL = tTL;
	}

	public TextureBunch getTextureBunch() {
		return textureBunch;
	}

	public void setTextureBunch(TextureBunch textureBunch) {
		this.textureBunch = textureBunch;
	}

	public SpriteType getType() {
		return SpriteType.SPRITE;
	}

	private void initializeSprite(float x, float y, int width, int height) throws SpriteException {
		setWidth(width);
		setHeight(height);
		setX(x);
		setY(y);
	}

	public ArrayList<Trigger> getAllTriggers() {
		ArrayList<Trigger> allTriggers = new ArrayList<>();
		for (TriggerWhen key : this.triggers.keySet()) {
			allTriggers.addAll(this.triggers.get(key));
		}
		return allTriggers;
	}
}
