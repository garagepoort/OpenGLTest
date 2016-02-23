package be.davidcorp.view.drawer;

import static be.davidcorp.view.game.GameLoop.HEIGHT;
import static be.davidcorp.view.game.GameLoop.WIDTH;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CURRENT_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2d;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.metric.Point;
import be.davidcorp.metric.Vector;
import be.davidcorp.view.MousePosition;
import be.davidcorp.view.TranslationManager;
import be.davidcorp.view.light.LightManager;

public class PlayerDrawer {
	private static PlayerFacade playerFacade = new PlayerFacade();
	private static LightManager lightManager = new LightManager();

	public static void drawPlayer() {
		float x = WIDTH / 2 + TranslationManager.getPlayerTranslationX();
		float y = HEIGHT / 2 + TranslationManager.getPlayerTranslationY();

		if (playerFacade.isPlayerAlive()) {
			lookInRightDirection(x, y);
		}

		drawPlayerBody(x, y);
	}

	private static void lookInRightDirection(float x, float y) {
		Point beginPointOfVector = new Point(x + playerFacade.getWidth() / 2, y + playerFacade.getHeight() / 2, 0);
		Point EndPointOfVector = new Point((float) MousePosition.X, (float) MousePosition.Y, 0);
		Vector v = new Vector(beginPointOfVector, EndPointOfVector);
		playerFacade.lookInDirection(v);
	}

	public static void drawPlayerFlaslight() {
		float playerPositionX = WIDTH / 2 + TranslationManager.getPlayerTranslationX();
		float playerPositionY = HEIGHT / 2 + TranslationManager.getPlayerTranslationY();

		float x = playerPositionX + playerFacade.getWidth() / 2;
		float y = playerPositionY + playerFacade.getHeight() / 2;
		int radius = 200;
		glPushMatrix();
		{
			glSetup();
			// volledige cirkel is 2*Math.PI
			double compleetAngle = Math.PI / 4;

			double increment = compleetAngle / 50;
			// Defining center of circle at (150, 150)
			double cx = x;
			double cy = y;

			glTranslatef((float) x, (float) y, 0);
			glRotatef(playerFacade.getRotationAngle() + 70, 0f, 0f, 1f);
			glTranslatef((float) -x, (float) -y, 0);

			for (double angle = 0; angle < compleetAngle; angle += increment) {
				glBegin(GL_POLYGON);
				// One vertex of each triangle is at center of circle
				glColor4f(lightManager.getPlayerFlashLight().getColor().getRed(), lightManager.getPlayerFlashLight().getColor().getGreen(), lightManager.getPlayerFlashLight().getColor().getBlue(), 1f);
				glVertex2d(cx, cy);
				// Other two vertices form the periphery of the circle
				glColor4f(0, 0, 0, 0f);
				glVertex2d(cx + Math.cos(angle) * radius, cy + Math.sin(angle) * radius);
				glVertex2d(cx + Math.cos(angle + increment) * radius, cy + Math.sin(angle + increment) * radius);
				glEnd();
			}
			glTearDown();
		}
		glPopMatrix();
	}

	private static void glTearDown() {
		glPopAttrib();
		glEnable(GL_TEXTURE_2D);
	}

	private static void glSetup() {
		glLoadIdentity();
		glDisable(GL_TEXTURE_2D);
		glPushAttrib(GL_CURRENT_BIT);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	private static void drawPlayerBody(float x, float y) {
		glPushMatrix();
		glPushAttrib(GL_CURRENT_BIT);
		{

			// rotation
			glTranslatef(x + playerFacade.getWidth() / 2, y + playerFacade.getHeight() / 2, 0);
			glRotatef(playerFacade.getRotationAngle(), 0f, 0f, 1f);
			glTranslatef(-x - playerFacade.getWidth() / 2, -y - playerFacade.getHeight() / 2, 0);

			Texture t = null;
			try {
				t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(playerFacade.getTexture()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			t.bind();
			glBegin(GL_QUADS);
			{
				glTexCoord2f(0, 0);// top left
				glVertex2f(x, y + t.getTextureHeight());
				glTexCoord2f(1, 0);// top right
				glVertex2f(x + t.getTextureWidth(), y + t.getTextureHeight());
				glTexCoord2f(1, 1);// bottom right
				glVertex2f(x + t.getTextureWidth(), y);
				glTexCoord2f(0, 1);// bottom left
				glVertex2f(x, y);
			}
			glEnd();
		}
		glPopAttrib();
		glPopMatrix();
	}

	public static void drawStamina() {
		double mPerc = (double) playerFacade.getStamina() / (double) playerFacade.getMaxStamina() * 100 / 2;
		CircleDrawer.drawCircle(100, 0, 50, 0, 0, 0);
		CircleDrawer.drawCircle(100, 0, mPerc, 0, 0, 255);
	}

	public static void drawHealth() {
		double hPerc = (double) playerFacade.getHealthPoints() / (double) playerFacade.getMaxHealthPoints() * 100;
		CircleDrawer.drawCircle(0, 0, 100, 0, 0, 0);
		CircleDrawer.drawCircle(0, 0, hPerc, 1f, 0, 0);
	}

}
