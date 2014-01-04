package be.davidcorp.inputControl;

import org.lwjgl.input.Mouse;

import be.davidcorp.WindDirection;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.metric.Point;
import be.davidcorp.metric.Vector;
import be.davidcorp.view.TranslationManager;
import be.davidcorp.view.game.GameLoop;
import be.davidcorp.view.game.PlayGamePanel;

public class GamePanelInputController extends InputController {

	private PlayerFacade playerFacade = new PlayerFacade();
	private GameFieldFacade gameFieldFacade = new GameFieldFacade();
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
		playGamePanel.togglePickupPanel();
	}

	@Override
	public void on_O_Key_pressed() {
		playGamePanel.toggleEquipmentPanel();
	}

	@Override
	public void on_I_Key_pressed() {
		playGamePanel.toggleInventoryMenu();
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
		boolean inPanel = playGamePanel.handlePanelsOnClick(Mouse.getX(), Mouse.getY(), MouseButton.LEFTBUTTON);
		if (!inPanel && !gameFieldFacade.isGamePaused()) {
			letPlayerAttackInDirectionOfVector();
		}
	}

	@Override
	public void onMouseRightPressed() {
		playGamePanel.handlePanelsOnClick(Mouse.getX(), Mouse.getY(), MouseButton.RIGHTBUTTON);
	}

	private float getPlayerMoveSpeed() {
		return playerFacade.getSpeed() * GameLoop.getSecondsMovedInGame();
	}

	private void letPlayerAttackInDirectionOfVector() {
		float ammoX = Mouse.getX() - TranslationManager.getGameFieldXTranslation();
		float ammoY = Mouse.getY() - TranslationManager.getGameFieldYTranslation();
		Vector vector = new Vector(playerFacade.getCenter(), new Point(ammoX, ammoY, 0));
		playerFacade.letPlayerAttackInDirection(vector);
	}
}
