package be.davidcorp.domain.sprite;

import static be.davidcorp.domain.sprite.SpriteType.SPRITESPAWNER;
import static be.davidcorp.domain.sprite.SpriteType.ZOMBIE;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.enemy.EnemyFactory;
import be.davidcorp.loaderSaver.repository.EnemyRepository;

public class SpriteSpawner extends Sprite{

	private EnemyRepository enemyRepository = new EnemyRepository();
	
	public SpriteSpawner(){
		setSpriteType(SPRITESPAWNER);
	}
	public void spawnSprite(SpriteType spriteType){
		if(spriteType == ZOMBIE){
			Enemy createZombie = EnemyFactory.createZombie(getX(), getY());
			createZombie = enemyRepository.createSprite(createZombie);
			GameFieldManager.getCurrentGameField().addEnemyToWorld(createZombie);
		}
	}

}
