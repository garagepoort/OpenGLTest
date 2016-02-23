package be.davidcorp.view.drawer;

import static org.lwjgl.opengl.GL11.GL_CURRENT_BIT;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glVertex2d;

public class CircleDrawer {

	public static void drawCircle(double x, double y, double radius, float redColor, float greenColor, float blueColor) {
		// Making circle in 50 small triangles
		glPushMatrix();
		glLoadIdentity();
		glDisable(GL_TEXTURE_2D);
		glPushAttrib(GL_CURRENT_BIT);

		double increment = 2 * Math.PI / 50;
		// Defining center of circle at (150, 150)
		double cx = x;
		double cy = y;
		// Defining radius of circle equal to 70 pixels

		// We want to draw circle in red colour
		glColor3f(redColor, greenColor, blueColor);
		// Starting loop for drawing triangles
		for (double angle = 0; angle < 2 * Math.PI; angle += increment) {
			glBegin(GL_POLYGON);
			// One vertex of each triangle is at center of circle
			glVertex2d(cx, cy);
			// Other two vertices form the periphery of the circle
			glVertex2d(cx + Math.cos(angle) * radius, cy + Math.sin(angle) * radius);
			glVertex2d(cx + Math.cos(angle + increment) * radius, cy + Math.sin(angle + increment) * radius);
			glEnd();
		}
		glPopAttrib();
		glEnable(GL_TEXTURE_2D);
		glPopMatrix();
	}
}
