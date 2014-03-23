package be.davidcorp.domain.sprite;

import static be.davidcorp.domain.sprite.SpriteType.SPRITESPAWNER;
import static be.davidcorp.domain.sprite.SpriteType.ZOMBIE;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.enemy.EnemyFactory;
import be.davidcorp.repository.DefaultSpriteRepository;
import be.davidcorp.repository.SpriteRepository;

public class SpriteSpawner extends Sprite{

	private static final long serialVersionUID = 1L;
	private SpriteRepository spriteRepository = DefaultSpriteRepository.getInstance();
	
	public void spawnSprite(SpriteType spriteType){
		if(spriteType == ZOMBIE){
			Enemy createZombie = EnemyFactory.createZombie(getX(), getY());
			createZombie = (Enemy) spriteRepository.createSprite(createZombie);
			GameFieldManager.getCurrentGameField().addEnemyToWorld(createZombie);
		}
	}
	
	@Override
	public SpriteType getType() {
		return SPRITESPAWNER;
	}

}
