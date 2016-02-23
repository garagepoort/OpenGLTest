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
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.Color;

public class BackgroundDrawer {

	public static void drawBackgroundColor(Color color){
		glDisable(GL_TEXTURE_2D);
		glPushAttrib(GL_CURRENT_BIT);
//		glColor3f(0.2f, 0.2f, 0.2f);
		glColor3f(color.r, color.g, color.b);
		glBegin(GL_QUADS);
		{
			glVertex2f(0, 0);
			glVertex2f(2048, 0);
			glVertex2f(2048, 2048);
			glVertex2f(0, 2048);
		}
		glEnd();
		glPopAttrib();
		glEnable(GL_TEXTURE_2D);
	}
}
