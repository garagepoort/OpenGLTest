package be.davidcorp.loaderSaver.mapper.spriteToString;

import static be.davidcorp.loaderSaver.SpriteProperty.USABLERANGE;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;

public class ConstructionSpriteToStringMapper extends SpriteToStringMapper<ConstructionSprite>{

	
	@Override
	public void addProperties() {
		super.addProperties();
		properties.put(USABLERANGE, valueToString(sprite.getUsableRange()));
	}

	@Override
	public String getSpriteClass() {
		return "CONSTRUCTION";
	}

}
