package be.davidcorp.loaderSaver.repository;

import static com.google.common.collect.Maps.newConcurrentMap;

import java.util.List;
import java.util.Map;

import be.davidcorp.domain.sprite.item.weapon.Weapon;
import be.davidcorp.loaderSaver.repository.exception.SpriteRepositoryException;

public class WeaponRepository implements SpriteRepository<Weapon> {

	private static Map<Integer, Weapon> weapons = newConcurrentMap();


	@Override
	public Weapon getSprite(int id) {
		return weapons.get(id);
	}
	
	@Override
	public Weapon createSprite(Weapon sprite) {
		if(weapons.containsKey(sprite.getID())){
			throw new SpriteRepositoryException("A weapon with this ID already exists. The sprite given to this method cannot have an already existing id.");
		}
		int id = IDGenerator.generateIdForSprites(weapons);
		sprite.setID(IDGenerator.generateIdForSprites(weapons));
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
}
