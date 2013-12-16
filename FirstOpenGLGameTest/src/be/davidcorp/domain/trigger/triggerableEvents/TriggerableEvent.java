package be.davidcorp.domain.trigger.triggerableEvents;

import be.davidcorp.domain.trigger.Trigger;
import be.davidcorp.domain.trigger.Triggerable;

public interface TriggerableEvent<SPRITE extends Triggerable> {

	public void doTriggerEvent(Trigger trigger, SPRITE sprite);
	
}
