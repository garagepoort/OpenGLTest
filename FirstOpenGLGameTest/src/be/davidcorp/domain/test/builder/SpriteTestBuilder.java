package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.Sprite;

public abstract class SpriteTestBuilder<SPRITE extends Sprite> {
	private int id = 1;
	private float x = 10;
	private float y = 11;
	private int width = 12;
	private int height = 13;
	
	protected abstract SPRITE createInstance();
	
	public SPRITE build() {
		SPRITE sprite = buildBasic();
		return sprite;
	}
	
	protected SPRITE buildBasic () {
		SPRITE sprite = createInstance();
		sprite.setID(id);
		sprite.setX(x);
		sprite.setY(y);
		sprite.setWidth(width);
		sprite.setHeight(height);
		return sprite;
	}
	
	public SpriteTestBuilder<SPRITE> withID(int id){
		this.id = id;
		return this;
	}
	
	public SpriteTestBuilder<SPRITE> withX(int x) {
		this.x = x;
		return this;
	}
	public SpriteTestBuilder<SPRITE> withY(int y) {
		this.y = y;
		return this;
	}

	public SpriteTestBuilder<SPRITE> withWidth(int width) {
		this.width = width;
		return this;
	}

	public SpriteTestBuilder<SPRITE> withHeight(int height) {
		this.height = height;
		return this;
	}
}
