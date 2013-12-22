package be.davidcorp.loaderSaver.repository;

import java.util.HashMap;
import java.util.List;

import be.davidcorp.domain.sprite.construction.ConstructionSprite;

public class ConstructionSpriteRepository implements SpriteRepository<ConstructionSprite>{
	
	private static HashMap<Integer, ConstructionSprite> constructionSprites = new HashMap<Integer, ConstructionSprite>();

	@Override
	public ConstructionSprite getSprite(int id) {
		return constructionSprites.get(id);
	}

	@Override
	public void updateSprite(ConstructionSprite constructionSpriteToUpdate) {
		ConstructionSprite constructionSprite = constructionSprites.get(constructionSpriteToUpdate.getID());
		constructionSprite.setX(constructionSpriteToUpdate.getX());
		constructionSprite.setY(constructionSpriteToUpdate.getY());
		constructionSprite.setWidth(constructionSpriteToUpdate.getWidth());
		constructionSprite.setHeight(constructionSpriteToUpdate.getHeight());
	}
	
	@Override
	public ConstructionSprite createSprite(ConstructionSprite sprite) {
		if(constructionSprites.containsKey(sprite.getID())){
			throw new SpriteRepositoryException("A weapon with this ID already exists. The sprite given to this method cannot have an already existing id.");
		}
		int id = IDGenerator.generateIdForSprites(constructionSprites);
		sprite.setID(IDGenerator.generateIdForSprites(constructionSprites));
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

}
