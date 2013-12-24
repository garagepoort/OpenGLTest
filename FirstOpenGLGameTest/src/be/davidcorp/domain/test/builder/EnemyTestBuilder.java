package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.organic.enemy.Enemy;

public abstract class EnemyTestBuilder<ENEMY extends Enemy> extends OrganicSpriteTestBuilder<ENEMY>{

	private int attackDamage;

	@Override
	protected ENEMY buildBasic() {
		ENEMY enemy = super.buildBasic();
		enemy.setAttackDamage(attackDamage);
		return enemy;
	}

	public EnemyTestBuilder<ENEMY> withAttackDamage(int attackDamage){
		this.attackDamage = attackDamage;
		return this;
	}
}
