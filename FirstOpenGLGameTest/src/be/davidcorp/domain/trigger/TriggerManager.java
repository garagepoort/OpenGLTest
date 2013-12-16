package be.davidcorp.domain.trigger;

import be.davidcorp.domain.trigger.triggerableEvents.TriggerableEvent;

public class TriggerManager {

	
	public static <TRIGGERABLE extends Triggerable> Trigger createTrigger(int id, TriggerWhen triggerWhen, TRIGGERABLE triggerable, TriggerableEvent<TRIGGERABLE> triggerableEvent){
		Trigger trigger = new Trigger(triggerWhen);
		trigger.setID(id);
		trigger.addTriggerable(triggerable);
		triggerable.addTriggerableEvent(trigger, triggerableEvent);
		return trigger;
	}
}
