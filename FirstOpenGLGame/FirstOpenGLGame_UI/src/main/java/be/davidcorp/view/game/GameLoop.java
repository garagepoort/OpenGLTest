package main.java.be.davidcorp.view.game;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;

import java.io.IOException;

import main.java.be.davidcorp.view.TranslationManager;
import main.java.be.davidcorp.view.light.LightManager;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import main.java.font.FontManager;

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
					FontManager.load();
					if(gamefieldFacade.isGamefieldInitialized()) TranslationManager.initializeBeginTranslation();

					calculateDelta();
					lastFPS = getTime();

					while (!Display.isCloseRequested()) {
						delta = calculateDelta();

						updateGamefield();
						updateFPS();
						getInputFromPlayer();
						updateDisplay();
					}

					Display.destroy();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

			public void updateDisplay() {
				Display.update();
				Display.sync(120); // cap fps to 60fps
			}

		};
		gameThread.start();
	}

	private void initializeDisplay() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(800, 600));
		Display.setResizable(false);
		Display.setFullscreen(false);
		Display.create(new PixelFormat(0, 0, 1));
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

}
