package GameCreator;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import be.davidcorp.applicationLayer.facade.GameFacade;
import be.davidcorp.applicationLayer.facade.PlayerFacade;

public class GameCreatorStarter {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
		new GameFacade().startApplication();
		new PlayerFacade().setSpeed(1);
		new GameCreatorFrame();
	}
}