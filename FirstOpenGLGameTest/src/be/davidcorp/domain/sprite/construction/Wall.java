package be.davidcorp.domain.sprite.construction;


import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.SpriteType;

public class Wall extends ConstructionSprite{

	public Wall(float x, float y, int width, int height) throws SpriteException {
		super(x, y, width, height);
		setMaxHealthPoints(1000);
		setColor(new Color(255, 0, 0));
	}


	public Wall() {
	}

	public void removeHealth(int hp){
		//Wall cannot die
	}

	@Override
	public SpriteType getType() {
		return SpriteType.WALL;
	}


	@Override
	protected void onUse(Sprite sprite) {
		
	}


}
