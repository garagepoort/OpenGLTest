package be.davidcorp.engine;

import static org.lwjgl.opengl.GL11.GL_INVALID_ENUM;
import static org.lwjgl.opengl.GL11.GL_INVALID_OPERATION;
import static org.lwjgl.opengl.GL11.GL_INVALID_VALUE;
import static org.lwjgl.opengl.GL11.GL_NO_ERROR;
import static org.lwjgl.opengl.GL11.GL_OUT_OF_MEMORY;
import static org.lwjgl.opengl.GL11.glGetError;
import static org.lwjgl.opengl.GL30.GL_INVALID_FRAMEBUFFER_OPERATION;

public class GLErrorChecker {

	public static void checkError() {
		int err = glGetError();
		switch(err) {
		case GL_NO_ERROR: return;
		case GL_INVALID_OPERATION: throw new RuntimeException("Invalid Operation");
		case GL_INVALID_ENUM: throw new RuntimeException("Invalid Enum");
		case GL_INVALID_VALUE: throw new RuntimeException("Invalid Value");
		case GL_INVALID_FRAMEBUFFER_OPERATION: throw new RuntimeException("Invalid Framebuffer Operation");
		case GL_OUT_OF_MEMORY: throw new RuntimeException("Out of Memory");
		}
	}
}
