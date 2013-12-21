package be.davidcorp.domain.sprite.item.weapon;

import be.davidcorp.domain.sprite.Sprite;

public class SwingArea extends Sprite{

	public int damage = 6000;
	
	public SwingArea(float x, float y, int width, int height, int ttl) {
		super(x, y, width, height);
		setTTL(ttl);
	}

	@Override
	public void onDeath() {}

}
