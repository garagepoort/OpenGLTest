package be.davidcorp.loaderSaver.repository;

import static com.google.common.collect.Maps.newConcurrentMap;

import java.util.List;
import java.util.Map;

import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.loaderSaver.repository.exception.SpriteRepositoryException;

import com.google.common.collect.Lists;

public class ConstructionSpriteRepository implements SpriteRepository<ConstructionSprite>{
	
	protected static Map<Integer, ConstructionSprite> constructionSprites = newConcurrentMap();

	@Override
	public ConstructionSprite getSprite(int id) {
		return constructionSprites.get(id);
	}

	@Override
	public void updateSprite(ConstructionSprite constructionSpriteToUpdate) {
		constructionSprites.put(constructionSpriteToUpdate.getID(), constructionSpriteToUpdate);
	}
	
	@Override
	public ConstructionSprite createSprite(ConstructionSprite sprite) {
		if(constructionSprites.containsKey(sprite.getID())){
			throw new SpriteRepositoryException("A constructionsprite with this ID already exists. The sprite given to this method cannot have an already existing id.");
		}
		int id = IDGenerator.generateIdForSprites();
		sprite.setID(IDGenerator.generateIdForSprites());
		constructionSprites.put(id, sprite);
		return sprite;
	}

	@Override
	public void emptyRepository(){
		constructionSprites.clear();
	}

	@Override
	public void loadSprites(List<ConstructionSprite> sprites) {
		for (ConstructionSprite constructionSprite : sprites) {
			constructionSprites.put(constructionSprite.getID(), constructionSprite);
		}
	}

	@Override
	public List<ConstructionSprite> getAllSprites() {
		return Lists.newArrayList(constructionSprites.values());
	}

	@Override
	public void deleteSprite(int id) {
		constructionSprites.remove(id);
	}

}
