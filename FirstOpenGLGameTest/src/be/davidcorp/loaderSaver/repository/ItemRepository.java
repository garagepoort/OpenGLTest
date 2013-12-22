package be.davidcorp.loaderSaver.repository;

import java.util.HashMap;
import java.util.List;

import be.davidcorp.domain.sprite.item.Item;

public class ItemRepository implements SpriteRepository<Item>{

	private static HashMap<Integer, Item> items = new HashMap<Integer, Item>();

	@Override
	public void loadSprites(List<Item> itemsList) {
		for (Item item : itemsList) {
			items.put(item.getID(), item);
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

	@Override
	public void updateSprite(Item spriteToUpdate) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	@Override
	public void emptyRepository(){
		items.clear();
	}
}
