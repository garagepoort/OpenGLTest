package be.davidcorp.applicationLayer.facade;

import static be.davidcorp.domain.game.GameFieldManager.getCurrentGameField;
import be.davidcorp.applicationLayer.dto.EnemyDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.OrganicSpriteDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.enemy.EnemyFactory;
import be.davidcorp.loaderSaver.repository.EnemyRepository;

public class EnemyFacade {
	private EnemyRepository enemyRepository = new EnemyRepository();

	public EnemyDTO createZombie(float x, float y) {
		try {
			Enemy enemy = EnemyFactory.createZombie(x, y);
			enemyRepository.createSprite(enemy);
			return OrganicSpriteDTOMapper.mapEnemyToEnemyDTO(enemy);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public EnemyDTO createSpider(float x, float y) {
		try {
			Enemy spider = EnemyFactory.createSpider(x, y);
			spider = enemyRepository.createSprite(spider);
			return OrganicSpriteDTOMapper.mapEnemyToEnemyDTO(spider);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}
	
	public void updateEnemy(EnemyDTO enemyDTO){
		try {
			Enemy enemy = OrganicSpriteDTOMapper.mapEnemyDTOToEnemy(enemyDTO);
			enemyRepository.updateSprite(enemy);
			getCurrentGameField().updateEnemy(enemy);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public void deleteEnemy(int id) {
		enemyRepository.deleteSprite(id);
		GameFieldManager.getCurrentGameField().removeEnemyFromWorld(id);
	}

}
