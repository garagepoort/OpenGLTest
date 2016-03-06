package be.davidcorp.view.game;

import java.io.IOException;

import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.GameStarterFacade;
import be.davidcorp.engine.Window;
import be.davidcorp.inputControl.GamePanelInputController;
import be.davidcorp.view.TranslationManager;

public class GameStarter {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private static GamePanelInputController inputController;
	private static GameDrawerImpl gameDrawer;
	private static GameLogicExecutorImpl gameLogicExecutor;

	public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
		try {
			PlayGamePanel playGamePanel = new PlayGamePanel();
			init(playGamePanel);

			GameStarterFacade.getInstance().startApplication();

			if(GameFieldFacade.getInstance().isGamefieldInitialized()){
				TranslationManager.initializeBeginTranslation();
			}

//			new GameLoop(new PlayGamePanel(), 800, 600).start();


			new Window(WIDTH, HEIGHT, new be.davidcorp.engine.GameLoop(gameLogicExecutor, gameDrawer), inputController);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void init(PlayGamePanel playGamePanel) {
		inputController = new GamePanelInputController(playGamePanel);
		gameDrawer = new GameDrawerImpl();
		gameLogicExecutor = new GameLogicExecutorImpl();
	}
}