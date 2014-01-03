package be.davidcorp.domain.system;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.TestedObject;

import be.davidcorp.component.HealthRegenerationComponent;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.test.builder.EnemyBuilder;
import be.davidcorp.system.HealthRegenerationSystem;

@RunWith(UnitilsJUnit4TestClassRunner.class)
public class HealthRegenerationSystemTest {

	@TestedObject
	private HealthRegenerationSystem healthRegenerationSystem;
	
	@Test
	public void givenASpriteWithHealthRegenerationComponent_whenExecuteSystemAfterHalfSecondMovedInGame_thenHealthIsRegenerated(){
		Enemy zombie = new EnemyBuilder().withHealth(50).withMaxHealth(100).build();
		HealthRegenerationComponent healthRegenerationComponent = new HealthRegenerationComponent(10);
		zombie.addComponent(healthRegenerationComponent);
		
		healthRegenerationSystem.executeSystem(zombie, 0.5f);
		
		assertThat(zombie.getHealthPoints()).isEqualTo(55);
	}
}
