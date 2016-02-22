package be.davidcorp.domain.trigger.triggerableEvents;

import static be.davidcorp.domain.trigger.triggerableEvents.TriggerableEventType.LIGHTSWITCH;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.trigger.Trigger;

public class LightSwitchEvent implements TriggerableEvent<Light>{

	@Override
	public void doTriggerEvent(Trigger trigger, Light sprite) {
		sprite.setLightOn(!sprite.isLightOn());
	}

	@Override
	public TriggerableEventType getType() {
		return LIGHTSWITCH;
	}

}
