package be.davidcorp.view.game;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_FORWARD_COMPAT;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_SAMPLES;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.opengl.GL;

import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.view.FrameBuffer;
import be.davidcorp.view.TranslationManager;
import be.davidcorp.view.light.LightManager;

public class GameLoop {

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

	private Thread gameThread;
	private GLFWErrorCallback errorCallback;
	private long window;
	private GLFWFramebufferSizeCallback framebufferSizeCallback;
	private FrameBuffer framebuffer = new FrameBuffer();
	private ShaderLoader shaderLoader = new ShaderLoader();

	public GameLoop(GamePanel gamePanel, int width, int height, boolean onlyRender) throws IOException {
		this.onlyRender = onlyRender;
		initialize(gamePanel, width, height);
	}
	
	public GameLoop(GamePanel gamePanel, int width, int height)
			throws IOException {
		initialize(gamePanel, width, height);
	}

	public static int getSecondsMovedInGame() {
		return delta;
	}

	public void start() {
		initializeDisplay();

		FloatBuffer fb = BufferUtils.createFloatBuffer(9);
		float[] g_vertex_buffer_data = {
				-1.0f, -1.0f, 0.0f,
				1.0f, -1.0f, 0.0f,
				0.0f,  1.0f, 0.0f,
		};

		fb.put(g_vertex_buffer_data);

		int vertexbuffer = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vertexbuffer);
		glBufferData(GL_ARRAY_BUFFER, fb, GL_STATIC_DRAW);

		try {
			initResizeCallback();
			playGamePanel.registerInputCallbacks(window, framebuffer);
			int programId = shaderLoader.loadShaders("shaders/SimpleVertexShader.vertexshader", "shaders/SimpleFragmentShader.fragmentshader");

			//			FontManager.load();
			if(gamefieldFacade.isGamefieldInitialized()) TranslationManager.initializeBeginTranslation();

			calculateDelta();
			lastFPS = getTime();
			glClearColor(0.0f, 0.0f, 0.4f, 0.0f);

			while (glfwWindowShouldClose(window) == GLFW_FALSE) {
				glClear( GL_COLOR_BUFFER_BIT );
				glUseProgram(programId);
				delta = calculateDelta();
//				updateGamefield();

				glEnableVertexAttribArray(0);
				glBindBuffer(GL_ARRAY_BUFFER, vertexbuffer);
				glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
//				glVertexAttribPointer(
//						0,                  // attribute 0. No particular reason for 0, but must match the layout in the shader.
//						3,                  // size
//						GL_FLOAT,           // type
//						GL_FALSE,           // normalized?
//						0                // stride
//						          // array buffer offset
//				);

				// Draw the triangle !
				glDrawArrays(GL_TRIANGLES, 0, 3); // 3 indices starting at 0 -> 1 triangle

				glDisableVertexAttribArray(0);

				updateFPS();
				updateDisplay();
			}

			glfwDestroyWindow(window);
			glfwTerminate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void updateDisplay() {
		glfwPollEvents();
		glfwSwapBuffers(window);
		//				Display.sync(120); // cap fps to 60fps
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

	private void initializeDisplay(){
		if(glfwInit() == 0)
		{
			throw new RuntimeException("failed to initialize opengl");
		}

		glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
		glfwWindowHint(GLFW_SAMPLES, 4);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE); // To make MacOS happy; should not be needed
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

		window = glfwCreateWindow(800, 600, "Zombicide", 0, 0);
		if(window == 0) {
			throw new RuntimeException("Failed to create window");
		}
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
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

}
