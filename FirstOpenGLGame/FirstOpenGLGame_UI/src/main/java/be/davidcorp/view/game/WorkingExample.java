package be.davidcorp.view.game;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;

import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.GameStarterFacade;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.engine.Display;
import be.davidcorp.view.FrameBuffer;
import be.davidcorp.view.light.LightManager;

public class WorkingExample {

	private long lastFrame;
	private int fps;
	private long lastFPS;
	private GamePanel playGamePanel;
	private GameFieldFacade gamefieldFacade;
	private LightManager lightManager;
	private boolean onlyRender;

	private static int delta;
	public static int WIDTH;
	public static int HEIGHT;
	private PlayerFacade playerFacade = new PlayerFacade();

	private GLFWErrorCallback errorCallback;
	private GLFWFramebufferSizeCallback framebufferSizeCallback;
	private FrameBuffer framebuffer = new FrameBuffer();
	private ShaderLoader shaderLoader = new ShaderLoader();

	private int VAO;
	private int VBO;
	private long window;

	public WorkingExample(GamePanel gamePanel, int width, int height, boolean onlyRender) throws IOException {
		this.onlyRender = onlyRender;
		initialize(gamePanel, width, height);
	}

	public WorkingExample(GamePanel gamePanel, int width, int height)
			throws IOException {
		initialize(gamePanel, width, height);
	}

	public static int getSecondsMovedInGame() {
		return delta;
	}

	public void start() throws IOException {
		window = Display.setup(WIDTH, HEIGHT);
		initResizeCallback();
		playGamePanel.registerInputCallbacks(window, framebuffer);

		VAO = glGenVertexArrays();
		VBO = glGenBuffers();

		glBindVertexArray(VAO);

		int programId = shaderLoader.loadShaders("shaders/SimpleVertexShader.vertexshader", "shaders/SimpleFragmentShader.fragmentshader");

		float[] g_vertex_buffer_data = {
				-1.0f, -1.0f, 0.0f,
				1.0f, -1.0f, 0.0f,
				0.0f,  1.0f, 0.0f,
		};
		FloatBuffer fb = BufferUtils.createFloatBuffer(9);
		fb.put(g_vertex_buffer_data).flip();

		glBindBuffer(GL_ARRAY_BUFFER, VBO);
		glBufferData(GL_ARRAY_BUFFER, fb, GL_STATIC_DRAW);

		try {

			//			FontManager.load();
			//			if(gamefieldFacade.isGamefieldInitialized()) TranslationManager.initializeBeginTranslation();

			calculateDelta();
			lastFPS = getTime();

			while (glfwWindowShouldClose(window) == GLFW_FALSE) {
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
				glUseProgram(programId);
				delta = calculateDelta();
				//				updateGamefield();

				glEnableVertexAttribArray(0);
				glBindBuffer(GL_ARRAY_BUFFER, VBO);
				glVertexAttribPointer(
						0,                  // attribute 0. No particular reason for 0, but must match the layout in the shader.
						3,                  // size
						GL_FLOAT,           // type
						false,           // normalized?
						0,                  // stride
						0            // array buffer offset
				);

				// Draw the triangle !
				glDrawArrays(GL_TRIANGLES, 0, 3); // 3 indices starting at 0 -> 1 triangle

				glDisableVertexAttribArray(0);

				updateFPS();
				Display.updateDisplay();
			}
			glDeleteBuffers(VBO);
			glDeleteVertexArrays(VAO);
			glDeleteProgram(programId);
			Display.stopOpenGl();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void initResizeCallback(){
		glfwSetFramebufferSizeCallback(window, (framebufferSizeCallback = new GLFWFramebufferSizeCallback() {

			@Override
			public void invoke(long window, int width, int height) {
				onResize(width, height);
			}

		}));
		onResize(WIDTH, HEIGHT);
	}

	private void onResize(int framebufferWidth, int framebufferHeight) {
		framebuffer.width = framebufferWidth;
		framebuffer.height = framebufferHeight;
	}

	private void updateGamefield() throws IOException {
		if (gamefieldFacade.isGamefieldInitialized()) {
			if (playerFacade.isPlayerAlive() && !onlyRender) {
				ifGameNotPausedUpdateGamefield();
			}
			lightManager.traceAllLights();
			playGamePanel.render();
		}
	}

	private void ifGameNotPausedUpdateGamefield() {
		if (!gamefieldFacade.isGamePaused()) {
			gamefieldFacade.updateGameField(delta);
		}
	}

	private int calculateDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	private long getTime() {
		return (long) (org.lwjgl.glfw.GLFW.glfwGetTime() * 1000);
	}

	private void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			//			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	private void initialize(GamePanel gamePanel, int width, int height) {
		playGamePanel = gamePanel;
		GameLoop.WIDTH = width;
		GameLoop.HEIGHT = height;
		gamefieldFacade = new GameFieldFacade();
		lightManager = new LightManager();
	}

	public static void main(String[] args) throws IOException {
		NativeLoader.loadNatives();
		new GameStarterFacade().startApplication();
		new WorkingExample(new PlayGamePanel(), 800, 600).start();
	}
}
