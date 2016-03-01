package be.davidcorp.engine;

import static be.davidcorp.engine.GLErrorChecker.checkError;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_INFO_LOG_LENGTH;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glDetachShader;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderiv;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;

import java.io.IOException;
import java.nio.IntBuffer;
import java.nio.charset.Charset;

import org.lwjgl.opengl.GL11;

import com.google.common.io.ByteStreams;
import com.jogamp.common.nio.Buffers;

public class ShaderLoader {

	public int loadShader(String file, int shaderType) {
		try {
			int shaderID = glCreateShader(shaderType);

			String shaderCode = readFile(file, Charset.defaultCharset());

			System.out.println("Compiling shader : " + file);
			glShaderSource(shaderID, shaderCode);
			glCompileShader(shaderID);

			return shaderID;
		} catch (IOException e) {
			throw new RuntimeException("Failed to load shaders");
		}
	}

	public int loadShaders(String vertex_file_path, String fragment_file_path) {
		try {
			// Create the shaders
			int vertexShaderID = glCreateShader(GL_VERTEX_SHADER);
			int fragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);

			// Read the Vertex Shader code from the file
			String vertexShaderCode = readFile(vertex_file_path, Charset.defaultCharset());

			// Read the Fragment Shader code from the file
			String fragmentShaderCode = readFile(fragment_file_path, Charset.defaultCharset());

			// Compile Vertex Shader
			System.out.println("Compiling shader : " + vertex_file_path);
			glShaderSource(vertexShaderID, vertexShaderCode);
			glCompileShader(vertexShaderID);

			checkError();
			checkShaderCompilation(vertexShaderID);

			// Compile Fragment Shader
			System.out.println("Compiling shader : " + fragment_file_path);
			glShaderSource(fragmentShaderID, fragmentShaderCode);
			glCompileShader(fragmentShaderID);

			checkShaderCompilation(fragmentShaderID);

			// Link the program
			System.out.println("Linking program");
			int programID = glCreateProgram();
			glAttachShader(programID, vertexShaderID);
			glAttachShader(programID, fragmentShaderID);
			glLinkProgram(programID);

			glDetachShader(programID, vertexShaderID);
			glDetachShader(programID, fragmentShaderID);

			glDeleteShader(vertexShaderID);
			glDeleteShader(fragmentShaderID);

			return programID;
		} catch (IOException e) {
			throw new RuntimeException("Could not load shaders", e);
		}
	}

	private void checkShaderCompilation(int shaderId) {
		IntBuffer statusShader = Buffers.newDirectIntBuffer(1);
		glGetShaderiv(shaderId, GL_COMPILE_STATUS, statusShader);
		if(statusShader.get() == GL11.GL_FALSE)
		{
			IntBuffer errorBuffer = Buffers.newDirectIntBuffer(1);
			glGetShaderiv(shaderId, GL_INFO_LOG_LENGTH, errorBuffer);
			int length = errorBuffer.get(0);
			String error = glGetShaderInfoLog(shaderId, length);
			System.out.print("ERROR: " + error + "\n");
			throw new RuntimeException("Failed to compile shader");
		}
	}

	private String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = ByteStreams.toByteArray(this.getClass().getClassLoader().getResourceAsStream(path));
		return new String(encoded, encoding);
	}
}
