package be.davidcorp.loaderSaver;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.construction.Wall;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.trigger.Trigger;
import be.davidcorp.domain.trigger.TriggerWhen;
import be.davidcorp.domain.trigger.triggerableEvents.LightSwitchEvent;
import be.davidcorp.domain.trigger.triggerableEvents.TriggerableEvent;
import be.davidcorp.loaderSaver.repository.DefaultSpriteRepository;
import be.davidcorp.loaderSaver.repository.TriggerRepository;


public class TriggerLoaderTest {

	private TriggerLoader triggerLoader;
	private TriggerRepository triggerRepository;
	
	@Mock private DefaultSpriteRepository defaultSpriteRepository;

	@Before
	public void initialize(){
		MockitoAnnotations.initMocks(this);
		triggerLoader = new TriggerLoader();
		triggerRepository = new TriggerRepository();
		
		triggerLoader.setDefaultSpriteRepository(defaultSpriteRepository);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void givenAWallAndALight_whenTriggersLoaded_thenTriggerOnWallLinkedToLight() throws SpriteException{
		//given
		Wall wall = new Wall(0, 0, 1, 1);
		Light light = new Light(0, 0, new Color(0, 0, 255), 10, true);
		
		when(defaultSpriteRepository.getSprite(1)).thenReturn(wall);
		when(defaultSpriteRepository.getSprite(2)).thenReturn(light);
		
		String loadedTriggers = 
				"TRIGGER\n"
				+ "ID:1\n"
				+ "SPRITE:1\n"
				+ "TRIGGERWHEN:ONUSE\n"
				+ "TRIGGERABLE:2\n"
				+ "EVENT:LIGHTSWITCH\n"
				+ "END";
		
		//when
		triggerLoader.loadTriggers(loadedTriggers);
		
		//then
		Trigger createdTrigger = triggerRepository.getTrigger(1);
		assertThat(createdTrigger).isNotNull();
		assertThat(createdTrigger.triggerWhen).isEqualTo(TriggerWhen.ONUSE);
		assertThat(createdTrigger.getTriggerables()).contains(light);
		
		assertThat(wall.getAllTriggers()).contains(createdTrigger);
		
		assertThat(light.getTriggerableEvents().get(createdTrigger)).isNotNull();
		
		TriggerableEvent createdEvent = light.getTriggerableEvents().get(createdTrigger).get(0);
		assertThat(createdEvent).isNotNull();
		assertThat(createdEvent).isInstanceOf(LightSwitchEvent.class);
	}
}