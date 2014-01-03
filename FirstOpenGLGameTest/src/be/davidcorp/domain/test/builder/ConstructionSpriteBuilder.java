package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;

public class ConstructionSpriteBuilder extends SpriteBuilder<ConstructionSprite>{
	private int usableRange;

	@Override
	protected ConstructionSprite buildBasic() {
		ConstructionSprite sprite = super.buildBasic();
		sprite.setUsableRange(usableRange);
		return sprite;
	}

	public ConstructionSpriteBuilder withUsableRange(int usableRange){
		this.usableRange = usableRange;
		return this;
	}
	
	@Override
	protected ConstructionSprite createInstance() {
		return new ConstructionSprite() {
			
			@Override
			protected void onUse(Sprite sprite) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	
}
