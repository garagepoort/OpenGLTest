package be.davidcorp.applicationLayer.facade;

import javax.inject.Inject;
import javax.inject.Named;

import be.davidcorp.applicationLayer.dto.EnemyDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.OrganicSpriteDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.enemy.EnemyFactory;
import be.davidcorp.repository.SpriteRepository;

@Named
public class EnemyFacade {
	@Inject
	private SpriteRepository spriteRepository;

	public EnemyDTO createZombie(float x, float y) {
		try {
			Enemy enemy = EnemyFactory.createZombie(x, y);
			spriteRepository.createSprite(enemy);
			return OrganicSpriteDTOMapper.mapEnemyToEnemyDTO(enemy);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public EnemyDTO createSpider(float x, float y) {
		try {
			Enemy spider = EnemyFactory.createSpider(x, y);
			spriteRepository.createSprite(spider);
			return OrganicSpriteDTOMapper.mapEnemyToEnemyDTO(spider);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}
	
	public void updateEnemy(EnemyDTO enemyDTO){
		try {
			Enemy enemy = OrganicSpriteDTOMapper.mapEnemyDTOToEnemy(enemyDTO);
			spriteRepository.updateSprite(enemy);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public void deleteEnemy(int id) {
		spriteRepository.deleteSprite(id);
	}

}
