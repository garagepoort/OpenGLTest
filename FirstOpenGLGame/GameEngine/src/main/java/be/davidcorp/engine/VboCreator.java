package be.davidcorp.engine;

import org.lwjgl.opengl.GL15;

public class VboCreator {

	private int VBO;

	public void initVbo(){
		VBO = GL15.glGenBuffers();
	}

	public void bindBuffer() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
	}
}
