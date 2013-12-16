package be.davidcorp.domain.trigger.triggerableEvents;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.trigger.Trigger;

public class DestroyEvent implements TriggerableEvent<Sprite>{

	@Override
	public void doTriggerEvent(Trigger trigger, Sprite sprite) {
		sprite.kill();
	}

}
