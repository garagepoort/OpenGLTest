package be.davidcorp.domain.trigger.triggerableEvents;

import static be.davidcorp.domain.trigger.triggerableEvents.TriggerableEventType.BEGINMISSION;
import be.davidcorp.domain.mission.Mission;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.domain.trigger.Trigger;

public class BeginMissionEvent implements TriggerableEvent<Mission> {

	@Override
	public void doTriggerEvent(Trigger trigger, Mission mission) {
		PlayerManager.getCurrentPlayer().setCurrentMission(mission);
		System.out.println("NEW MISSION STARTED");
	}

	@Override
	public TriggerableEventType getType() {
		return BEGINMISSION;
	}

	
}
