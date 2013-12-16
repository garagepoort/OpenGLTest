package be.davidcorp.loaderSaver.repository;

import static be.davidcorp.domain.sprite.SpriteType.PISTOL;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.item.weapon.Pistol;
import be.davidcorp.domain.sprite.item.weapon.Weapon;
import be.davidcorp.loaderSaver.LoaderException;
import be.davidcorp.loaderSaver.SpriteLoaderSaver;
import be.davidcorp.loaderSaver.SpriteProperty;
import be.davidcorp.loaderSaver.SpriteLoaderSaver.SpriteLoaderEvent;

public class WeaponRepository implements SpriteRepository<Weapon> {

	private static HashMap<Integer, Weapon> weapons = new HashMap<Integer, Weapon>();

	@Override
	public void loadSprites(String type, ArrayList<String> spriteStrings) throws LoaderException {
		for (String weaponString : spriteStrings) {
			Weapon weapon = new SpriteLoaderSaver<Weapon>().loadSprite(weaponString, new WeaponLoaderEvent());
			weapons.put(weapon.getID(), weapon);
			if(type.equals("gamefield")){
				GameFieldManager.getCurrentGameField().addGroundItem(weapon);
			}
		}
	}

	@Override
	public Weapon getSprite(int id) {
		return weapons.get(id);
	}
	
	@Override
	public Weapon createSprite(Weapon sprite) throws SpriteRepositoryException {
		if(weapons.containsKey(sprite.getID())){
			throw new SpriteRepositoryException("A weapon with this ID already exists. The sprite given to this method cannot have an already existing id.");
		}
		int id = IDGenerator.generateIdForSprites(weapons);
		sprite.setID(IDGenerator.generateIdForSprites(weapons));
		weapons.put(id, sprite);
		return sprite;
	}
	
	private static class WeaponLoaderEvent extends SpriteLoaderEvent<Weapon> {

		@Override
		public Weapon createSprite(Map<SpriteProperty, String> values) throws LoaderException {
			try {
				SpriteType spriteType = SpriteType.valueOf(values.get(SpriteProperty.SPRITETYPE));
				float x = parseFloat(values.get(SpriteProperty.X));
				float y = parseFloat(values.get(SpriteProperty.Y));
				int kogels = parseInt(values.get(SpriteProperty.AANTAL_BULLETS));

				if (spriteType == PISTOL)
					return new Pistol(x, y, kogels);
				return null;
			} catch (Exception e) {
				throw new LoaderException(e);
			}
		}

	}

	@Override
	public void updateSprite(Weapon spriteToUpdate) throws SpriteException {
		throw new UnsupportedOperationException("Not yet implemented");		
	}

	@Override
	public void emptyRepository(){
		weapons.clear();
	}
}
