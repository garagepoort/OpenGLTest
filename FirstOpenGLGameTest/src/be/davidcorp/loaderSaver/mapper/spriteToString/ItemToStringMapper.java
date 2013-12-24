package be.davidcorp.loaderSaver.mapper.spriteToString;

import static be.davidcorp.loaderSaver.SpriteProperty.AANTAL_BULLETS;
import static be.davidcorp.loaderSaver.SpriteProperty.INFOTEXT;
import static be.davidcorp.loaderSaver.SpriteProperty.WEIGHT;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.weapon.RangedWeapon;

public class ItemToStringMapper extends SpriteToStringMapper<Item>{
	
	@Override
	public void addProperties() {
		super.addProperties();
		properties.put(INFOTEXT, sprite.getInfoText());
		properties.put(WEIGHT, valueToString(sprite.getWeight()));
		
		if(sprite instanceof RangedWeapon){
			RangedWeapon weapon = (RangedWeapon) sprite;
			properties.put(AANTAL_BULLETS, valueToString(weapon.getMaximumAmountOfAmmo()));
		}
	}

	@Override
	public String getSpriteClass() {
		return "ITEM";
	}

}
