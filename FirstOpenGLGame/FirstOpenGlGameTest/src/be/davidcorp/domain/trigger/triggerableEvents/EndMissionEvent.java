package be.davidcorp.domain.trigger.triggerableEvents;

import static be.davidcorp.domain.trigger.triggerableEvents.TriggerableEventType.ENDMISSION;
import be.davidcorp.domain.mission.Mission;
import be.davidcorp.domain.trigger.Trigger;

public class EndMissionEvent implements TriggerableEvent<Mission> {

	@Override
	public void doTriggerEvent(Trigger trigger, Mission sprite) {
		if(sprite.checkAllCriterias()){
			sprite.completeMission();
		}
	}

	@Override
	public TriggerableEventType getType() {
		return ENDMISSION;
	}

}
