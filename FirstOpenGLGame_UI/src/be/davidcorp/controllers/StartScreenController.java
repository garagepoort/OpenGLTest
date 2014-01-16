package be.davidcorp.controllers;

import static be.davidcorp.view.game.GameLoop.nifty;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.view.TranslationManager;
import be.davidcorp.view.game.GameLoop;
import be.davidcorp.view.ui.nifty.GamePanelScreen;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class StartScreenController implements ScreenController{

		private GameFieldFacade gameFieldFacade = new GameFieldFacade();
		private PlayerFacade playerFacade = new PlayerFacade();
		
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
		
		public void startGame(){
			System.out.println("Game started!!!!");
			
//			nifty.fromXml("niftyLayouts/gamePanelScreen.xml", "start");

			nifty.gotoScreen(GamePanelScreen.KEY);
			gameFieldFacade.initializeGameFieldWithName("FirstDayOfInvasion");
			TranslationManager.initializeBeginTranslation();
		}
		
		public void takeItem(String id){
//			Element element = GamePanelInputController.pickupItemPopup;
			Element itemPanel = GameLoop.nifty.getCurrentScreen().findElementByName(id+"-itemPanel");
			itemPanel.markForRemoval();
			itemPanel.getParent().layoutElements();
			playerFacade.pickUpitem(Integer.parseInt(id));
		}


}
