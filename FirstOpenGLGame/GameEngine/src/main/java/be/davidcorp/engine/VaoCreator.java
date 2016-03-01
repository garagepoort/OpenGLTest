package be.davidcorp.engine;

import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class VaoCreator {

	private int VAO;

	public void initVao(){
		VAO = glGenVertexArrays();
		glBindVertexArray(VAO);
	}
}
