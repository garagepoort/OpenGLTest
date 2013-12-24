package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.organic.enemy.Zombie;

public class ZombieTestBuilder extends EnemyTestBuilder<Zombie>{

	@Override
	protected Zombie createInstance() {
		return new Zombie();
	}

}
