package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.organic.enemy.Enemy;

public class EnemyBuilder extends OrganicSpriteBuilder<Enemy>{

	private int attackDamage = 100;
	private int viewRange = 50;
	private int viewRangeOffset = 150;
	private int attackRange;

	@Override
	protected Enemy buildBasic() {
		Enemy enemy = super.buildBasic();
		enemy.setAttackDamage(attackDamage);
		enemy.setDefaultViewRange(viewRange);
		enemy.setViewRangeOffset(viewRangeOffset);
		enemy.setAttackRange(attackRange);
		return enemy;
	}

	public EnemyBuilder withAttackDamage(int attackDamage){
		this.attackDamage = attackDamage;
		return this;
	}
	
	public EnemyBuilder withViewRange(int viewRange){
		this.viewRange = viewRange;
		return this;
	}
	
	public EnemyBuilder withViewRangeOffset(int viewRangeOffset){
		this.viewRangeOffset = viewRangeOffset;
		return this;
	}
	
	public EnemyBuilder withAttackRange(int attackRange){
		this.attackRange = attackRange;
		return this;
	}
	
	@Override
	protected Enemy createInstance() {
		return new Enemy();
	}
}
