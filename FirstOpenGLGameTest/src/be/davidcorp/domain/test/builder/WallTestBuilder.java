package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.construction.Wall;

public class WallTestBuilder extends ConstructionSpriteTestBuilder<Wall>{

	@Override
	protected Wall createInstance() {
		return new Wall();
	}

}
