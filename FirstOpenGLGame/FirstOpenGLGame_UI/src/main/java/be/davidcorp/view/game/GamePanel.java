package main.java.be.davidcorp.view.game;

import java.io.IOException;
import java.util.Observable;

import be.davidcorp.applicationLayer.facade.PlayerFacade;
import main.java.be.davidcorp.inputControl.InputController;
import main.java.be.davidcorp.view.drawer.GameOverDrawer;
import main.java.be.davidcorp.view.drawer.GamePanelDrawer;

public class GamePanel extends Observable{
	
	private GamePanelDrawer gamePanelDrawer = new GamePanelDrawer();
	private InputController inputController;

	private PlayerFacade playerFacade = new PlayerFacade();
	public void setInputController(InputController inputController) {
		this.inputController = inputController;
	}
	
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

	public void checkInput(){
		inputController.checkInput();
	}

	private void renderPanels() throws IOException {
		gamePanelDrawer.drawGamePanel(this);
	}
	
	protected GamePanelDrawer getGamePanelDrawer() {
		return gamePanelDrawer;
	}
}
