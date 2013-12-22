package be.davidcorp.loaderSaver.repository;

import java.util.HashMap;
import java.util.List;

import be.davidcorp.domain.sprite.item.weapon.Weapon;

public class WeaponRepository implements SpriteRepository<Weapon> {

	private static HashMap<Integer, Weapon> weapons = new HashMap<Integer, Weapon>();


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
