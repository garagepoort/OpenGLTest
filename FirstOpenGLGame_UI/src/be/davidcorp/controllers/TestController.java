package be.davidcorp.controllers;

import static be.davidcorp.view.game.GameLoop.nifty;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.view.TranslationManager;
import be.davidcorp.view.game.GameLoop;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class TestController implements ScreenController{

	private GameFieldFacade gameFieldFacade = new GameFieldFacade();
	@Override
	public void bind(Nifty arg0, Screen arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartScreen() {
		// TODO Auto-generated method stub
	}
	
	public void checkClick(){
		System.out.println("clicked!!!!");
		
		nifty.fromXml("niftyLayouts/gamePanelScreen.xml", "start");
		gameFieldFacade.initializeGameFieldWithName("FirstDayOfInvasion");
		TranslationManager.initializeBeginTranslation();
	}

}
