package be.davidcorp.applicationLayer.dto.mapper.spriteToDTO;

import java.util.ArrayList;
import java.util.List;

import be.davidcorp.applicationLayer.dto.EnemyDTO;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;

public class OrganicSpriteDTOMapper {

//	public static Zombie mapEnemyDTOToZombie(EnemyDTO enemyDTO)  {
//		if (enemyDTO.enemyType != EnemyType.ZOMBIE) {
//			throw new MapperException("This is not a ZOMBIE type");
//		}
//		Zombie zombie = new Zombie(enemyDTO.getX(), enemyDTO.getY());
//		SpriteDTOMapper.mapSpriteDTOToSprite(zombie, enemyDTO);
//		return zombie;
//	}
//
//	public static Spider mapEnemyDTOToSpider(EnemyDTO enemyDTO)  {
//		if (enemyDTO.enemyType != EnemyType.SPIDER) {
//			throw new MapperException("This is not a SPIDER type");
//		}
//		try {
//			Spider spider = new Spider(enemyDTO.getX(), enemyDTO.getY());
//			SpriteDTOMapper.mapSpriteDTOToSprite(spider, enemyDTO);
//			return spider;
//		} catch (Exception e) {
//			throw new MapperException(e);
//		}
//	}

	public static EnemyDTO mapEnemyToEnemyDTO(Enemy enemy)  {
		EnemyDTO enemyDTO = new EnemyDTO();
		SpriteDTOMapper.mapSpriteToSpriteDTO(enemyDTO, enemy);
		return enemyDTO;
	}

	public static List<EnemyDTO> mapEnemiesToDTOs(List<Enemy> enemies)  {
		List<EnemyDTO> enemyDTOs = new ArrayList<>();
		for (Enemy enemy : enemies) {
			enemyDTOs.add(mapEnemyToEnemyDTO(enemy));
		}
		return enemyDTOs;
	}

	public static Enemy mapEnemyDTOToEnemy(EnemyDTO spriteDTO) {
		
		return null;
	}

//	public static Enemy doAutoMappingForEnemyDTO(EnemyDTO spriteDTO)  {
//		if (spriteDTO.enemyType == EnemyType.SPIDER) {
//			return mapEnemyDTOToSpider(spriteDTO);
//		}
//		if (spriteDTO.enemyType == EnemyType.ZOMBIE) {
//			return mapEnemyDTOToZombie(spriteDTO);
//		}
//		return null;
//	}
}
