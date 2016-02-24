package be.davidcorp.view.game;

import java.io.IOException;

import be.davidcorp.applicationLayer.facade.GameStarterFacade;

public class GameStarter {

	public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
		NativeLoader.loadNatives();
		try {
			new GameStarterFacade().startApplication();
			new GameLoop(new PlayGamePanel(), 800, 600).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}