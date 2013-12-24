package be.davidcorp.loaderSaver.mapper.spriteToString;

import static be.davidcorp.loaderSaver.SpriteProperty.ATTACKDAMAGE;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.enemy.Spider;
import be.davidcorp.domain.sprite.organic.enemy.Zombie;

public class EnemyToStringMapper<ENEMY extends Enemy> extends SpriteToStringMapper<ENEMY>{

	@Override
	public String getSpriteClass() {
		return "ENEMY";
	}

	@Override
	public void addProperties() {
		super.addProperties();
		properties.put(ATTACKDAMAGE, valueToString(sprite.getAttackDamage()));
	}

	@Override
	public String getSpriteType() {
		if(sprite instanceof Spider){
			return "SPIDER";
		}
		if(sprite instanceof Zombie){
			return "ZOMBIE";
		}
		throw new MapperException("No enemy found for type: " + sprite);
	}
	
	

}
