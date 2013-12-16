package be.davidcorp.loaderSaver.repository;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.weapon.Weapon;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;

public class SpriteRepositoryFactory {

	public static SpriteRepository<?> createRepository(Sprite sprite) {
		if(sprite instanceof Enemy) {
			return new EnemyRepository();
		}
		if(sprite instanceof ConstructionSprite) {
			return new ConstructionSpriteRepository();
		}
		if(sprite instanceof Item) {
			return new ItemRepository();
		}
		if(sprite instanceof Weapon) {
			return new WeaponRepository();
		}
		throw new RuntimeException("No repository for sprite: " + sprite.getClass().getName());
	}

}
