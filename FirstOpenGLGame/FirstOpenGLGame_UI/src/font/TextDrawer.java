package font;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class TextDrawer {

	/**
	 * Method that draws a text at a certain position using the {@link HashMap} from the {@link FontManager} class.
	 * @param text The text that has to be drawn.
	 * @param x The x position where the text starts.
	 * @param y The y position where the text starts.
	 */
	public static void drawText(String text, float x, float y){
		char[] chars = text.toCharArray();
		float standX = x;
		for(int i = 0; i<chars.length;i++){
			Texture t = FontManager.fonts.get(chars[i]);
			if(t!=null){
				t.bind();
				GL11.glBegin(GL11.GL_QUADS);{
					GL11.glTexCoord2f(0,1);//bottom left
					GL11.glVertex2f(x,y);
					GL11.glTexCoord2f(1,1);//bottom right
					GL11.glVertex2f(x+t.getTextureWidth(),y);
					GL11.glTexCoord2f(1,0);//top right
					GL11.glVertex2f(x+t.getTextureWidth(),y+t.getTextureHeight());
					GL11.glTexCoord2f(0,0);//top left
					GL11.glVertex2f(x,y+t.getTextureHeight());

				}
				GL11.glEnd();
				if(chars[i] == 'm' || chars[i] == 'w' || chars[i] == 'C' || chars[i] == 'M' || chars[i] == 'W' || chars[i] == 'C'){
					x+=10;
				}else if(chars[i] == 'r' || chars[i] == 't' || chars[i] == 'f'){
					x+=4;
				}
				else{
					x+=t.getTextureWidth();
				}
			}
			if(chars[i] == '\n'){
				y-=16;
				x=standX;
			}
		}
	}
}
