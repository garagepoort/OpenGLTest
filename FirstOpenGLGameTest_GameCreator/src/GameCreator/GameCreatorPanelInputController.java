package GameCreator;

import javax.swing.JPanel;

import org.lwjgl.input.Mouse;

import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.applicationLayer.facade.ConstructionSpriteFacade;
import be.davidcorp.applicationLayer.facade.EnemyFacade;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.ItemFacade;
import be.davidcorp.applicationLayer.facade.LightFacade;
import be.davidcorp.inputControl.InputController;
import be.davidcorp.view.TranslationManager;

public class GameCreatorPanelInputController extends InputController {

	private GameCreatorPanel gameCreatorPanel;
	private GameFieldFacade gamefieldFacade = new GameFieldFacade();
	
	private LightFacade lightFacade = new LightFacade();
	private ConstructionSpriteFacade constructionSpriteFacade = new ConstructionSpriteFacade();
	private EnemyFacade enemyFacade = new EnemyFacade();
	private ItemFacade itemFacade = new ItemFacade();
	
	
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
				int id = gameCreatorPanel.getSelectedSprite().getId();
				if(gameCreatorPanel.getSelectedSprite() instanceof LightDTO){
					lightFacade.deleteLight(id);
					gamefieldFacade.removeLightFromWorld(id);
				}
				gamefieldFacade.removeSpriteFromWorld(gameCreatorPanel.getSelectedSprite());
				gameCreatorPanel.setSelectedSprite(null);
			} catch (Exception e) {
				ErrorHandler.handleError((JPanel) null, e);
			}
		}
	}

}
