package be.davidcorp.domain.sprite.item.potion;

import java.io.IOException;

import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.organic.OrganicSprite;
import be.davidcorp.texture.TextureBunch;

public class StaminaPotion extends Item {
	private int stamina = 20;

	public StaminaPotion(float x, float y) throws IOException {
		super(x, y);
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/potions/manapotion.png"));
		setInfoText("The potion gives you 20 Mana.");
	}

	@Override
	public void useItem(OrganicSprite organicSprite) {
		organicSprite.addStamina(stamina);
		kill();
	}

}
