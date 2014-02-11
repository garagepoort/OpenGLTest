package be.davidcorp.applicationLayer.dto;

import be.davidcorp.applicationLayer.dto.mapper.EnemyType;
import be.davidcorp.applicationLayer.facade.OrganicSpriteDTO;

public class EnemyDTO extends OrganicSpriteDTO{

	private EnemyType enemyType;
	
	public EnemyDTO(EnemyType type){
		this.enemyType = type;
	}
	
	public EnemyType getEnemyType() {
		return enemyType;
	}
}
