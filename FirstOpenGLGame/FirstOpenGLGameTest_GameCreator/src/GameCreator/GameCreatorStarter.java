package GameCreator;

import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import static javax.swing.UIManager.setLookAndFeel;

import javax.swing.UnsupportedLookAndFeelException;

import be.davidcorp.applicationLayer.facade.GameStarterFacade;
import be.davidcorp.applicationLayer.facade.PlayerFacade;

public class GameCreatorStarter {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setLookAndFeel(getSystemLookAndFeelClassName());
		new GameStarterFacade().startGameCreatorApplication();
		new PlayerFacade().setSpeed(1);
		new GameCreatorFrame();
	}
}