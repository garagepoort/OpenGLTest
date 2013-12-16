package shader;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.Display;

public class ShaderManager {
	
	public static int shaderProgram;
	
	public static void initializeShaders(){
		shaderProgram = glCreateProgram();
		int vertexShader = glCreateShader(ARBVertexShader.GL_VERTEX_SHADER_ARB);
		int fragmentShader = glCreateShader(ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
		
		StringBuilder vertexShaderSource = new StringBuilder();
		StringBuilder fragmentShaderSource = new StringBuilder();
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("src/shader/vertexShaderLight.vert"));
			String line;
			while((line = reader.readLine()) != null){
				vertexShaderSource.append(line).append("\n");
			}
			reader.close();
		}catch(IOException e){
			System.err.println("Vertex shader wasn't loaded properly.");
			Display.destroy();
			System.exit(1);
		}
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("src/shader/fragmentShaderLight.frag"));
			String line;
			while((line = reader.readLine()) != null){
				fragmentShaderSource.append(line).append("\n");
			}
			reader.close();
		}catch(IOException e){
			System.err.println("Fragment shader wasn't loaded properly.");
			Display.destroy();
			System.exit(1);
		}
		
		glShaderSource(vertexShader, vertexShaderSource);
		glCompileShader(vertexShader);
//		if(glGetShader(vertexShader, GL_COMPILE_STATUS) == GL_FALSE){
//			System.err.println("Vertex shader wasn't compiled correctly");
//		}
		if (ARBShaderObjects.glGetObjectParameteriARB(vertexShader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL_FALSE)
			throw new RuntimeException("Error creating shader: " + getLogInfo(vertexShader));
		
		glShaderSource(fragmentShader, fragmentShaderSource);
		glCompileShader(fragmentShader);
//		if(glGetShader(vertexShader, GL_COMPILE_STATUS) == GL_FALSE){
//			System.err.println("Vertex shader wasn't compiled correctly");
//		}
		if (ARBShaderObjects.glGetObjectParameteriARB(fragmentShader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL_FALSE)
			throw new RuntimeException("Error creating shader: " + getLogInfo(fragmentShader));
		
		glAttachShader(shaderProgram, vertexShader);
		glAttachShader(shaderProgram, fragmentShader);
		glLinkProgram(shaderProgram);
		glLinkProgram(shaderProgram);
		
		
	}
	
	private static String getLogInfo(int obj) {
		return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
		}

}
