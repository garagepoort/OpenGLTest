package be.davidcorp.applicationLayer.dto;

import be.davidcorp.applicationLayer.dto.mapper.EnemyType;
import be.davidcorp.applicationLayer.facade.OrganicSpriteDTO;

public class EnemyDTO extends OrganicSpriteDTO{

	public EnemyType enemyType;
	
	public EnemyDTO(EnemyType enemyType){
		this.enemyType = enemyType;
	}
}
