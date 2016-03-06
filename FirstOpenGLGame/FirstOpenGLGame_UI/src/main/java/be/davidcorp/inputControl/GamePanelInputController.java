package be.davidcorp.inputControl;

import java.util.List;

import be.davidcorp.WindDirection;
import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.engine.FpsCalculator;
import be.davidcorp.engine.InputController;
import be.davidcorp.engine.MousePosition;
import be.davidcorp.metric.Point;
import be.davidcorp.metric.Vector;
import be.davidcorp.view.TranslationManager;
import be.davidcorp.view.game.PlayGamePanel;

public class GamePanelInputController extends InputController {

	private PlayerFacade playerFacade = new PlayerFacade();
	private GameFieldFacade gameFieldFacade = GameFieldFacade.getInstance();
	private PlayGamePanel playGamePanel;

	public GamePanelInputController(PlayGamePanel panel) {
		this.playGamePanel = panel;
	}

	@Override
	public void on_P_Key_pressed() {
		gameFieldFacade.togglePause();
	}

	@Override
	public void on_F_Key_pressed() {
		if (!gameFieldFacade.isGamePaused()) {
			playerFacade.turnFlaslightOnOff();
		}
	}

	@Override
	public void on_E_Key_pressed() {
		if (!gameFieldFacade.isGamePaused()) {
			gameFieldFacade.useInRangeOfPlayer();
		}
	}

	@Override
	public void on_G_Key_pressed() {
		// playGamePanel.togglePickupPanel();
//		List<ItemDTO> itemsThatCanBePickedUpByPlayer = gameFieldFacade.getItemsThatCanBePickedUpByPlayer();
//		if(pickupItemPopup !=null){
//			nifty.closePopup(pickupItemPopup.getId());
//			pickupItemPopup=null;
//		}else if(!itemsThatCanBePickedUpByPlayer.isEmpty()){
//			PickUpItemPopUp.createPopUp(itemsThatCanBePickedUpByPlayer);
//			pickupItemPopup = nifty.createPopup("popupItem");
//			nifty.showPopup(nifty.getCurrentScreen(), pickupItemPopup.getId(), null);
//			System.out.println(nifty.getCurrentScreen().debugOutput());
//		}
	}
	@Override
	public void on_O_Key_pressed() {
		// pickUpItemPopup
	}

	@Override
	public void on_I_Key_pressed() {
		List<ItemDTO> itemsThatCanBePickedUpByPlayer = playerFacade.getInventoryItems();
//		if(pickupItemPopup !=null){
//			nifty.closePopup(pickupItemPopup.getId());
//			pickupItemPopup=null;
//		}else if(!itemsThatCanBePickedUpByPlayer.isEmpty()){
//			PickUpItemPopUp.createPopUp(itemsThatCanBePickedUpByPlayer);
//			pickupItemPopup = nifty.createPopup("popupItem");
//			nifty.showPopup(nifty.getCurrentScreen(), pickupItemPopup.getId(), null);
//			System.out.println(nifty.getCurrentScreen().debugOutput());
//		}
	}

	@Override
	public void on_0_Key() {
		if (!gameFieldFacade.isGamePaused()) {
			playerFacade.useSkill(0);
		}
	}

	@Override
	public void onSpaceKey() {
		if (!gameFieldFacade.isGamePaused()) {
			letPlayerAttackInDirectionOfVector();
		}
	}

	@Override
	public void on_LEFT_Key() {
		if (!gameFieldFacade.isGamePaused()) {
			playerFacade.movePlayer(WindDirection.WEST, getPlayerMoveSpeed());
			if (playerFacade.isPlayerMoving()) {
				TranslationManager.initializeLeftTranslation();
			}
		}
	}

	@Override
	public void on_RIGHT_Key() {
		if (!gameFieldFacade.isGamePaused()) {
			playerFacade.movePlayer(WindDirection.EAST, getPlayerMoveSpeed());
			if (playerFacade.isPlayerMoving()) {
				TranslationManager.initializeRightTranslation();
			}
		}
	}

	@Override
	public void on_UP_Key() {
		if (!gameFieldFacade.isGamePaused()) {
			playerFacade.movePlayer(WindDirection.NORTH, getPlayerMoveSpeed());
			if (playerFacade.isPlayerMoving()) {
				TranslationManager.initializeUpTranslation();
			}
		}
	}

	@Override
	public void on_DOWN_Key() {
		if (!gameFieldFacade.isGamePaused()) {
			playerFacade.movePlayer(WindDirection.SOUTH, getPlayerMoveSpeed());
			if (playerFacade.isPlayerMoving()) {
				TranslationManager.initializeDownTranslation();
			}
		}
	}

	@Override
	public void onMouseLeftPressed() {
		if (!gameFieldFacade.isGamePaused()) {
			letPlayerAttackInDirectionOfVector();
		}
	}

	@Override
	public void onMouseRightPressed() {
	}

	private float getPlayerMoveSpeed() {
		return playerFacade.getSpeed() * FpsCalculator.getInstance().getDelta();
	}

	private void letPlayerAttackInDirectionOfVector() {
		float ammoX = (float) (MousePosition.X - TranslationManager.getGameFieldXTranslation());
		float ammoY = (float) (MousePosition.Y - TranslationManager.getGameFieldYTranslation());
		Vector vector = new Vector(playerFacade.getCenter(), new Point(ammoX, ammoY, 0));
		playerFacade.letPlayerAttackInDirection(vector);
	}
}
