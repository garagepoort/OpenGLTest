package GameCreator;

import static org.lwjgl.opengl.GL11.GL_CURRENT_BIT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.io.IOException;
import java.util.List;

import org.newdawn.slick.Color;

import GameCreator.createframes.FrameFacade;
import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.dto.EnemyDTO;
import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.SpriteDTO;
import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.applicationLayer.facade.ConstructionSpriteFacade;
import be.davidcorp.applicationLayer.facade.EnemyFacade;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.ItemFacade;
import be.davidcorp.applicationLayer.facade.LightFacade;
import be.davidcorp.metric.Point;
import be.davidcorp.view.TranslationManager;
import be.davidcorp.view.game.GamePanel;

public class GameCreatorPanel extends GamePanel {

	private SpriteDTO selectedSprite = null;
	private GameFieldFacade gameFieldFacade = new GameFieldFacade();
	private ItemFacade itemFacade = new ItemFacade();
	private ConstructionSpriteFacade constructionSpriteFacade = new ConstructionSpriteFacade();
	private LightFacade lightFacade = new LightFacade();
	private EnemyFacade enemyFacade = new EnemyFacade();
	private Point dragPoint;

	public GameCreatorPanel() {
		setInputController(new GameCreatorPanelInputController(this));
	}

	@Override
	public void render() throws IOException {
		super.render();
		getGamePanelDrawer().setShadowsOn(false);

		drawSelectedSprite();
	}

	public void dragSelectedSprite(float pointX, float pointY) {
		if (selectedSprite != null) {
			setDragPoint(pointX, pointY);
			
			float previousX = selectedSprite.getX();
			float previousY = selectedSprite.getY();
			selectedSprite.setX(pointX - dragPoint.x);
			selectedSprite.setY(pointY - dragPoint.y);
			if (gameFieldFacade.isDTOCollidingWithConstructionItem(selectedSprite)) {
				selectedSprite.setX(previousX);
				selectedSprite.setY(previousY);
			}
			try {
				updateSelectedSprite();
			} catch (Exception e) {
				e.printStackTrace();
			}
			setChanged();
			notifyObservers(selectedSprite);
		}
	}

	public void updateSelectedSprite() {
		FrameFacade.getSelectedSpritePanel().setSprite(selectedSprite);
		if (selectedSprite instanceof ItemDTO) {
			itemFacade.updateItem((ItemDTO) selectedSprite);
		} else if (selectedSprite instanceof ConstructionSpriteDTO) {
			constructionSpriteFacade.updateConstructionSprite((ConstructionSpriteDTO) selectedSprite);
		} else if (selectedSprite instanceof EnemyDTO) {
			enemyFacade.updateEnemy((EnemyDTO) selectedSprite);
		} else if (selectedSprite instanceof LightDTO) {
			lightFacade.updateLight((LightDTO) selectedSprite);
		}
	}

	public void selectSelectedSprite(float pointX, float pointY) {
		dragPoint = null;
		List<SpriteDTO> mouseCollisionSprites = getMouseCollisionSprite(pointX, pointY);
		if (mouseCollisionSprites.size() > 0 && mouseCollisionSprites.get(0).getId() > 0) {
			selectedSprite = mouseCollisionSprites.get(0);
			if (selectedSprite != null) {
				FrameFacade.getSelectedSpritePanel().setSprite(selectedSprite);
			}
		} else {
			selectedSprite = null;
		}
	}

	private List<SpriteDTO> getMouseCollisionSprite(float x, float y) {
		Point point = new Point(x, y, 0);
		return gameFieldFacade.getSpritesOnPointInGamefield(point);
	}

	public SpriteDTO getSelectedSprite() {
		return selectedSprite;
	}

	public void setSelectedSprite(SpriteDTO selectedSprite) {
		this.selectedSprite = selectedSprite;
	}

	private void drawSelectedSprite() {
		if (selectedSprite != null) {
			glPushMatrix();
			{
				glTranslatef(TranslationManager.getGameFieldXTranslation(), TranslationManager.getGameFieldYTranslation(), 0);
				Color color = new Color(255, 255, 0);
				glDisable(GL_TEXTURE_2D);
				glPushAttrib(GL_CURRENT_BIT);
				glLineWidth(3);
				glColor3f(color.r, color.g, color.b);

				glBegin(GL_LINES);
				{
					glVertex3f(selectedSprite.getDownLeftPoint().x, selectedSprite.getDownLeftPoint().y, 0);
					glVertex3f(selectedSprite.getDownRightPoint().x, selectedSprite.getDownRightPoint().y, 0);

					glVertex3f(selectedSprite.getDownRightPoint().x, selectedSprite.getDownRightPoint().y, 0);
					glVertex3f(selectedSprite.getUpperRightPoint().x, selectedSprite.getUpperRightPoint().y, 0);

					glVertex3f(selectedSprite.getUpperRightPoint().x, selectedSprite.getUpperRightPoint().y, 0);
					glVertex3f(selectedSprite.getUpperLeftPoint().x, selectedSprite.getUpperLeftPoint().y, 0);

					glVertex3f(selectedSprite.getUpperLeftPoint().x, selectedSprite.getUpperLeftPoint().y, 0);
					glVertex3f(selectedSprite.getDownLeftPoint().x, selectedSprite.getDownLeftPoint().y, 0);
				}

				glEnd();
				glPopAttrib();
				glEnable(GL_TEXTURE_2D);
			}
			glPopMatrix();

		}
	}

	public void setDragPoint(float pointX, float pointY) {
		if (this.dragPoint == null) {
			float selectedSpriteX = selectedSprite.getX();
			float selectedSpriteY = selectedSprite.getY();
			this.dragPoint = new Point(pointX-selectedSpriteX, pointY-selectedSpriteY, 0);
		}
	}

}
