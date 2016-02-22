package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.construction.ConstructionSprite;

public class ConstructionSpriteBuilder extends SpriteBuilder<ConstructionSprite>{

	@Override
	protected ConstructionSprite buildBasic() {
		ConstructionSprite sprite = super.buildBasic();
		return sprite;
	}

	@Override
	protected ConstructionSprite createInstance() {
		return new ConstructionSprite();
	}

	
}
