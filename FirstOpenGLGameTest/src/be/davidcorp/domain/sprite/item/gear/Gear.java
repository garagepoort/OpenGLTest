package be.davidcorp.domain.sprite.item.gear;

import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.sprite.Sprite;

public class Gear extends Sprite{

	public Gear(float x, float y) throws SpriteException {
		super(x, y,64,64);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}

}
