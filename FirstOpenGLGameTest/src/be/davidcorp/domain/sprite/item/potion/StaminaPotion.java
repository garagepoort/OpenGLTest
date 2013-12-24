package be.davidcorp.domain.sprite.item.potion;

import static be.davidcorp.domain.sprite.SpriteType.STAMINAPOTION;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.organic.OrganicSprite;
import be.davidcorp.texture.TextureBunch;

public class StaminaPotion extends Item {
	private int stamina = 20;

	public StaminaPotion(float x, float y) {
		super(x, y);
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/potions/manapotion.png"));
		setInfoText("The potion gives you 20 Mana.");
	}
	
	public StaminaPotion(){
		this(32, 32);
	}

	@Override
	public void useItem(OrganicSprite organicSprite) {
		organicSprite.addStamina(stamina);
		kill();
	}

	@Override
	public SpriteType getType() {
		return STAMINAPOTION;
	}

}
