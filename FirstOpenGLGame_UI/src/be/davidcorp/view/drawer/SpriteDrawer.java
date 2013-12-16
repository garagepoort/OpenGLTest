package be.davidcorp.view.drawer;

import static org.lwjgl.opengl.GL11.GL_CURRENT_BIT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.IOException;

import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import be.davidcorp.applicationLayer.dto.SpriteDTO;

public class SpriteDrawer {

	public static void drawSpriteWithoutTexture(SpriteDTO spriteDTO) {
		glDisable(GL_TEXTURE_2D);
		glPushAttrib(GL_CURRENT_BIT);
		{
			glColor3f(spriteDTO.getColor().getRed(), spriteDTO.getColor().getGreen(), spriteDTO.getColor().getBlue());
			glBegin(GL_QUADS);
			{
				glVertex2f(spriteDTO.getDownLeftPoint().x, spriteDTO.getDownLeftPoint().y);
				glVertex2f(spriteDTO.getDownRightPoint().x, spriteDTO.getDownRightPoint().y);
				glVertex2f(spriteDTO.getUpperRightPoint().x, spriteDTO.getUpperRightPoint().y);
				glVertex2f(spriteDTO.getUpperLeftPoint().x, spriteDTO.getUpperLeftPoint().y);
			}
			glEnd();
		}
		glPopAttrib();
		glEnable(GL_TEXTURE_2D);
	}

	public static void drawSpriteWithTexture(SpriteDTO spriteDTO) {
		bindTexture(spriteDTO);
		glPushMatrix();
		{
			rotateSprite(spriteDTO, spriteDTO.getX(), spriteDTO.getY());
			glBegin(GL_QUADS);
			{
				glTexCoord2f(0, 0);// top left
				glVertex2f(spriteDTO.getX(), spriteDTO.getY() + spriteDTO.getHeight());
				glTexCoord2f(1, 0);// top right
				glVertex2f(spriteDTO.getX() + spriteDTO.getWidth(), spriteDTO.getY() + spriteDTO.getHeight());
				glTexCoord2f(1, 1);// bottom right
				glVertex2f(spriteDTO.getX() + spriteDTO.getWidth(), spriteDTO.getY());
				glTexCoord2f(0, 1);// bottom left
				glVertex2f(spriteDTO.getX(), spriteDTO.getY());
			}
			glEnd();
		}
		glPopMatrix();
	}
	
	private static void bindTexture(SpriteDTO spriteDTO) {
		try {
			TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(spriteDTO.getTextureBunch().getCurrentTexture())).bind();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void rotateSprite(SpriteDTO spriteDTO, float x, float y) {
		glTranslatef(x + spriteDTO.getWidth() / 2, y + spriteDTO.getHeight() / 2, 0);
		glRotatef(spriteDTO.getRotationAngle(), 0f, 0f, 1f);
		glTranslatef(-x - spriteDTO.getWidth() / 2, -y - spriteDTO.getHeight() / 2, 0);
	}

}
