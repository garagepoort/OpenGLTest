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
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.io.IOException;
import java.util.List;

import org.newdawn.slick.Color;

import be.davidcorp.applicationLayer.dto.SpriteDTO;
import be.davidcorp.applicationLayer.exception.MapperException;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.metric.Point;
import be.davidcorp.view.game.GamePanel;


public class GameCreatorPanel extends GamePanel{
	
	private SpriteDTO selectedSprite = null;
	private GameFieldFacade gameFieldFacade = new GameFieldFacade();

	public GameCreatorPanel() {
		setInputController(new GameCreatorPanelInputController(this));
	}

	@Override
	public void render() throws IOException, MapperException {
		super.render();
		drawSelectedSprite();
	}

	
	public void dragSelectedSprite(float pointX, float pointY) {
		if (selectedSprite != null) {
			selectedSprite.setX(pointX - selectedSprite.getWidth() / 2);
			selectedSprite.setY(pointY - selectedSprite.getHeight() / 2);
			try {
				gameFieldFacade.updateSpriteInGamefield(selectedSprite);
			} catch (ModelException e) {
				e.printStackTrace();
			}
			setChanged();
			notifyObservers(selectedSprite);
		}
	}

	public void selectSelectedSprite(float pointX, float pointY) throws MapperException {
		List<SpriteDTO> mouseCollisionSprites = getMouseCollisionSprite(pointX, pointY);
		if (mouseCollisionSprites.size() > 0) {
			selectedSprite = mouseCollisionSprites.get(0);
			if (selectedSprite != null) {
				setChanged();
				this.notifyObservers(selectedSprite);
			}
		}else{
			selectedSprite = null;
		}
	}
	
	private List<SpriteDTO> getMouseCollisionSprite(float x, float y) throws MapperException {
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
	
}
