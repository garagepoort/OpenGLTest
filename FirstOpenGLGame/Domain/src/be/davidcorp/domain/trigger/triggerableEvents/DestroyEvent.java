package be.davidcorp.domain.trigger.triggerableEvents;

import static be.davidcorp.domain.trigger.triggerableEvents.TriggerableEventType.DESTROYEVENT;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.trigger.Trigger;

public class DestroyEvent implements TriggerableEvent<Sprite>{

	@Override
	public void doTriggerEvent(Trigger trigger, Sprite sprite) {
		sprite.kill();
	}

	@Override
	public TriggerableEventType getType() {
		return DESTROYEVENT;
	}

}
