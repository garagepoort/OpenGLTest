package be.davidcorp.view.game;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glDetachShader;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;

import java.io.IOException;
import java.nio.charset.Charset;

import com.google.common.io.ByteStreams;

public class ShaderLoader {

	public int loadShaders(String vertex_file_path, String fragment_file_path) throws IOException {
		// Create the shaders
		int vertexShaderID = glCreateShader(GL_VERTEX_SHADER);
		int fragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);

			// Read the Vertex Shader code from the file
			String vertexShaderCode = readFile(vertex_file_path, Charset.defaultCharset());

			// Read the Fragment Shader code from the file
			String fragmentShaderCode = readFile(fragment_file_path, Charset.defaultCharset());

		 	// Compile Vertex Shader
			System.out.println("Compiling shader : " +  vertex_file_path);
		 	glShaderSource(vertexShaderID, vertexShaderCode);
		 	glCompileShader(vertexShaderID);

		 	// Compile Fragment Shader
			System.out.println("Compiling shader : " +  fragment_file_path);
		 	glShaderSource(fragmentShaderID, fragmentShaderCode);
		 	glCompileShader(fragmentShaderID);

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
		}

	private String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = ByteStreams.toByteArray(this.getClass().getClassLoader().getResourceAsStream(path));
		return new String(encoded, encoding);
	}
}
