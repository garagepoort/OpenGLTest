package be.davidcorp.view.drawer;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CURRENT_BIT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glVertex2d;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.List;

import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.applicationLayer.dto.light.Segment;

public class LightDrawer {

	public LightDrawer() {
		// TODO Auto-generated constructor stub
	}

	public static void drawLight(double x, double y, double radius, LightDTO light) {
		// Making circle in 50 small triangles
		glDisable(GL_TEXTURE_2D);
		glPushAttrib(GL_CURRENT_BIT);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		// volledige cirkel is 2*Math.PI
		double compleetAngle = 2 * Math.PI;
		double increment = compleetAngle / 50;
		// Defining center of circle at (150, 150)
		double cx = x;
		double cy = y;
		// We want to draw circle in red colour

		// Starting loop for drawing triangles
		for (double angle = 0; angle < compleetAngle; angle += increment) {
			glBegin(GL_POLYGON);
			// One vertex of each triangle is at center of circle
			glColor4f(light.getColor().getRed(), light.getColor().getGreen(), light.getColor().getBlue(), 1f);
			glVertex2d(cx, cy);
			// Other two vertices form the periphery of the circle
			glColor4f(0, 0, 0, 0f);
			glVertex2d(cx + Math.cos(angle) * radius, cy + Math.sin(angle) * radius);
			glVertex2d(cx + Math.cos(angle + increment) * radius, cy + Math.sin(angle + increment) * radius);
			glEnd();
		}
		glPopAttrib();
		glEnable(GL_TEXTURE_2D);
	}
	
	public static void drawLightSegments(List<Segment> segments) {
		for (Segment w : segments) {
			glPushMatrix();
			{
				glDisable(GL_TEXTURE_2D);
				glPushAttrib(GL_CURRENT_BIT);
				glLineWidth(3);
				glColor3f(w.color.getRed(), w.color.getGreen(), w.color.getBlue());

				glBegin(GL_LINES);
				{
					glVertex3f(w.p1.point.x, w.p1.point.y, 0);
					glVertex3f(w.p2.point.x, w.p2.point.y, 0);
				}

				glEnd();
				glPopAttrib();
				glEnable(GL_TEXTURE_2D);

			}
			glPopMatrix();
		}
	}
}
