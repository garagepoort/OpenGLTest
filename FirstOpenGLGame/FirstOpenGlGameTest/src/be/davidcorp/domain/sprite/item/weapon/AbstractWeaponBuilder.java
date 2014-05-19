package be.davidcorp.domain.sprite.item.weapon;

import be.davidcorp.domain.sprite.item.AbstractItemBuilder;

public abstract class AbstractWeaponBuilder<WEAPON extends Weapon> extends AbstractItemBuilder<WEAPON>{

	private int attackCooldownTime;
	
	@Override
	protected WEAPON buildBasic() {
		WEAPON weapon = super.buildBasic();
		weapon.setAttackCooldown(attackCooldownTime);
		return weapon;
	}
	
	public AbstractWeaponBuilder<WEAPON> withAttackCooldownTime(int attackCooldown){
		this.attackCooldownTime = attackCooldown;
		return this;
	}

}
