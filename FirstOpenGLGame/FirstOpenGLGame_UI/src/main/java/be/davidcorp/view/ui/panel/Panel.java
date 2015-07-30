package main.java.be.davidcorp.view.ui.panel;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import main.java.be.davidcorp.view.game.PlayGamePanel;
import main.java.be.davidcorp.view.ui.UIComponent;
import main.java.font.TextDrawer;

public class Panel extends UIComponent {

	private Color color;
	private String text;
	private float textX;
	private float textY;
	private PlayGamePanel panel;

	public Panel(float x, float y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
		setVisible(true);
	}

	public Panel(float x, float y, int width, int height, String texture){
		super(x, y, width, height);
		setTexture(texture);
		setVisible(true);
	}

	public void setX(float x) {
		float diff = getX() - x;
		super.setX(x);
		replaceTextX((getTextX() - diff));
	}

	public void setY(float y) {
		float diff = getY() - y;
		super.setY(y);
		replaceTextY((getTextY() - diff));
	}

	public void replaceTextX(float x) {
		this.textX = x;
	}

	public void replaceTextY(float y) {
		this.textY = y;
	}


	public void drawThis()  {
		if (isVisible()) {
			if (getTexture() == null || getTexture().equals("")) {
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glPushAttrib(GL11.GL_CURRENT_BIT);
				GL11.glColor4f(color.r, color.g, color.b, color.a);
				drawQuad();
				GL11.glPopAttrib();
				GL11.glEnable(GL11.GL_TEXTURE_2D);
			} else {
				try {
					TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(getTexture())).bind();
				} catch (IOException e) {
					e.printStackTrace();
				}
				drawQuad();
			}
			if (text != null && text != "") {
				TextDrawer.drawText(text, textX, textY);
			}
		}

	}

	private void drawQuad() {
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(0, 1);// bottom left
			GL11.glVertex2f(getX(), getY());
			GL11.glTexCoord2f(1, 1);// bottom right
			GL11.glVertex2f(getX() + getWidth(), getY());
			GL11.glTexCoord2f(1, 0);// top right
			GL11.glVertex2f(getX() + getWidth(), getY() + getHeight());
			GL11.glTexCoord2f(0, 0);// top left
			GL11.glVertex2f(getX(), getY() + getHeight());
		}
		GL11.glEnd();
	}

	public String getText() {
		return text;
	}

	public void setText(String text, int relativeX, int relativeY) {
		this.text = text;
		setTextX(relativeX);
		setTextY(relativeY);
	}

	public float getTextX() {
		return textX;
	}

	/**
	 * Set the x value of the text
	 * 
	 * @param textX
	 *            The relative x coordinate of this text.
	 */
	public void setTextX(float textX) {
		this.textX = getX() + textX;
	}

	/**
	 * @return The y coordinate of the text inside this panel.
	 */
	public float getTextY() {
		return textY;
	}
	
	/**
	 * Set the y coordinate of the text
	 * 
	 * @param textY
	 *            The relative y coordinate of this text.
	 */
	public void setTextY(float textY) {
		this.textY = getY() + textY;
	}

	public PlayGamePanel getGamePanel() {
		return panel;
	}

	public void setGamePanel(PlayGamePanel panel) {
		this.panel = panel;
	}

}
