package be.davidcorp.applicationLayer.dto.mapper.spriteToDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import be.davidcorp.applicationLayer.dto.EnemyDTO;
import be.davidcorp.applicationLayer.dto.mapper.EnemyType;
import be.davidcorp.applicationLayer.exception.MapperException;
import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.enemy.Spider;
import be.davidcorp.domain.sprite.organic.enemy.Zombie;

public class OrganicSpriteDTOMapper {

	public static Zombie mapEnemyDTOToZombie(EnemyDTO enemyDTO) throws MapperException {
		if (enemyDTO.enemyType != EnemyType.ZOMBIE) {
			throw new MapperException("This is not a ZOMBIE type");
		}
		try {
			Zombie zombie = new Zombie(enemyDTO.getX(), enemyDTO.getY());
			SpriteDTOMapper.mapSpriteDTOToSprite(zombie, enemyDTO);
			return zombie;
		} catch (SpriteException | IOException e) {
			throw new MapperException(e);
		}
	}

	public static Spider mapEnemyDTOToSpider(EnemyDTO enemyDTO) throws MapperException {
		if (enemyDTO.enemyType != EnemyType.SPIDER) {
			throw new MapperException("This is not a SPIDER type");
		}
		try {
			Spider spider = new Spider(enemyDTO.getX(), enemyDTO.getY());
			SpriteDTOMapper.mapSpriteDTOToSprite(spider, enemyDTO);
			return spider;
		} catch (SpriteException | IOException | MapperException e) {
			throw new MapperException(e);
		}
	}
	
	public static EnemyDTO mapZombieToEnemyDTO(Zombie zombie) throws MapperException {
		EnemyDTO enemyDTO = new EnemyDTO(EnemyType.ZOMBIE);
		SpriteDTOMapper.mapSpriteToSpriteDTO(enemyDTO, zombie);
		return enemyDTO;
	}
	
	public static EnemyDTO mapSpiderToEnemyDTO(Spider spider) throws MapperException {
		EnemyDTO enemyDTO = new EnemyDTO(EnemyType.SPIDER);
		SpriteDTOMapper.mapSpriteToSpriteDTO(enemyDTO, spider);
		return enemyDTO;
	}

	public static List<EnemyDTO> doAutoMappingForEnemies(List<Enemy> enemies) throws MapperException {
		List<EnemyDTO> enemyDTOs = new ArrayList<>();
		for (Enemy enemy : enemies) {
			enemyDTOs.add(doAutoMappingForEnemy(enemy));
		}
		return enemyDTOs;
	}

	public static EnemyDTO doAutoMappingForEnemy(Enemy enemy) throws MapperException {
		EnemyDTO enemyDTO = null;
		if(enemy instanceof Zombie){
			enemyDTO = mapZombieToEnemyDTO((Zombie) enemy);
		}
		if(enemy instanceof Spider){
			enemyDTO = mapSpiderToEnemyDTO((Spider) enemy);
		}
		if(enemyDTO == null){
			throw new MapperException("No mapping found for enemy: " + enemy.getClass().getCanonicalName());
		}
		return enemyDTO;
	}
}
