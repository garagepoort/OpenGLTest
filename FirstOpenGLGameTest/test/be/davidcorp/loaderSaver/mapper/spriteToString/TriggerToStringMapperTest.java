package be.davidcorp.loaderSaver.mapper.spriteToString;

import static be.davidcorp.domain.trigger.TriggerBuilder.aTrigger;
import static be.davidcorp.domain.trigger.TriggerWhen.ONUSE;
import static be.davidcorp.domain.trigger.triggerableEvents.TriggerableEventType.LIGHTSWITCH;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.test.builder.ConstructionSpriteBuilder;
import be.davidcorp.domain.test.builder.LightTestBuilder;
import be.davidcorp.domain.trigger.Trigger;
import be.davidcorp.domain.trigger.Triggerable;
import be.davidcorp.domain.trigger.triggerableEvents.LightSwitchEvent;
import be.davidcorp.domain.trigger.triggerableEvents.TriggerableEvent;

import com.google.common.collect.Maps;


public class TriggerToStringMapperTest {

	private static final int WALLID = 1;
	private static final int LIGHTID = 2;
	private TriggerToStringMapper mapper;
	
	@Before
	public void init(){
		mapper = new TriggerToStringMapper();
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void givenATriggerWithOneTriggerableThatHasOneEvent_whenMapToString_thenMapToString(){
		//given
		HashMap<Triggerable, List<TriggerableEvent>> triggerables = Maps.newHashMap();
		List<TriggerableEvent> events = new ArrayList<TriggerableEvent>();
		events.add(new LightSwitchEvent());
		triggerables.put(aLight(), events);
		
		Trigger trigger = aTrigger()
							.withID(1)
							.triggeredWhen(ONUSE)
							.withSource(aWall())
							.withAnotherTriggerable(aLight(), new LightSwitchEvent())
							.build();
		
		//when
		String result = mapper.mapTriggerToString(trigger);
		
		//then
		assertThat(result).contains("TRIGGER");
		assertThat(result).contains("ID:1");
		assertThat(result).contains("TRIGGERWHEN:" + ONUSE);
		assertThat(result).contains("SOURCE:" + WALLID);
		assertThat(result).contains("TRIGGERABLE:" + LIGHTID);
		assertThat(result).contains("EVENT:" + LIGHTSWITCH);
		assertThat(result).contains("END");
	}

	public ConstructionSprite aWall() {
		return new ConstructionSpriteBuilder().withID(1).build();
	}

	public Light aLight() {
		return new LightTestBuilder().withID(2).build();
	}
}
