package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.item.weapon.Pistol;


public class PistolTestBuilder extends RangedWeaponTestBuilder<Pistol>{

	@Override
	protected Pistol createInstance() {
		return new Pistol();
	}


}
