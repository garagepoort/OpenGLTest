package be.davidcorp.loaderSaver.mapper.stringSpriteMapper;

import java.util.Map;

import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.loaderSaver.SpriteProperty;

public class StringToConstructionSpriteMapper extends StringToSpriteMapper<ConstructionSprite>{

	@Override
	protected void doExtraMapping(ConstructionSprite sprite, Map<SpriteProperty, String> properties) {
	}

}
