package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.Sprite;

public abstract class SpriteTestBuilder<SPRITE extends Sprite> {
	private float x;
	private float y;
	private int width;
	private int height;
	
	protected abstract SPRITE createInstance();
	
	public SPRITE build() {
		SPRITE sprite = buildBasic();
		return sprite;
	}
	
	protected SPRITE buildBasic () {
		SPRITE sprite = createInstance();
		sprite.setX(x);
		sprite.setY(y);
		sprite.setWidth(width);
		sprite.setHeight(height);
		return sprite;
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
