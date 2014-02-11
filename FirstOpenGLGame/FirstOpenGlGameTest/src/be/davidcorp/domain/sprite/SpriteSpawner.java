package be.davidcorp.domain.sprite;

import static be.davidcorp.domain.sprite.SpriteType.SPRITESPAWNER;
import static be.davidcorp.domain.sprite.SpriteType.ZOMBIE;
import be.davidcorp.database.repository.EnemyRepository;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.enemy.EnemyFactory;

public class SpriteSpawner extends Sprite{

	private static final long serialVersionUID = 1L;
	private EnemyRepository enemyRepository = new EnemyRepository();
	
	public void spawnSprite(SpriteType spriteType){
		if(spriteType == ZOMBIE){
			Enemy createZombie = EnemyFactory.createZombie(getX(), getY());
			createZombie = enemyRepository.createSprite(createZombie);
			GameFieldManager.getCurrentGameField().addEnemyToWorld(createZombie);
		}
	}
	
	@Override
	public SpriteType getType() {
		return SPRITESPAWNER;
	}

}
