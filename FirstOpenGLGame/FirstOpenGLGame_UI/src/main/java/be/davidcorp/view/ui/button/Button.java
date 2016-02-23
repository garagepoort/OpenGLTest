package be.davidcorp.view.ui.button;

import java.io.IOException;

import be.davidcorp.view.ui.UIComponent;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class Button extends UIComponent {

	public Button(float x, float y, int width, int height) {
		super(x, y, width, height);
	}


	@Override
	public void drawThis() {
		//TODO david niet ok
		try {
			TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(getTexture())).bind();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GL11.glBegin(GL11.GL_QUADS);{
			GL11.glTexCoord2f(0,1);//bottom left
			GL11.glVertex2f(getX(),getY());
			GL11.glTexCoord2f(1,1);//bottom right
			GL11.glVertex2f(getX()+getWidth(),getY());
			GL11.glTexCoord2f(1,0);//top right
			GL11.glVertex2f(getX()+getWidth(),getY()+getHeight());
			GL11.glTexCoord2f(0,0);//top left
			GL11.glVertex2f(getX(),getY()+getHeight());
		}
		GL11.glEnd();
	}

}
