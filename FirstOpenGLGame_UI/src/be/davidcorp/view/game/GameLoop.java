package be.davidcorp.view.game;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.view.TranslationManager;
import be.davidcorp.view.light.LightManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.nulldevice.NullSoundDevice;
import de.lessvoid.nifty.renderer.lwjgl.input.LwjglInputSystem;
import de.lessvoid.nifty.renderer.lwjgl.render.LwjglRenderDevice;
import de.lessvoid.nifty.tools.TimeProvider;
import font.FontManager;

public class GameLoop {

	private Nifty nifty;
	private long lastFrame;
	private int fps;
	private long lastFPS;
	private GamePanel playGamePanel;
	private GameFieldFacade gamefieldFacade;
	private LightManager lightManager;
	private boolean onlyRender;
	private boolean listenForInput = true;

	private static int secondsMovedInGame;
	public static int WIDTH;
	public static int HEIGHT;
	private PlayerFacade playerFacade = new PlayerFacade();

	private Thread gameThread;

	public GameLoop(GamePanel gamePanel, int width, int height,
			boolean onlyRender) throws IOException {
		this.onlyRender = onlyRender;
		initialize(gamePanel, width, height);
	}
	public GameLoop(GamePanel gamePanel, int width, int height)
			throws IOException {
		initialize(gamePanel, width, height);
	}

	public static int getSecondsMovedInGame() {
		return secondsMovedInGame;
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
					FontManager.load();
					TranslationManager.initializeBeginTranslation();

					secondsMovedInGame = calculateSecondsMovedInGame();
					lastFPS = getTime();

					initializeNifty();
					while (!Display.isCloseRequested()) {
						secondsMovedInGame = calculateSecondsMovedInGame();

						getInputFromPlayer();
						updateGamefield();
						playGamePanel.render();
						renderNifty();

						updateDisplay();
					}

					Display.destroy();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

			public void updateDisplay() {
				updateFPS(); // update FPS Counter
				Display.update();
				Display.sync(120); // cap fps to 60fps
			}

		};
		gameThread.start();
	}

	private void initializeNifty() throws Exception {
		LwjglInputSystem inputSystem = new LwjglInputSystem();
		inputSystem.startup();
		nifty = new Nifty(new LwjglRenderDevice(), new NullSoundDevice(), inputSystem, new TimeProvider());
	}

	private void renderNifty() {
		int mouseX = Mouse.getX();
		int mouseY = Display.getDisplayMode().getHeight() - Mouse.getY();
		nifty.render(false);
	}

	private void ifGameNotPausedUpdateGamefield() {
		if (!gamefieldFacade.isGamePaused()) {
			gamefieldFacade.updateGameField(secondsMovedInGame);
		}
	}

	private void initializeDisplay() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(800, 600));
		Display.setResizable(false);
		Display.setFullscreen(false);
		Display.create(new PixelFormat(0, 0, 1));
	}

	private void updateGamefield() {
		if (playerFacade.isPlayerAlive() && !onlyRender) {
			ifGameNotPausedUpdateGamefield();
		}
		lightManager.traceAllLights();
	}

	private void getInputFromPlayer() {
		if (playerFacade.isPlayerAlive() && listenForInput) {
			playGamePanel.checkInput();
		}
	}
	private void initOpenGL() {
		glViewport(0, 0, WIDTH, HEIGHT);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}

	private int calculateSecondsMovedInGame() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	private void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
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

	public void setListenForInput(boolean listenForInput) {
		this.listenForInput = listenForInput;
	}
}
