package be.davidcorp.loaderSaver.stringSpriteMapper;

import static java.lang.Integer.parseInt;

import java.util.Map;

import be.davidcorp.domain.sprite.organic.OrganicSprite;
import be.davidcorp.loaderSaver.SpriteProperty;

public class StringToOrganicSpriteMapper<SPRITE extends OrganicSprite> extends StringToSpriteMapper<SPRITE>{

	@Override
	protected void doExtraMapping(SPRITE sprite, Map<SpriteProperty, String> properties) {
		if(properties.containsKey(SpriteProperty.STAMINA)) sprite.setStamina(parseInt(properties.get(SpriteProperty.STAMINA)));
	}
	
}
