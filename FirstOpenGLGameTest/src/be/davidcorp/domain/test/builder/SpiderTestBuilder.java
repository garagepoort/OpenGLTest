package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.organic.enemy.Spider;

public class SpiderTestBuilder extends EnemyTestBuilder<Spider>{

	@Override
	protected Spider createInstance() {
		return new Spider();
	}

}
