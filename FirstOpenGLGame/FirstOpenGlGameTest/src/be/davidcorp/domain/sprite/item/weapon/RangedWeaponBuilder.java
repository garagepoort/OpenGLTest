package be.davidcorp.domain.sprite.item.weapon;

public class RangedWeaponBuilder extends AbstractWeaponBuilder<RangedWeapon>{

	private int maxAmountOfAmmo;
	
	@Override
	protected RangedWeapon buildBasic() {
		RangedWeapon  weapon = super.buildBasic();
		weapon.setMaximumAmountOfAmmo(maxAmountOfAmmo);
		return weapon;
	}
	
	public RangedWeaponBuilder withMaxAmountOfAmmo(int maxAmountOfAmmo){
		this.maxAmountOfAmmo = maxAmountOfAmmo;
		return this;
	}
	
	@Override
	protected RangedWeapon createInstance() {
		return new RangedWeapon(1,1,1,1,1);
	}

}
