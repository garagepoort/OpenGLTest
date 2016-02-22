package be.davidcorp.system;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.unitils.UnitilsJUnit4TestClassRunner;

import be.davidcorp.component.ComponentType;
import be.davidcorp.component.UsableComponent;
import be.davidcorp.component.UsableComponent.OnUseImplementation;
import be.davidcorp.component.UsingComponent;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.test.builder.ConstructionSpriteBuilder;
import be.davidcorp.domain.test.builder.EnemyBuilder;

@RunWith(UnitilsJUnit4TestClassRunner.class)
public class UsingSystemTest {

	@Before
	public void before(){
		UsingSystem.getInstance().clearUsableSprites();
	}
	
//	@Test
	public void givenASpriteWithUsingComponentInUsableRange_whenExecuteSystem_thenOnUseIsTriggered(){
		//given
		UsableComponent usableComponent = mockUsableComponent();
		ConstructionSprite constructionSprite = aUsableConstructionSprite(100, 100, usableComponent);

		Enemy enemy = anEnemyWithUsingComponent();
		
		//when
		UsingSystem.getInstance().executeSystem(enemy, 1);
		
		//then
		Mockito.verify(usableComponent.getImplementation()).onUse(constructionSprite, enemy);
	}
	
	@Test
	public void givenASpriteWithUsingComponentInNotUsableRange_whenExecuteSystem_thenOnUseIsNotTriggered(){
		//given
		UsableComponent usableComponent = mockUsableComponent();
		ConstructionSprite constructionSprite = aUsableConstructionSprite(800f, 800f, usableComponent);
		
		Enemy enemy = anEnemyWithUsingComponent();
		
		//when
		UsingSystem.getInstance().executeSystem(enemy, 1);
		
		//then
		Mockito.verify(usableComponent.getImplementation(), never()).onUse(constructionSprite, enemy);
	}
	public Enemy anEnemyWithUsingComponent() {
		return new EnemyBuilder()
		.withX(100)
		.withY(100)
		.withComponent(mockUsingComponent())
		.build();
	}
	
	@Test
	public void givenASpriteWithoutUsingComponentInUsableRange_whenExecuteSystem_thenOnUseIsNotTriggered(){
		//given
		UsableComponent usableComponent = mockUsableComponent();
		ConstructionSprite constructionSprite = aUsableConstructionSprite(100, 100, usableComponent);
		
		Enemy enemy = anEnemy();
		
		//when
		UsingSystem.getInstance().executeSystem(enemy, 1);
		
		//then
		Mockito.verify(usableComponent.getImplementation(), never()).onUse(constructionSprite, enemy);
	}
	public Enemy anEnemy() {
		Enemy enemy = new EnemyBuilder()
		.withX(100)
		.withY(100)
		.build();
		return enemy;
	}
	public ConstructionSprite aUsableConstructionSprite(float x, float y, UsableComponent usableComponent) {
		ConstructionSprite constructionSprite = new ConstructionSpriteBuilder()
		.withX(x)
		.withY(y)
		.withComponent(usableComponent)
		.build();
		return constructionSprite;
	}

	public UsableComponent mockUsableComponent() {
		UsableComponent usableComponent = mock(UsableComponent.class);
		when(usableComponent.getType()).thenReturn(ComponentType.USABLE_COMPONENT);
		when(usableComponent.getUsableRange()).thenReturn(100);
		OnUseImplementation mock = mock(OnUseImplementation.class);
		when(usableComponent.getImplementation()).thenReturn(mock);
		return usableComponent;
	}

	public UsingComponent mockUsingComponent() {
		UsingComponent usingComponent = mock(UsingComponent.class);
		when(usingComponent.getType()).thenReturn(ComponentType.USING_COMPONENT);
		when(usingComponent.isUsing()).thenReturn(true);
		return usingComponent;
	}
}
