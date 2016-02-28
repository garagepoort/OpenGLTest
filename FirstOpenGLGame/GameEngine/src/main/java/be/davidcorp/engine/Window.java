package be.davidcorp.engine;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Window {

	public static void createWindow(GameLoop gameLoop){
		long window = Display.setup();

		while (glfwWindowShouldClose(window) == GLFW_FALSE) {
			gameLoop.execute();
			Display.updateDisplay();
		}
		Display.stopOpenGl();
	}
}
