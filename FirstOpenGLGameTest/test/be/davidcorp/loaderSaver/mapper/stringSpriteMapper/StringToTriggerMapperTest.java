package be.davidcorp.loaderSaver.mapper.stringSpriteMapper;

import static be.davidcorp.domain.trigger.TriggerWhen.ONUSE;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import be.davidcorp.domain.sprite.construction.Wall;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.test.builder.LightTestBuilder;
import be.davidcorp.domain.test.builder.WallTestBuilder;
import be.davidcorp.domain.trigger.Trigger;
import be.davidcorp.domain.trigger.TriggerWhen;
import be.davidcorp.loaderSaver.repository.DefaultSpriteRepository;


public class StringToTriggerMapperTest {

	private StringToTriggerMapper stringToTriggerMapper;
	@Mock
	private DefaultSpriteRepository defaultSpriteRepository;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		stringToTriggerMapper = new StringToTriggerMapper();
		stringToTriggerMapper.setDefaultSpriteRepository(defaultSpriteRepository);
	}
	
	@Test
	public void givenComplexTriggerString_whenMapToTrigger_thenMapperCorrectly(){
		Wall wall = aWall();
		Light light = aLight();
		
		when(defaultSpriteRepository.getSprite(1)).thenReturn(wall);
		when(defaultSpriteRepository.getSprite(2)).thenReturn(light);
		
		String triggerString = "TRIGGER\n"
				+ "ID:1\n"
				+ "SOURCE:1\n"
				+ "TRIGGERWHEN:ONUSE\n"
				+ "TRIGGERABLE:2\n"
				+ "EVENT:LIGHTSWITCH\n"
				+ "END";
		
		Trigger trigger = stringToTriggerMapper.mapStringToTrigger(triggerString);
		
		assertThat(trigger.getID()).isEqualTo(1);
		assertThat(trigger.triggerWhen).isEqualTo(ONUSE);
		assertThat(trigger.getSource()).isEqualTo(wall);
		assertThat(trigger.getTriggerables()).containsOnly(light);
		assertThat(light.getTriggerableEvents().containsKey(trigger));
		assertThat(light.getTriggerableEvents().get(trigger)).hasSize(1);
	}

	private Wall aWall() {
		return new WallTestBuilder()
				.withID(1)
				.build();
	}
	
	private Light aLight() {
		return new LightTestBuilder()
		.withID(2)
		.build();
	}
}
