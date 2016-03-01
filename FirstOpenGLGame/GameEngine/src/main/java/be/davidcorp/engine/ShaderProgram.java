package be.davidcorp.engine;

import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glBindAttribLocation;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glDetachShader;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;

import org.lwjgl.opengl.GL20;

public abstract class ShaderProgram {

	private int programId;
	private int vertexShaderId;
	private int fragmentShaderId;

	private ShaderLoader shaderLoader = new ShaderLoader();

	public ShaderProgram(String vertexFile, String fragmentFile) {
		vertexShaderId = shaderLoader.loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		fragmentShaderId = shaderLoader.loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		programId = glCreateProgram();
		glAttachShader(programId, vertexShaderId);
		glAttachShader(programId, fragmentShaderId);
		bindAttributes();
		glLinkProgram(programId);
		glValidateProgram(programId);
	}

	public void start(){
		glUseProgram(programId);
	}

	public void stop(){
		glUseProgram(0);
	}

	public void cleanUp(){
		stop();
		glDetachShader(programId, vertexShaderId);
		glDetachShader(programId, fragmentShaderId);
		glDeleteShader(vertexShaderId);
		glDeleteShader(fragmentShaderId);
		glDeleteProgram(programId);
	}

	protected void bindAttribute(int attribute, String variableName){
		glBindAttribLocation(programId, attribute, variableName);
	}

	protected abstract void bindAttributes();
}
