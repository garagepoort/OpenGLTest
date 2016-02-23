package be.davidcorp.view.game;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;

import java.io.IOException;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.view.TranslationManager;
import be.davidcorp.view.light.LightManager;
import font.FontManager;

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
	public void stop() {
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void start() {

		gameThread = new Thread() {
			public void run() {
				try {
					initializeDisplay();
					initOpenGL();
					glfwShowWindow(window);
					FontManager.load();
					if(gamefieldFacade.isGamefieldInitialized()) TranslationManager.initializeBeginTranslation();

					calculateDelta();
					lastFPS = getTime();

					while (glfwWindowShouldClose(window) == GL_FALSE) {
						delta = calculateDelta();

						updateGamefield();
						updateFPS();
						getInputFromPlayer();
						updateDisplay();
					}

					glfwDestroyWindow(window);
					glfwTerminate();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

			public void updateDisplay() {
				glfwPollEvents();
				glfwSwapBuffers(window);
//				Display.sync(120); // cap fps to 60fps
			}

		};
		gameThread.start();
	}

	private void initializeDisplay(){
		glfwInit();
		glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

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
	
	private void getInputFromPlayer() {
		if (playerFacade.isPlayerAlive()) {
			playGamePanel.checkInput();
		}
	}
	private void initOpenGL() {
		glViewport(0, 0, WIDTH, HEIGHT);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, 0, HEIGHT, 1f, -1);
		glMatrixMode(GL_MODELVIEW);
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
