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
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.PixelFormat;

import be.davidcorp.applicationLayer.facade.GameFieldFacade;
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

	private static int secondsMovedInGame;
	public static int WIDTH;
	public static int HEIGHT;

	public GameLoop(GamePanel gamePanel, int width, int height) throws IOException {
		playGamePanel = gamePanel;
		GameLoop.WIDTH = width;
		GameLoop.HEIGHT = height;
		gamefieldFacade = new GameFieldFacade();
		lightManager = new LightManager();
	}

	Thread gameThread;

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
					// ShaderManager.initializeShaders();
					TranslationManager.initializeBeginTranslation();

					secondsMovedInGame = calculateSecondsMovedInGame();
					lastFPS = getTime();

					while (!Display.isCloseRequested()) {
						secondsMovedInGame = calculateSecondsMovedInGame();

						playGamePanel.checkInput();

						ifGameNotPausedUpdateGamefield();

						lightManager.traceAllLights();
						playGamePanel.render();

						updateFPS(); // update FPS Counter
						Display.update();
						Display.sync(120); // cap fps to 60fps
					}

					Display.destroy();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		gameThread.start();
	}

	private void ifGameNotPausedUpdateGamefield() {
		if (!gamefieldFacade.isGamePaused()) {
			gamefieldFacade.updateGameField(secondsMovedInGame);
		}
	}

	private void initializeDisplay() throws LWJGLException {
//		Display.setDisplayMode(new DisplayMode(800, 600));
		Display.setResizable(false);
		 Display.setFullscreen(false);
		Display.create(new PixelFormat(0, 0, 1));
	}

	private void initOpenGL() {
		glViewport(0, 0, WIDTH, HEIGHT);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}

	public static int getSecondsMovedInGame() {
		return secondsMovedInGame;
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
}
