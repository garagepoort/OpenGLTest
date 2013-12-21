package be.davidcorp.loaderSaver.repository;

import static be.davidcorp.domain.sprite.SpriteType.ZOMBIE;
import static java.lang.Float.parseFloat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.enemy.Spider;
import be.davidcorp.domain.sprite.organic.enemy.Zombie;
import be.davidcorp.loaderSaver.LoaderException;
import be.davidcorp.loaderSaver.SpriteLoaderSaver;
import be.davidcorp.loaderSaver.SpriteLoaderSaver.SpriteLoaderEvent;
import be.davidcorp.loaderSaver.SpriteProperty;

public class EnemyRepository implements SpriteRepository<Enemy>{

	private static HashMap<Integer, Enemy> enemies = new HashMap<Integer, Enemy>();

	@Override
	public void loadSprites(String type, ArrayList<String> spriteStrings) {
		for (String enemyString : spriteStrings) {
			Enemy enemy = new SpriteLoaderSaver<Enemy>().loadSprite(enemyString, new EnemyLoaderEvent());
			enemies.put(enemy.getID(), enemy);
			if(type.equals("gamefield")){
				GameFieldManager.getCurrentGameField().addEnemyToWorld(enemy);
			}
		}
	}
	
	@Override
	public Enemy getSprite(int id) {
		return enemies.get(id);
	}

	@Override
	public Enemy createSprite(Enemy sprite) {
		if(enemies.containsKey(sprite.getID())){
			throw new SpriteRepositoryException("A weapon with this ID already exists. The sprite given to this method cannot have an already existing id.");
		}
		int id = IDGenerator.generateIdForSprites(enemies);
		sprite.setID(IDGenerator.generateIdForSprites(enemies));
		enemies.put(id, sprite);
		return sprite;
	}
	
	private static class EnemyLoaderEvent extends SpriteLoaderEvent<Enemy>{

		@Override
		public Enemy createSprite(Map<SpriteProperty, String> values)  {
			try {
				SpriteType spriteType = SpriteType.valueOf(values.get(SpriteProperty.SPRITETYPE));
				float x = parseFloat(values.get(SpriteProperty.X));
				float y = parseFloat(values.get(SpriteProperty.Y));
//				int width = parseInt(values.get(SpriteProperty.WIDTH));
//				int height = parseInt(values.get(SpriteProperty.HEIGHT));
				
				if (spriteType == ZOMBIE)
					return new Zombie(x, y);
				if (spriteType == SpriteType.SPIDER)
					return new Spider(x, y);
				
				return null;
			} catch (Exception e) {
				throw new LoaderException(e);
			}
		}

	}

	@Override
	public void updateSprite(Enemy spriteToUpdate) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void emptyRepository(){
		enemies.clear();
	}
}
