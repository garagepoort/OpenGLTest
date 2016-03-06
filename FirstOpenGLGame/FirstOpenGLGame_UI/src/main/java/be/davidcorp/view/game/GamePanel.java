package be.davidcorp.view.game;

import java.io.IOException;
import java.util.Observable;

import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.view.drawer.GameOverDrawer;
import be.davidcorp.view.drawer.GamePanelDrawer;

public abstract class GamePanel extends Observable{
	
	private GamePanelDrawer gamePanelDrawer = new GamePanelDrawer();

	private PlayerFacade playerFacade = new PlayerFacade();

	public void render() throws IOException {
//		removeClosedPanels();
		renderPanels();
		renderGameOverPanelIfPlayerDeadOrGameWonIfMissionCompleted();
	}
	
	private void renderGameOverPanelIfPlayerDeadOrGameWonIfMissionCompleted() {
		if(!playerFacade.isPlayerAlive()){
			GameOverDrawer.drawGameOver();
		}else if(playerFacade.isCurrentMissionCompleted()){
			GameWonDrawer.drawGameWon();
		}
	}

	private void renderPanels() throws IOException {
		gamePanelDrawer.drawGamePanel(this);
	}

	protected GamePanelDrawer getGamePanelDrawer() {
		return gamePanelDrawer;
	}
}
