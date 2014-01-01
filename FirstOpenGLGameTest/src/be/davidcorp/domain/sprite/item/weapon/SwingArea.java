package be.davidcorp.domain.sprite.item.weapon;

import static be.davidcorp.domain.sprite.SpriteType.SWINGAREA;
import be.davidcorp.component.TimeToLiveComponent;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.SpriteType;

public class SwingArea extends Sprite{

	public int damage = 6000;
	
	public SwingArea(float x, float y, int width, int height, int ttl) {
		super(x, y, width, height);
		addComponent(new TimeToLiveComponent(ttl));
	}

	@Override
	public void onDeath() {}

	@Override
	public SpriteType getType() {
		return SWINGAREA;
	}

}
