package be.davidcorp.view.game;

import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.engine.GameLogicExecutor;

public class GameLogicExecutorImpl implements GameLogicExecutor{

	@Override
	public void executeGameLogic(int delta) {
		GameFieldFacade.getInstance().updateGameField(delta);
	}

}
