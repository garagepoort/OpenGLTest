package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.item.potion.HealthPotion;

public class HealthPotionTestBuilder extends ItemTestBuilder<HealthPotion>{

	@Override
	protected HealthPotion createInstance() {
		return new HealthPotion();
	}

}
