package be.davidcorp.loaderSaver.mapper.spriteToString;

import static be.davidcorp.loaderSaver.SpriteProperty.ATTACKDAMAGE;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;

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

}
