package GameCreator;

import javax.swing.JPanel;

import org.lwjgl.input.Mouse;

import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.inputControl.InputController;
import be.davidcorp.view.TranslationManager;

public class GameCreatorPanelInputController extends InputController {

	private GameCreatorPanel gameCreatorPanel;
	private GameFieldFacade gamefieldFacade = new GameFieldFacade();

	
	
	public GameCreatorPanelInputController(GameCreatorPanel gameCreatorPanel) {
		this.gameCreatorPanel = gameCreatorPanel;
	}

	@Override
	public void onMouseLeftPressed() {
		try {
			float pointX = Mouse.getX() - TranslationManager.getGameFieldXTranslation();
			float pointY = Mouse.getY() - TranslationManager.getGameFieldYTranslation();
			if (!mousePushedDown) {
				gameCreatorPanel.selectSelectedSprite(pointX, pointY);
			} else if (gameCreatorPanel.getSelectedSprite() != null) {
				gameCreatorPanel.dragSelectedSprite(pointX, pointY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void on_MOUSELEFT_released() {
//		 gameCreatorPanel.setSelectedSprite(null);
	}

	@Override
	public void onDeletePressed() {
		if (gameCreatorPanel.getSelectedSprite() != null) {
			try {
				gamefieldFacade.removeSpriteFromWorld(gameCreatorPanel.getSelectedSprite());
				gameCreatorPanel.setSelectedSprite(null);
			} catch (Exception e) {
				ErrorHandler.handleError((JPanel) null, e);
			}
		}
	}

}
