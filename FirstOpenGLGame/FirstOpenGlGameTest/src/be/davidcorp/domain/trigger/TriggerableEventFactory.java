package be.davidcorp.domain.trigger;

import static be.davidcorp.domain.trigger.triggerableEvents.TriggerableEventType.LIGHTSWITCH;
import be.davidcorp.domain.trigger.triggerableEvents.LightSwitchEvent;
import be.davidcorp.domain.trigger.triggerableEvents.SpawnZombieEvent;
import be.davidcorp.domain.trigger.triggerableEvents.TriggerableEvent;
import be.davidcorp.domain.trigger.triggerableEvents.TriggerableEventType;

public class TriggerableEventFactory {

	@SuppressWarnings("rawtypes")
	public static TriggerableEvent createTriggerableEvent(TriggerableEventType triggerableEventType) {
		if(triggerableEventType == LIGHTSWITCH){
			return new LightSwitchEvent();
		}
		if(triggerableEventType == TriggerableEventType.SPAWN_ZOMBIE){
			return new SpawnZombieEvent();
		}
		return null;
	}

	
}
