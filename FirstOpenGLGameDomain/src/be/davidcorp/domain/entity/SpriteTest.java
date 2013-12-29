package be.davidcorp.domain.entity;

import org.fest.assertions.Assertions;
import org.junit.Test;

import be.davidcorp.domain.components.InputComponent;


public class SpriteTest {

	@Test
	public void givenSpriteWithInputComponent_whenHasComponent_thenReturnTrue(){
		//given
		Sprite sprite = new Sprite(new InputComponent());
		
		boolean containsComponent = sprite.hasComponent(InputComponent.class);
		
		Assertions.assertThat(containsComponent).isTrue();
	}
	
	@Test
	public void givenSpriteWithoutInputComponent_whenHasComponent_thenReturnFalse(){
		//given
		Sprite sprite = new Sprite();
		
		boolean containsComponent = sprite.hasComponent(InputComponent.class);
		
		Assertions.assertThat(containsComponent).isFalse();
	}
}
