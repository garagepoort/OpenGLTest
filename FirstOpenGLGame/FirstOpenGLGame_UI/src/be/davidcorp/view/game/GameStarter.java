package be.davidcorp.view.game;

import be.davidcorp.applicationLayer.facade.GameFacade;

public class GameStarter {

	public static void main(String[] args) {
		try {
			new GameFacade().startApplication();
			new GameLoop(new PlayGamePanel(), 800, 600).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}