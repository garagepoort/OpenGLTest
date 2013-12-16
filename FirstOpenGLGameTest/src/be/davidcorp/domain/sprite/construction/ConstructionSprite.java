package be.davidcorp.domain.sprite.construction;

import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.SpriteType;


public abstract class ConstructionSprite extends Sprite{

	
	public ConstructionSprite(float x, float y, int width, int height) throws SpriteException {
		super(x, y, width, height);
	}

	public ConstructionSprite() {
	}

	private int usableRange = 100;
	
	public SpriteType getType() {
		return SpriteType.CONSTRUCTION;
	}

	protected abstract void onUse(Sprite sprite);
	
	protected void setUsableRange(int usableRange){
		this.usableRange = usableRange;
	}
	
	public void doUse(Sprite sprite){
		if(isInUsableRange(sprite) || usableRange == -1){
			onUse(sprite);
		}
	}
	
	private boolean isInUsableRange(Sprite sprite) {
		float x = sprite.getX()+sprite.getWidth()/2;
		float y = sprite.getY()+sprite.getHeight()/2;
		float x2 = getX()+getWidth()/2;
		float y2 = getY()+getHeight()/2;
		if(x<x2+usableRange && x>x2-usableRange&& y<y2+usableRange&& y>y2-usableRange){
			return true;
		}
		return false;
	}

}
