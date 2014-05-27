package be.davidcorp.view.game;

import static org.lwjgl.opengl.GL11.GL_CURRENT_BIT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class GameWonDrawer {

	public static void drawGameWon(){
		glPushMatrix();
		glPushAttrib(GL_CURRENT_BIT);
		{
			Texture t = null;
			try {
				t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/images/mission-completed.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			float x = GameLoop.WIDTH/2 - t.getTextureWidth()/2;
			float y = GameLoop.HEIGHT/2 - t.getTextureHeight()/2;
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
}
