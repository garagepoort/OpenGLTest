package be.davidcorp.domain.sprite;

import static be.davidcorp.domain.trigger.TriggerWhen.ONDESTROY;
import static be.davidcorp.domain.trigger.TriggerWhen.ONHEALTHGAIN;
import static be.davidcorp.domain.trigger.TriggerWhen.ONHEALTHLOSS;
import static com.google.common.collect.Maps.newHashMap;

import java.util.ArrayList;
import java.util.Map;

import be.davidcorp.WindDirection;
import be.davidcorp.component.Component;
import be.davidcorp.component.ComponentType;
import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.trigger.Trigger;
import be.davidcorp.domain.trigger.TriggerWhen;
import be.davidcorp.domain.trigger.Triggerable;
import be.davidcorp.domain.utilities.sprite.SpriteMovingUtility;
import be.davidcorp.metric.Point;
import be.davidcorp.metric.Vector;
import be.davidcorp.texture.TextureBunch;

public abstract class Sprite extends Triggerable {

	private Map<ComponentType, Component> componentsMap = newHashMap();
	private Map<TriggerWhen, ArrayList<Trigger>> triggers = newHashMap();

	private int ID;
	private Color color;
	private float defaultSpeed = 0.35f;

	private boolean alive = true;
	private int healthPoints = 10000;
	private int maxHealthPoints = 10000;
	private float rotationAngle;
	private boolean isMoving;

	private TextureBunch textureBunch;
	private Vector directionVector = new Vector(new Point(0, 0, 0), new Point(0, 300, 0));


	private HitBox hitbox = new HitBox();

	private SpriteType spriteType;

	public Sprite(float x, float y, int width, int height) {
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

	public void setWidth(int width) {
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

	public void setHeight(int height) {
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
	
	public void setAlive(boolean alive) {
		this.alive = alive;
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

	public void setHealthPoints(int healthPoints) {
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
		setHealthPoints(maxHealthPoints);
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public void updateSprite(float secondsMovedInGame) {
		updateTexture();
		setMoving(false);
	}

	public void addHealth(int hp) {
		if (getHealthPoints() + hp >= getMaxHealthPoints()) {
			setHealthPoints(getMaxHealthPoints());
		} else {
			setHealthPoints(getHealthPoints() + hp);
		}
		checkTriggers(ONHEALTHGAIN, null);
	}

	public void removeHealth(int hp) {
		if (getHealthPoints() - hp <= 0) {
			setHealthPoints(0);
		} else {
			setHealthPoints(getHealthPoints() - hp);
		}
		checkTriggers(ONHEALTHLOSS, null);
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

	public TextureBunch getTextureBunch() {
		return textureBunch;
	}

	public void setTextureBunch(TextureBunch textureBunch) {
		this.textureBunch = textureBunch;
	}

	public SpriteType getType(){
		return spriteType;
	}

	public ArrayList<Trigger> getAllTriggers() {
		ArrayList<Trigger> allTriggers = new ArrayList<>();
		for (ArrayList<Trigger> value : this.triggers.values()) {
			allTriggers.addAll(value);
		}
		return allTriggers;
	}

	@SuppressWarnings("unchecked")
	public <COMPONENT extends Component> COMPONENT getComponent(ComponentType componentType) {
		Component component = componentsMap.get(componentType);
		return (COMPONENT) component;
	}

	public void addComponent(Component component) {
		componentsMap.put(component.getType(), component);
	}
	
	public void setSpriteType(SpriteType spriteType){
		this.spriteType = spriteType;
	}

	private void initializeSprite(float x, float y, int width, int height) {
		setWidth(width);
		setHeight(height);
		setX(x);
		setY(y);
	}


}
