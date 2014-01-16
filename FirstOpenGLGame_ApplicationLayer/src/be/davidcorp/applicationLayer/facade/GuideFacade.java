package be.davidcorp.applicationLayer.facade;

import be.davidcorp.domain.game.GameFieldManager;

public class GuideFacade {

	public boolean isGuidanceOn() {
		return GameFieldManager.getCurrentGameField().getGuide().isGuidanceOn();
	}

	public String getCurrentGuidance() {
		return GameFieldManager.getCurrentGameField().getGuide().getCurrentGuidance();
	}
}
