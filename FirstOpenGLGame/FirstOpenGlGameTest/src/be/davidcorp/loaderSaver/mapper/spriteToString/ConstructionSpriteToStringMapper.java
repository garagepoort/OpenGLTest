package be.davidcorp.loaderSaver.mapper.spriteToString;

import be.davidcorp.domain.sprite.construction.ConstructionSprite;

public class ConstructionSpriteToStringMapper extends SpriteToStringMapper<ConstructionSprite>{

	
	@Override
	public String getSpriteClass() {
		return "CONSTRUCTION";
	}

}
