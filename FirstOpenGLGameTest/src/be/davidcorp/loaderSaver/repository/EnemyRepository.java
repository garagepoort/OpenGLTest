package be.davidcorp.loaderSaver.repository;

import static com.google.common.collect.Maps.newConcurrentMap;

import java.util.List;
import java.util.Map;

import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.loaderSaver.repository.exception.SpriteRepositoryException;

import com.google.common.collect.Lists;

public class EnemyRepository implements SpriteRepository<Enemy>{

	protected static Map<Integer, Enemy> enemies = newConcurrentMap();

	@Override
	public void loadSprites(List<Enemy> enemiesList) {
		for (Enemy enemy : enemiesList) {
			enemies.put(enemy.getID(), enemy);
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
		int id = IDGenerator.generateIdForSprites();
		sprite.setID(IDGenerator.generateIdForSprites());
		enemies.put(id, sprite);
		return sprite;
	}

	@Override
	public void updateSprite(Enemy spriteToUpdate) {
		if(!enemies.containsKey(spriteToUpdate.getID())) throw new SpriteRepositoryException("No sprite with this id in the repository");
		enemies.put(spriteToUpdate.getID(), spriteToUpdate);
	}

	@Override
	public void emptyRepository(){
		enemies.clear();
	}
	
	@Override
	public List<Enemy> getAllSprites() {
		return Lists.newArrayList(enemies.values());
	}
	
	@Override
	public void deleteSprite(int id) {
		enemies.remove(id);
	}
}
