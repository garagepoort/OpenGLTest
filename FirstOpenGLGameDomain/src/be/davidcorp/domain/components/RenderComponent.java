package be.davidcorp.domain.components;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class RenderComponent implements Component{

	public Texture texture;
	
	public RenderComponent(String texturePath){
		try {
			this.texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(texturePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
