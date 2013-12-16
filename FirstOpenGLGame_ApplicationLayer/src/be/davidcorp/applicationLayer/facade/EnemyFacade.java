package be.davidcorp.applicationLayer.facade;

import java.io.IOException;

import be.davidcorp.applicationLayer.dto.EnemyDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.OrganicSpriteDTOMapper;
import be.davidcorp.applicationLayer.exception.MapperException;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.sprite.organic.enemy.Spider;
import be.davidcorp.domain.sprite.organic.enemy.Zombie;
import be.davidcorp.loaderSaver.repository.EnemyRepository;
import be.davidcorp.loaderSaver.repository.SpriteRepositoryException;

public class EnemyFacade {
	private EnemyRepository enemyRepository = new EnemyRepository();

	public EnemyDTO createZombie(float x, float y) throws ModelException {
		try {
			Zombie zombie = new Zombie(x, y);
			zombie = (Zombie) enemyRepository.createSprite(zombie);
			return OrganicSpriteDTOMapper.mapZombieToEnemyDTO(zombie);
		} catch (SpriteException | IOException | MapperException | SpriteRepositoryException e) {
			throw new ModelException(e);
		}
	}

	public EnemyDTO createSpider(float x, float y) throws ModelException {
		try {
			Spider spider = new Spider(x, y);
			spider = (Spider) enemyRepository.createSprite(spider);
			return OrganicSpriteDTOMapper.mapSpiderToEnemyDTO(spider);
		} catch (SpriteException | IOException | MapperException | SpriteRepositoryException e) {
			throw new ModelException(e);
		}
	}

}
