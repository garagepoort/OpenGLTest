package GameCreator;

import javax.swing.JPanel;

import org.lwjgl.input.Mouse;

import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.dto.EnemyDTO;
import be.davidcorp.applicationLayer.dto.ItemDTO;
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
	public void on_LEFT_Key() {
		TranslationManager.initializeLeftTranslation();
	}


	@Override
	public void on_RIGHT_Key() {
		TranslationManager.initializeRightTranslation();
	}


	@Override
	public void on_UP_Key() {
		TranslationManager.initializeUpTranslation();
	}


	@Override
	public void on_DOWN_Key() {
		TranslationManager.initializeDownTranslation();;
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
				if(gameCreatorPanel.getSelectedSprite() instanceof ConstructionSpriteDTO){
					constructionSpriteFacade.deleteConstructionSprite(id);
					gamefieldFacade.removeConstructionSpriteFromWorld(id);
				}
				if(gameCreatorPanel.getSelectedSprite() instanceof EnemyDTO){
					enemyFacade.deleteEnemy(id);
					gamefieldFacade.removeEnemyFromWorld(id);
				}
				if(gameCreatorPanel.getSelectedSprite() instanceof ItemDTO){
					itemFacade.deleteItem(id);
					gamefieldFacade.removeGroundItemFromWorld(id);
				}
				gameCreatorPanel.setSelectedSprite(null);
			} catch (Exception e) {
				ErrorHandler.handleError((JPanel) null, e);
			}
		}
	}

}
