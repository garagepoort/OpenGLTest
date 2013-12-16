package be.davidcorp.loaderSaver.repository;

import static java.lang.Float.parseFloat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.potion.HealthPotion;
import be.davidcorp.domain.sprite.item.potion.StaminaPotion;
import be.davidcorp.loaderSaver.LoaderException;
import be.davidcorp.loaderSaver.SpriteLoaderSaver;
import be.davidcorp.loaderSaver.SpriteProperty;
import be.davidcorp.loaderSaver.SpriteLoaderSaver.SpriteLoaderEvent;

public class ItemRepository implements SpriteRepository<Item>{

	private static HashMap<Integer, Item> items = new HashMap<Integer, Item>();

	@Override
	public void loadSprites(String type, ArrayList<String> spriteStrings) throws LoaderException {
		for (String itemString : spriteStrings) {
			Item item = new SpriteLoaderSaver<Item>().loadSprite(itemString, new ItemLoaderEvent());
			items.put(item.getID(), item);
			if(type.equals("gamefield")){
				GameFieldManager.getCurrentGameField().addGroundItem(item);
			}
		}
	}

	@Override
	public Item getSprite(int id) {
		return items.get(id);
	}
	
	@Override
	public Item createSprite(Item sprite) {
		int id = IDGenerator.generateIdForSprites(items);
		sprite.setID(id);
		items.put(id, sprite);
		return sprite;
	}

	private static class ItemLoaderEvent extends SpriteLoaderEvent<Item>{

		@Override
		public Item createSprite(Map<SpriteProperty, String> values) throws LoaderException {
			try {
				SpriteType spriteType = SpriteType.valueOf(values.get(SpriteProperty.SPRITETYPE));
				float x = parseFloat(values.get(SpriteProperty.X));
				float y = parseFloat(values.get(SpriteProperty.Y));
				if (spriteType == SpriteType.HEALTHPOTION)
					return new HealthPotion(x, y);
				if (spriteType == SpriteType.STAMINAPOTION)
					return new StaminaPotion(x, y);
				return null;
			} catch (Exception e) {
				throw new LoaderException(e);
			}
		}
		
	}

	@Override
	public void updateSprite(Item spriteToUpdate) throws SpriteException {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	@Override
	public void emptyRepository(){
		items.clear();
	}
}
