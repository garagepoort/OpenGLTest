package be.davidcorp.view.drawer;

import static be.davidcorp.view.game.GameLoop.HEIGHT;
import static be.davidcorp.view.game.GameLoop.WIDTH;
import static org.lwjgl.opengl.GL11.GL_ALWAYS;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_EQUAL;
import static org.lwjgl.opengl.GL11.GL_INCR;
import static org.lwjgl.opengl.GL11.GL_KEEP;
import static org.lwjgl.opengl.GL11.GL_REPLACE;
import static org.lwjgl.opengl.GL11.GL_STENCIL_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_STENCIL_TEST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearStencil;
import static org.lwjgl.opengl.GL11.glColorMask;
import static org.lwjgl.opengl.GL11.glDepthMask;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glStencilFunc;
import static org.lwjgl.opengl.GL11.glStencilMask;
import static org.lwjgl.opengl.GL11.glStencilOp;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.Color;

import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.applicationLayer.dto.light.LightTriangle;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.view.TranslationManager;
import be.davidcorp.view.game.GamePanel;
import be.davidcorp.view.light.LightManager;

public class GamePanelDrawer {

	private static PlayerFacade playerFacade = new PlayerFacade();
	private LightManager lightManager = new LightManager();
	private boolean shadowsOn = true;

	public void drawGamePanel(GamePanel gamePanel) {
		// Clear The Screen And The Depth Buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);

		float x = WIDTH / 2 + TranslationManager.getPlayerTranslationX();
		float y = HEIGHT / 2 + TranslationManager.getPlayerTranslationY();

		drawWorld();

		glPushMatrix();
		{
			glTranslatef(TranslationManager.getGameFieldXTranslation(), TranslationManager.getGameFieldYTranslation(), 0);
			glEnable(GL_STENCIL_TEST);
			glClearStencil(0);
			int lightCounter = 1;
			glStencilMask(0xff);

			lightCounter = drawLightsInGamefield(lightCounter);
			lightCounter = drawPlayerFlashLight(lightCounter);

			lightCounter++;
			drawLineOfSight(x, y, lightCounter);
			drawSpritesInWorld();
		}
		glPopMatrix();
		glDisable(GL_STENCIL_TEST);

		PlayerDrawer.drawPlayer();
	}

	private void drawLineOfSight(float x, float y, int lightCounter) {
		glStencilFunc(GL_ALWAYS, lightCounter, 0xFF);
		glStencilOp(GL_REPLACE, GL_REPLACE, GL_REPLACE);
		glColorMask(false, false, false, false);
		glDepthMask(false);
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_TRIANGLES);
		{
			for (LightTriangle l : lightManager.getPlayerLineOfSight().getLightTriangles()) {
				glVertex2f(l.p2.x, l.p2.y);
				glVertex2f(l.p1.x, l.p1.y);
				glVertex2f(l.p3.x, l.p3.y);
			}
		}
		glEnd();
		glEnable(GL_TEXTURE_2D);

		glStencilFunc(GL_EQUAL, lightCounter, 0xFF);
		glStencilOp(GL_INCR, GL_INCR, GL_INCR);

