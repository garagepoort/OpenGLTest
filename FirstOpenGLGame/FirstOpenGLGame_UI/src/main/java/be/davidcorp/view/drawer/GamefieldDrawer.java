package main.java.be.davidcorp.view.drawer;

import static main.java.be.davidcorp.view.TranslationManager.getGameFieldXTranslation;
import static main.java.be.davidcorp.view.TranslationManager.getGameFieldYTranslation;
import static main.java.be.davidcorp.view.game.GameLoop.HEIGHT;
import static main.java.be.davidcorp.view.game.GameLoop.WIDTH;
import static org.lwjgl.opengl.GL11.GL_CURRENT_BIT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDepthMask;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.IOException;

import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import be.davidcorp.applicationLayer.dto.AmmoDTO;
import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.dto.EnemyDTO;
import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;

public class GamefieldDrawer {

	private static GameFieldFacade fieldFacade = new GameFieldFacade();
	
	public static void drawGameFieldBackground(){
		glDepthMask(false);

		try {
			TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(fieldFacade.getTexture())).bind();
		} catch (IOException e) {
			e.printStackTrace();
		}
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 1);// bottom left
			glVertex2f(fieldFacade.getXPosition(), fieldFacade.getYPosition());
			glTexCoord2f(1, 1);// bottom right
			glVertex2f(fieldFacade.getXPosition() + fieldFacade.getWidthOfGamefield(), fieldFacade.getYPosition());
			glTexCoord2f(1, 0);// top right
			glVertex2f(fieldFacade.getXPosition() + fieldFacade.getWidthOfGamefield(), fieldFacade.getYPosition() + fieldFacade.getHeightOfGamefield());
			glTexCoord2f(0, 0);// top left
			glVertex2f(fieldFacade.getXPosition(), fieldFacade.getYPosition() + fieldFacade.getHeightOfGamefield());

		}
		glEnd();

		glDepthMask(true);
	}
	
	public static void drawGamefieldShadows(){
			glDisable(GL_TEXTURE_2D);
			glPushAttrib(GL_CURRENT_BIT);
			glColor4f(0, 0, 0, 0.7f);
			glBegin(GL_QUADS);
			{
				glVertex2f(0 - getGameFieldXTranslation(), 0 - getGameFieldYTranslation());
				glVertex2f(WIDTH - getGameFieldXTranslation(), 0 - getGameFieldYTranslation());
				glVertex2f(WIDTH - getGameFieldXTranslation(), HEIGHT - getGameFieldYTranslation());
				glVertex2f(0 - getGameFieldXTranslation(), HEIGHT - getGameFieldYTranslation());
			}
			glEnd();
			glPopAttrib();
			glEnable(GL_TEXTURE_2D);
	}
	
	public static void drawConstructionItems()  {
		for (ConstructionSpriteDTO constructionSprite : fieldFacade.getConstructionSpritesFromWorld()) {
			if(constructionSprite.getTexture() == null){
				SpriteDrawer.drawSpriteWithoutTexture(constructionSprite);
			}else{
				SpriteDrawer.drawSpriteWithTexture(constructionSprite);
			}
		}
	}
	
	public static void drawFiredAmmo() {
		for (AmmoDTO ammoDTO : fieldFacade.getAmmoInWorld()) {
			SpriteDrawer.drawSpriteWithoutTexture(ammoDTO);
		}
	}

	public static void drawGroundItems() {
		try {
			for (ItemDTO item : fieldFacade.getItemsOnGroundInWorld()) {
				SpriteDrawer.drawSpriteWithTexture(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void drawEnemies()  {
		for (EnemyDTO enemy : fieldFacade.getEnemiesInWorld()) {
			SpriteDrawer.drawSpriteWithTexture(enemy);
		}
	}

}
