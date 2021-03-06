package GameCreator;

import javax.swing.JPanel;

import org.lwjgl.input.Mouse;

import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.dto.EnemyDTO;
import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.applicationLayer.facade.ConstructionSpriteFacade;
import be.davidcorp.applicationLayer.facade.EnemyFacade;
import be.davidcorp.applicationLayer.facade.ItemFacade;
import be.davidcorp.applicationLayer.facade.LightFacade;
import main.java.be.davidcorp.inputControl.InputController;
import main.java.be.davidcorp.view.TranslationManager;

public class GameCreatorPanelInputController extends InputController {

	private GameCreatorPanel gameCreatorPanel;
	
	private LightFacade lightFacade = new LightFacade();
	private ConstructionSpriteFacade constructionSpriteFacade = new ConstructionSpriteFacade();
	private EnemyFacade enemyFacade = new EnemyFacade();
	private ItemFacade itemFacade = new ItemFacade();
	
	
	public GameCreatorPanelInputController(GameCreatorPanel gameCreatorPanel) {
		this.gameCreatorPanel = gameCreatorPanel;
	}

	
	@Override
	public void on_LEFT_Key() {
		
		TranslationManager.initializeLeftTranslationForGameCreator();
	}


	@Override
	public void on_RIGHT_Key() {
		TranslationManager.initializeRightTranslationForGameCreator();
	}


	@Override
	public void on_UP_Key() {
		TranslationManager.initializeUpTranslationForGameCreator();
	}


	@Override
	public void on_DOWN_Key() {
		TranslationManager.initializeDownTranslationForGameCreator();
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
	public void onDeletePressed() {
		if (gameCreatorPanel.getSelectedSprite() != null) {
			try {
				int id = gameCreatorPanel.getSelectedSprite().getId();
				if(gameCreatorPanel.getSelectedSprite() instanceof LightDTO){
					lightFacade.deleteLight(id);
				}
				if(gameCreatorPanel.getSelectedSprite() instanceof ConstructionSpriteDTO){
					constructionSpriteFacade.deleteConstructionSprite(id);
					
				}
				if(gameCreatorPanel.getSelectedSprite() instanceof EnemyDTO){
					enemyFacade.deleteEnemy(id);
				}
				if(gameCreatorPanel.getSelectedSprite() instanceof ItemDTO){
					itemFacade.deleteItem(id);
				}
				gameCreatorPanel.setSelectedSprite(null);
			} catch (Exception e) {
				ErrorHandler.handleError((JPanel) null, e);
			}
		}
	}

}
