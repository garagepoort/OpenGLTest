package be.davidcorp.domain.system;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.TestedObject;

import be.davidcorp.component.HealthRegenerationComponent;
import be.davidcorp.domain.sprite.organic.enemy.Zombie;
import be.davidcorp.domain.test.builder.ZombieTestBuilder;

@RunWith(UnitilsJUnit4TestClassRunner.class)
public class HealthRegenerationSystemTest {

	@TestedObject
	private HealthRegenerationSystem healthRegenerationSystem;
	
	@Test
	public void givenASpriteWithHealthRegenerationComponent_whenExecuteSystemAfterHalfSecondMovedInGame_thenHealthIsRegenerated(){
		Zombie zombie = new ZombieTestBuilder().withHealth(50).build();
		HealthRegenerationComponent healthRegenerationComponent = new HealthRegenerationComponent(10);
		zombie.addComponent(healthRegenerationComponent);
		
		healthRegenerationSystem.executeSystem(zombie, 0.5f);
		
		assertThat(zombie.getHealthPoints()).isEqualTo(55);
	}
}
