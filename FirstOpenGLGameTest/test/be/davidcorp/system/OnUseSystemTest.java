package be.davidcorp.system;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.TestedObject;

import be.davidcorp.component.ComponentType;
import be.davidcorp.component.HealthRegenerationComponent;
import be.davidcorp.component.UseComponent;
import be.davidcorp.component.UseComponent.onUseImplementation;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.test.builder.ConstructionSpriteBuilder;

@RunWith(UnitilsJUnit4TestClassRunner.class)
public class OnUseSystemTest {

	@TestedObject
	private OnUseSystem onUseSystem;
	
	@Test
	public void givenASpriteWithUseComponentAndPlayerInViewRange_whenExecuteSystem_thenImplementationIsExecuted(){
		
		ConstructionSprite constructionSprite = new ConstructionSpriteBuilder().withHealth(50).withMaxHealth(100).build();
		UseComponent useComponent = new UseComponent(100, new onUseImplementation() {
			
			@Override
			public void onUse(Sprite sprite) {
				// TODO Auto-generated method stub
				
			}
		});
		constructionSprite.addComponent(useComponent);
		
		onUseSystem.executeSystem(constructionSprite, 0.5f);

		Assert.fail();
	}
}
