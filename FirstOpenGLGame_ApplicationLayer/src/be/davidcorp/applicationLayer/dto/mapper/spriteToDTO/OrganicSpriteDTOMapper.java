package be.davidcorp.applicationLayer.dto.mapper.spriteToDTO;

import java.util.ArrayList;
import java.util.List;

import be.davidcorp.applicationLayer.dto.EnemyDTO;
import be.davidcorp.applicationLayer.dto.mapper.EnemyType;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.enemy.EnemyFactory;

public class OrganicSpriteDTOMapper {

	public static EnemyDTO mapEnemyToEnemyDTO(Enemy enemy)  {
		EnemyDTO enemyDTO = new EnemyDTO(EnemyType.valueOf(enemy.getType().toString()));
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

	public static Enemy mapEnemyDTOToEnemy(EnemyDTO enemyDTO) {
		SpriteType spriteType = SpriteType.valueOf(enemyDTO.getEnemyType().toString());
		Enemy enemy = EnemyFactory.createFromType(0, 0, spriteType);
		SpriteDTOMapper.mapSpriteDTOToSprite(enemy, enemyDTO);
		return enemy;
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
