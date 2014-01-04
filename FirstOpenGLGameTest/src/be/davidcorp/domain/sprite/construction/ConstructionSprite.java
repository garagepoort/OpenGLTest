package be.davidcorp.domain.sprite.construction;

import be.davidcorp.domain.sprite.Sprite;


public class ConstructionSprite extends Sprite{

	private int usableRange = 100;
	
	public ConstructionSprite(float x, float y, int width, int height) {
		super(x, y, width, height);
	}

	public ConstructionSprite() {
		super();
	}
}
