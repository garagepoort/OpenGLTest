package be.davidcorp.loaderSaver.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.loaderSaver.repository.exception.SpriteRepositoryException;

import com.google.common.collect.Lists;

public class ItemRepository implements SpriteRepository<Item>{

	protected static Map<Integer, Item> items = new ConcurrentHashMap<>();

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
		int id = IDGenerator.generateIdForSprites();
		sprite.setID(id);
		items.put(id, sprite);
		return sprite;
	}

	@Override
	public void updateSprite(Item spriteToUpdate) {
		if(!items.containsKey(spriteToUpdate.getID())) throw new SpriteRepositoryException("No sprite with this id in the repository");
		items.put(spriteToUpdate.getID(), spriteToUpdate);
	}
	
	@Override
	public void emptyRepository(){
		items.clear();
	}
	
	@Override
	public List<Item> getAllSprites() {
		return Lists.newArrayList(items.values());
	}
	
	@Override
	public void deleteSprite(int id) {
		items.remove(id);
	}
}
