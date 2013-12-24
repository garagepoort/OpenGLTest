package be.davidcorp.loaderSaver.mapper.stringSpriteMapper;

import static be.davidcorp.loaderSaver.SpriteProperty.AANTAL_BULLETS;
import static be.davidcorp.loaderSaver.SpriteProperty.INFOTEXT;
import static be.davidcorp.loaderSaver.SpriteProperty.WEIGHT;
import static java.lang.Integer.parseInt;

import java.util.Map;

import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.weapon.RangedWeapon;
import be.davidcorp.loaderSaver.SpriteProperty;

public class StringToItemMapper extends StringToSpriteMapper<Item>{

	@Override
	protected void doExtraMapping(Item sprite, Map<SpriteProperty, String> properties) {
		if(properties.containsKey(WEIGHT)) sprite.setWeight(parseInt(properties.get(WEIGHT)));
		if(properties.containsKey(INFOTEXT)) sprite.setInfoText(properties.get(INFOTEXT));
		
		if(sprite instanceof RangedWeapon){
			RangedWeapon weapon = (RangedWeapon) sprite;
			if(properties.containsKey(AANTAL_BULLETS)) weapon.setMaximumAmountOfAmmo(parseInt(properties.get(AANTAL_BULLETS)));
		}
	}

}
