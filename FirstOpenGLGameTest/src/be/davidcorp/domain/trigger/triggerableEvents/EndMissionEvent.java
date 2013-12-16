package be.davidcorp.domain.trigger.triggerableEvents;

import be.davidcorp.domain.mission.Mission;
import be.davidcorp.domain.trigger.Trigger;

public class EndMissionEvent implements TriggerableEvent<Mission> {

	@Override
	public void doTriggerEvent(Trigger trigger, Mission sprite) {
		if(sprite.checkAllCriterias()){
			sprite.completeMission();
		}
	}

}
