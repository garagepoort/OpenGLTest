package be.davidcorp.database;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import be.davidcorp.FileUtility;
import be.davidcorp.database.TriggerLoader;
import be.davidcorp.database.repository.DefaultSpriteRepository;
import be.davidcorp.database.repository.TriggerRepository;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.construction.ConstructionSpriteFactory;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.trigger.Trigger;
import be.davidcorp.domain.trigger.TriggerWhen;
import be.davidcorp.domain.trigger.triggerableEvents.LightSwitchEvent;
import be.davidcorp.domain.trigger.triggerableEvents.TriggerableEvent;


public class TriggerLoaderTest {

	private TriggerLoader triggerLoader;
	private TriggerRepository triggerRepository;
	private File file;
	
	@Mock private DefaultSpriteRepository defaultSpriteRepository;
	@Mock private FileUtility fileUtility;

	@Before
	public void initialize(){
		MockitoAnnotations.initMocks(this);
		triggerRepository = new TriggerRepository();
		triggerLoader = new TriggerLoader();
		triggerLoader.setFileUtility(fileUtility);
		triggerLoader.setDefaultSpriteRepository(defaultSpriteRepository);
		file = new File("resources/test/testfile.txt");
	}
	
	@After
	public void tearDown(){
		file.delete();
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void givenAWallAndALight_whenTriggersLoaded_thenTriggerOnWallLinkedToLight() throws IOException{
		//given
		ConstructionSprite wall = ConstructionSpriteFactory.createWall(0, 0, 1, 1);
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
		
		when(fileUtility.getFileContent(file)).thenReturn(loadedTriggers);
		
		
		//when
		triggerLoader.loadTriggers(file);
		
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
