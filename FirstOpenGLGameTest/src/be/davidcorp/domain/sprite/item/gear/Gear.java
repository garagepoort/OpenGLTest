package be.davidcorp.domain.sprite.item.gear;

import static be.davidcorp.domain.sprite.SpriteType.GEAR;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.SpriteType;

public class Gear extends Sprite{

	public Gear(float x, float y) {
		super(x, y,64,64);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SpriteType getType() {
		return GEAR;
	}

}
