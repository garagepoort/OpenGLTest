package GameCreator;

import be.davidcorp.applicationLayer.facade.GameFacade;


public class GameCreatorStarter {

	public static void main(String[] args) {
		new GameFacade().startApplication();
		new GameCreatorFrame();
	}
}