		glPushMatrix();
		{
			glLoadIdentity();
			LightDrawer.drawLight(x + playerFacade.getWidth() / 2, y + playerFacade.getHeight() / 2, lightManager.getPlayerLineOfSight().getRadius(), lightManager.getPlayerLineOfSight());
			lightCounter++;

			glStencilFunc(GL_EQUAL, lightCounter, 0xFF);
			glStencilOp(GL_KEEP, GL_KEEP, GL_KEEP);
			glColorMask(true, true, true, true);
			glDepthMask(true);

			glLoadIdentity();
			LightDrawer.drawLight(x + playerFacade.getWidth() / 2, y + playerFacade.getHeight() / 2, lightManager.getPlayerLineOfSight().getRadius(), lightManager.getPlayerLineOfSight());
		}
		glPopMatrix();
	}

	private int drawLightsInGamefield(int lightCounter)  {
		glDisable(GL_TEXTURE_2D);
		for (LightDTO light : lightManager.getGamefieldLights()) {
			if (light.isLightOn()) {
				glStencilFunc(GL_ALWAYS, lightCounter, 0xFF);
				glStencilOp(GL_REPLACE, GL_REPLACE, GL_REPLACE);
				glColorMask(false, false, false, false);
				glDepthMask(false);

				glBegin(GL_TRIANGLES);
				{
					for (LightTriangle l : light.getLightTriangles()) {
						glVertex2f(l.p2.x, l.p2.y);
						glVertex2f(l.p1.x, l.p1.y);
						glVertex2f(l.p3.x, l.p3.y);
					}
				}
				glEnd();
				glStencilFunc(GL_EQUAL, lightCounter, 0xFF);
				glStencilOp(GL_INCR, GL_INCR, GL_INCR);

				LightDrawer.drawLight(light.getX(), light.getY(), light.getRadius(), light);
				lightCounter++;

				glStencilFunc(GL_EQUAL, lightCounter, 0xFF);
				glStencilOp(GL_KEEP, GL_KEEP, GL_KEEP);
				glColorMask(true, true, true, true);
				glDepthMask(true);

				LightDrawer.drawLight(light.getX(), light.getY(), light.getRadius(), light);
				drawSpritesInWorld();
				LightDrawer.drawLightSegments(lightManager.getSegments());
				lightCounter++;
			}
		}
		glEnable(GL_TEXTURE_2D);
		return lightCounter;
	}

	private int drawPlayerFlashLight(int lightCounter)  {
		if (playerFacade.isFlashLightOn()) {
			// draw the flashlight
			glStencilFunc(GL_ALWAYS, lightCounter, 0xFF);
			glStencilOp(GL_REPLACE, GL_REPLACE, GL_REPLACE);
			glColorMask(false, false, false, false);
			glDepthMask(false);

			glDisable(GL_TEXTURE_2D);
			glBegin(GL_TRIANGLES);
			{
				for (LightTriangle l : lightManager.getPlayerFlashLight().getLightTriangles()) {
					glVertex2f(l.p2.x, l.p2.y);
					glVertex2f(l.p1.x, l.p1.y);
					glVertex2f(l.p3.x, l.p3.y);
				}
			}
			glEnd();
			glEnable(GL_TEXTURE_2D);

			glStencilFunc(GL_EQUAL, lightCounter, 0xFF);
			glStencilOp(GL_INCR, GL_INCR, GL_INCR);

			PlayerDrawer.drawPlayerFlaslight();
			lightCounter++;

			glStencilFunc(GL_EQUAL, lightCounter, 0xFF);
			glStencilOp(GL_KEEP, GL_KEEP, GL_KEEP);
			glColorMask(true, true, true, true);
			glDepthMask(true);

			PlayerDrawer.drawPlayerFlaslight();
			drawSpritesInWorld();
			LightDrawer.drawLightSegments(lightManager.getSegments());
		}
		return lightCounter;
	}

	private void drawWorld()  {
		glTranslatef(TranslationManager.getGameFieldXTranslation(), TranslationManager.getGameFieldYTranslation(), 0);
		BackgroundDrawer.drawBackgroundColor(new Color(0.2f, 0.2f, 0.2f));
		GamefieldDrawer.drawConstructionItems();
		if (shadowsOn) {
			GamefieldDrawer.drawGamefieldShadows();
		} else {
			drawSpritesInWorld();
		}
	}

	private void drawSpritesInWorld()  {
		GamefieldDrawer.drawFiredAmmo();
		GamefieldDrawer.drawEnemies();
		GamefieldDrawer.drawGroundItems();
	}

	public void setShadowsOn(boolean shadowsOn) {
		this.shadowsOn = shadowsOn;
	}
}
