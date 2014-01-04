package be.davidcorp.domain.test.builder;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import be.davidcorp.component.Component;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.SpriteType;

public abstract class SpriteBuilder<SPRITE extends Sprite> {
	private int id = 1;
	private float x = 10;
	private float y = 11;
	private int width = 12;
	private int height = 13;
	private int health = 100;
	private float speed = 1;
	private int maxHealth = 100;
	private SpriteType spriteType;
	private List<Component> components = newArrayList();

	protected abstract SPRITE createInstance();

	public SPRITE build() {
		SPRITE sprite = buildBasic();
		return sprite;
	}

	protected SPRITE buildBasic() {
		SPRITE sprite = createInstance();
		sprite.setID(id);
		sprite.setX(x);
		sprite.setY(y);
		sprite.setWidth(width);
		sprite.setHeight(height);
		sprite.setMaxHealthPoints(maxHealth);
		sprite.setHealthPoints(health);
		sprite.setSpeed(speed);
		sprite.setSpriteType(spriteType);
		for(Component component : components){
			sprite.addComponent(component);
		}
		return sprite;
	}

	public SpriteBuilder<SPRITE> withID(int id) {
		this.id = id;
		return this;
	}

	public SpriteBuilder<SPRITE> withX(float x) {
		this.x = x;
		return this;
	}
	public SpriteBuilder<SPRITE> withY(float y) {
		this.y = y;
		return this;
	}

	public SpriteBuilder<SPRITE> withWidth(int width) {
		this.width = width;
		return this;
	}

	public SpriteBuilder<SPRITE> withHeight(int height) {
		this.height = height;
		return this;
	}

	public SpriteBuilder<SPRITE> withHealth(int health) {
		this.health = health;
		return this;
	}

	public SpriteBuilder<SPRITE> withMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
		return this;
	}

	public SpriteBuilder<SPRITE> withSpeed(float speed) {
		this.speed = speed;
		return this;
	}

	public SpriteBuilder<SPRITE> withSpriteType(SpriteType spriteType) {
		this.spriteType = spriteType;
		return this;
	}
	
	public SpriteBuilder<SPRITE> withComponent(Component component) {
		components.add(component);
		return this;
	}
}
