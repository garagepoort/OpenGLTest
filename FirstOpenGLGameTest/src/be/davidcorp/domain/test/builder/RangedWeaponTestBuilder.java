package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.item.weapon.RangedWeapon;

public abstract class RangedWeaponTestBuilder<WEAPON extends RangedWeapon> extends ItemTestBuilder<WEAPON>{
	
	private int maximumAmmo;
	
	@Override
	protected WEAPON buildBasic() {
		WEAPON weapon = super.buildBasic();
		weapon.setMaximumAmountOfAmmo(maximumAmmo);
		return weapon;
	}


	public RangedWeaponTestBuilder<WEAPON> withMaximumAmmo(int maximumAmmo){
		this.maximumAmmo = maximumAmmo;
		return this;
	}



}
