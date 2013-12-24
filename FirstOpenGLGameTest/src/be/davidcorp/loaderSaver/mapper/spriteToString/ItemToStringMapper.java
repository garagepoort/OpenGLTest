package be.davidcorp.loaderSaver.mapper.spriteToString;

import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.potion.HealthPotion;
import be.davidcorp.domain.sprite.item.potion.StaminaPotion;
import be.davidcorp.domain.sprite.item.weapon.Pistol;
import be.davidcorp.domain.sprite.item.weapon.RangedWeapon;
import be.davidcorp.loaderSaver.SpriteProperty;

public class ItemToStringMapper extends SpriteToStringMapper<Item>{

	
	
	@Override
	public void addProperties() {
		super.addProperties();
		properties.put(SpriteProperty.INFOTEXT, sprite.getInfoText());
		properties.put(SpriteProperty.WEIGHT, valueToString(sprite.getWeight()));
		
		if(sprite instanceof RangedWeapon){
			RangedWeapon weapon = (RangedWeapon) sprite;
			properties.put(SpriteProperty.WEIGHT, valueToString(weapon.getMaximumAmountOfAmmo()));
		}
	}

	@Override
	public String getSpriteClass() {
		return "ITEM";
	}

	@Override
	public String getSpriteType() {
		if(sprite instanceof HealthPotion){
			return "HEALTHPOTION";
		}
		if(sprite instanceof StaminaPotion){
			return "STAMINAPOTION";
		}
		if(sprite instanceof Pistol){
			return "PISTOL";
		}
		throw new MapperException("No mapping for sprite: " + sprite);
	}

}
