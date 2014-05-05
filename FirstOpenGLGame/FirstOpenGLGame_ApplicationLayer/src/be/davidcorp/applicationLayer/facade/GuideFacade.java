package be.davidcorp.applicationLayer.facade;

import be.davidcorp.domain.game.CurrentGameFieldManager;

public class GuideFacade {

	public boolean isGuidanceOn() {
		return CurrentGameFieldManager.getCurrentGameField().getGuide().isGuidanceOn();
	}

	public String getCurrentGuidance() {
		return CurrentGameFieldManager.getCurrentGameField().getGuide().getCurrentGuidance();
	}
}
