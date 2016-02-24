package be.davidcorp.view.drawer;

import static org.lwjgl.opengl.GL11.glClearColor;

import org.newdawn.slick.Color;

public class BackgroundDrawer {

	public static void drawBackgroundColor(Color color){
		glClearColor(color.r, color.g, color.b, 0);
	}
}
