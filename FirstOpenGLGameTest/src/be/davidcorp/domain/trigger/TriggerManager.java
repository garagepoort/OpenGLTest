package be.davidcorp.domain.trigger;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.trigger.triggerableEvents.TriggerableEvent;

public class TriggerManager {

	
	@SuppressWarnings("rawtypes")
	public static <TRIGGERABLE extends Triggerable> Trigger createTriggerOnSprite(int triggerID, TriggerWhen triggerWhen, TRIGGERABLE triggerable, TriggerableEvent event, Sprite sprite) {
		Trigger trigger = new Trigger(triggerWhen);
		trigger.setID(triggerID);
		trigger.addTriggerable(triggerable);
		triggerable.addTriggerableEvent(trigger, event);
		sprite.addTrigger(trigger);
		return trigger;
	}
}
