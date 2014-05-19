package be.davidcorp.domain.sprite.item;

import java.io.Serializable;

import be.davidcorp.domain.attribute.Attribute;
import be.davidcorp.domain.attribute.AttributeType;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.organic.OrganicSprite;

public class ItemFactory {

	public static Item createHealthPotion(float x, float y, int health){
		Item item = new ItemBuilder()
			.withInfoTekst("This potion heals you for 20 health.")
			.withUsableImplementation(new HealthPotionUsableImplementation(health))
			.withSpriteType(SpriteType.HEALTHPOTION)
			.withX(x)
			.withY(y)
			.build();
		return item;
	}
	
	public static Item createStaminaPotion(float x, float y, int stamina){
		Item item = new ItemBuilder()
		.withInfoTekst("The potion gives you 20 Mana.")
		.withUsableImplementation(new StaminaPotionUsableImplementation(stamina))
		.withSpriteType(SpriteType.STAMINAPOTION)
		.withX(x)
		.withY(y)
		.build();
		return item;
	}
	
	public static Item createHealthNecklace(float x, float y, int health){
		Item item = new ItemBuilder()
			.withInfoTekst("This necklace increases your \nhealth by 20 health")
			.withAnotherAttribute(new Attribute(AttributeType.HEALTH, health))
			.withSpriteType(SpriteType.HEALTHNECKLACE)
			.withX(x)
			.withY(y)
			.build();
//		item.setTextureBunch(new TextureBunch().withDefaultTexture(NECKLACE_PNG));
		return item;
	}
	

	public static class HealthPotionUsableImplementation implements UsableImplementation, Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 424720376034055350L;
		private int health;

		public HealthPotionUsableImplementation(int health) {
			this.health = health;
		}

		@Override
		public void useItem(Item item, Sprite usingSprite) {
			usingSprite.addHealth(health);
			item.kill();
		}
	}
	
	public static class StaminaPotionUsableImplementation implements UsableImplementation, Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -9164444888431082908L;
		private int stamina;
		
		public StaminaPotionUsableImplementation(int stamina) {
			this.stamina = stamina;
		}
		
		@Override
		public void useItem(Item item, Sprite usingSprite) {
			((OrganicSprite) usingSprite).addStamina(stamina);
			item.kill();
		}
	}
}
