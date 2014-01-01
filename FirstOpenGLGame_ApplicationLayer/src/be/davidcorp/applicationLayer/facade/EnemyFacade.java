package be.davidcorp.applicationLayer.facade;

import be.davidcorp.applicationLayer.dto.EnemyDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.OrganicSpriteDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.sprite.organic.enemy.Spider;
import be.davidcorp.domain.sprite.organic.enemy.Zombie;
import be.davidcorp.loaderSaver.repository.EnemyRepository;

public class EnemyFacade {
	private EnemyRepository enemyRepository = new EnemyRepository();

	public EnemyDTO createZombie(float x, float y) {
		try {
			Zombie zombie = new Zombie(x, y);
			zombie = (Zombie) enemyRepository.createSprite(zombie);
			return OrganicSpriteDTOMapper.mapZombieToEnemyDTO(zombie);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public EnemyDTO createSpider(float x, float y) {
		try {
			Spider spider = new Spider(x, y);
			spider = (Spider) enemyRepository.createSprite(spider);
			return OrganicSpriteDTOMapper.mapSpiderToEnemyDTO(spider);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public void deleteEnemy(int id) {
		// TODO Auto-generated method stub
		
	}

}
