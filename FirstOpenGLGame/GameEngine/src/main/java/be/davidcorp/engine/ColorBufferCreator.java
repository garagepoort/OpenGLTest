package be.davidcorp.engine;

import org.lwjgl.opengl.GL15;

public class ColorBufferCreator {

	private int colorBuffer;

	public void initColorBuffer(){
		colorBuffer = GL15.glGenBuffers();
	}

	public void bindBuffer() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorBuffer);
	}
}
