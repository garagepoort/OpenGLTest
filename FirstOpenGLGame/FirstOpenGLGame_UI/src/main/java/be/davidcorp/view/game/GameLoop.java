package be.davidcorp.view.game;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.glClearColor;

import java.io.IOException;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;

import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.engine.Display;
import be.davidcorp.view.FrameBuffer;
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

	private GLFWErrorCallback errorCallback;
	private GLFWFramebufferSizeCallback framebufferSizeCallback;
	private FrameBuffer framebuffer = new FrameBuffer();
	private StaticShaderProgram shaderProgram;

	private long window;
	private Projection projection = new Projection();

	private Loader loader = new Loader();
	private Renderer renderer = new Renderer();

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

	public void start() throws IOException {
		window = Display.setup(800, 600);
		initResizeCallback();
		shaderProgram = new StaticShaderProgram();

		float[] triangleData = {
				-1.0f, -1.0f, 0.0f,
				1.0f, -1.0f, 0.0f,
				0.0f, 1.0f, 0.0f,
		};


//					FontManager.load();
//		if (gamefieldFacade.isGamefieldInitialized()){
//			TranslationManager.initializeBeginTranslation();
//		}

		loader.createVao();

		calculateDelta();
		lastFPS = getTime();

		while (glfwWindowShouldClose(window) == GLFW_FALSE) {
			glClearColor(0.0f, 0.0f, 0.4f, 0.0f);
			delta = calculateDelta();

			RawModel rawModel = loader.loadToVao(triangleData);

			renderer.prepare();

			shaderProgram.start();
			renderer.render(rawModel);
			shaderProgram.stop();

			updateFPS();
			Display.updateDisplay();
		}
		shaderProgram.cleanUp();
		loader.cleanUp();
		Display.stopOpenGl();
	}

	private void initResizeCallback() {
		glfwSetFramebufferSizeCallback(window, (framebufferSizeCallback = new GLFWFramebufferSizeCallback() {

			@Override
			public void invoke(long window, int width, int height) {
				onResize(width, height);
			}

		}));
		onResize(WIDTH, HEIGHT);
	}

	private void onResize(int framebufferWidth, int framebufferHeight) {
		projection.left = 0;
		projection.right = framebufferWidth;
		projection.bottom = 0;
		projection.top = framebufferHeight;
		framebuffer.width = framebufferWidth;
		framebuffer.height = framebufferHeight;
		MVPManager.getInstance().setProjection(projection);
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
