package be.davidcorp.domain.system;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.TestedObject;

import be.davidcorp.component.ComponentType;
import be.davidcorp.component.TimeToLiveComponent;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.system.TimeToLiveSystem;

@RunWith(UnitilsJUnit4TestClassRunner.class)
public class TimeToLiveSystemTest {

	@TestedObject
	private TimeToLiveSystem timeToLiveSystem;
	
	@Test
	public void givenASpriteWithTimeToLiveComponent_whenExecuteSystem_SpriteHasLessTimeToLive(){
		Enemy spider = mock(Enemy.class);
		TimeToLiveComponent component = new TimeToLiveComponent(500);
		Mockito.when(spider.getComponent(ComponentType.TIME_TO_LIVE)).thenReturn(component);
	
		timeToLiveSystem.executeSystem(spider, 1);		
		
		Assertions.assertThat(component.TimeToLiveTime).isEqualTo(499);
		verify(spider, never()).kill();
	}
	
	@Test
	public void givenASpriteWithTimeToLiveAlmostUp_whenExecuteSystem_SpriteGetsKilled(){
		Enemy spider = mock(Enemy.class);
		TimeToLiveComponent component = new TimeToLiveComponent(1);
		Mockito.when(spider.getComponent(ComponentType.TIME_TO_LIVE)).thenReturn(component);
		
		timeToLiveSystem.executeSystem(spider, 1);		
		
		verify(spider, times(1)).kill();
	}
}
