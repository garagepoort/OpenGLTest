package be.davidcorp.engine;

import static org.lwjgl.opengl.GL11.glGenTextures;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class TextureBufferCreator {

	private int textureBuffer;

	public void initTextureBuffer(){
		textureBuffer = glGenTextures();
	}

	public void bindBuffer() {
		GL15.glBindBuffer(GL11.GL_TEXTURE_2D, textureBuffer);
	}
}
