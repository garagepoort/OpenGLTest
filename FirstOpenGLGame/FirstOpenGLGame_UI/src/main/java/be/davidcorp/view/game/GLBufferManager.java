package be.davidcorp.view.game;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import be.davidcorp.domain.sprite.Sprite;

public class GlBufferManager {

	private static  GlBufferManager instance = new GlBufferManager();
	private int VAO;
	private int VBO;

	private GlBufferManager(){};

	public static GlBufferManager getInstance(){
		return instance;
	}

	public void init(){
		VAO = glGenVertexArrays();
		VBO = glGenBuffers();
	}

	public int getVAO() {
		return VAO;
	}

	public int getVBO() {
		return VBO;
	}

	public void addSpriteToVertexBuffer(Sprite sprite){
		glBindBuffer(GL_ARRAY_BUFFER, VBO);

	}
}
