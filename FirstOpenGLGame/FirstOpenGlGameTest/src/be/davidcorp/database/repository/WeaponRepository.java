package be.davidcorp.database.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import be.davidcorp.database.repository.exception.SpriteRepositoryException;
import be.davidcorp.domain.sprite.item.weapon.Weapon;

import com.google.common.collect.Lists;

public class WeaponRepository implements SpriteRepository<Weapon> {

	protected static Map<Integer, Weapon> weapons = new ConcurrentHashMap<>();


	@Override
	public Weapon getSprite(int id) {
		return weapons.get(id);
	}
	
	@Override
	public Weapon createSprite(Weapon sprite) {
		if(weapons.containsKey(sprite.getID())){
			throw new SpriteRepositoryException("A weapon with this ID already exists. The sprite given to this method cannot have an already existing id.");
		}
		int id = IDGenerator.generateIdForSprites();
		sprite.setID(IDGenerator.generateIdForSprites());
		weapons.put(id, sprite);
		return sprite;
	}
	
	@Override
	public void updateSprite(Weapon spriteToUpdate) {
		throw new UnsupportedOperationException("Not yet implemented");		
	}

	@Override
	public void emptyRepository(){
		weapons.clear();
	}

	@Override
	public void loadSprites(List<Weapon> itemsList) {
		for (Weapon weapon : itemsList) {
			weapons.put(weapon.getID(), weapon);
		}
	}
	
	@Override
	public List<Weapon> getAllSprites() {
		return Lists.newArrayList(weapons.values());
	}
	
	@Override
	public void deleteSprite(int id) {
		weapons.remove(id);
	}
}
