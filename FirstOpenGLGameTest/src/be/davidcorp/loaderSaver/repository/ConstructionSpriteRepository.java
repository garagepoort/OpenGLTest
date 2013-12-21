package be.davidcorp.loaderSaver.repository;

import static be.davidcorp.domain.sprite.SpriteType.WALL;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.construction.Wall;
import be.davidcorp.loaderSaver.LoaderException;
import be.davidcorp.loaderSaver.SpriteLoaderSaver;
import be.davidcorp.loaderSaver.SpriteLoaderSaver.SpriteLoaderEvent;
import be.davidcorp.loaderSaver.SpriteProperty;

public class ConstructionSpriteRepository implements SpriteRepository<ConstructionSprite>{
	
	private static HashMap<Integer, ConstructionSprite> constructionSprites = new HashMap<Integer, ConstructionSprite>();

	@Override
	public void loadSprites(String type, ArrayList<String> spriteStrings) {
		for (String constructionString : spriteStrings) {
			ConstructionSprite constructionSprite = new SpriteLoaderSaver<ConstructionSprite>().loadSprite(constructionString, new ConstructionLoaderEvent());
			constructionSprites.put(constructionSprite.getID(), constructionSprite);
			if(type.equals("gamefield")){
				GameFieldManager.getCurrentGameField().addConstructionItem(constructionSprite);
			}
		}
	}
	
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
	
	private static class ConstructionLoaderEvent extends SpriteLoaderEvent<ConstructionSprite> {
		@Override
		public ConstructionSprite createSprite(Map<SpriteProperty, String> values) {
			try {
				SpriteType spriteType = SpriteType.valueOf(values.get(SpriteProperty.SPRITETYPE));
				float x = parseFloat(values.get(SpriteProperty.X));
				float y = parseFloat(values.get(SpriteProperty.Y));
				int width = parseInt(values.get(SpriteProperty.WIDTH));
				int height = parseInt(values.get(SpriteProperty.HEIGHT));
				
				if (spriteType == WALL)
					return new Wall(x, y, width, height);
				return null;
			} catch (Exception e) {
				throw new LoaderException(e);
			}
		}
		
	}

	@Override
	public void emptyRepository(){
		constructionSprites.clear();
	}

}
