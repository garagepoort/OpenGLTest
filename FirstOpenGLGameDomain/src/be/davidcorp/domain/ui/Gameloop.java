package be.davidcorp.domain.ui;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_STENCIL_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import be.davidcorp.domain.World;
import be.davidcorp.domain.entity.Sprite;
import be.davidcorp.domain.entity.SpriteFactory;
import be.davidcorp.domain.system.FiringSystem;
import be.davidcorp.domain.system.MovementSystem;
import be.davidcorp.domain.system.PlayerInputSystem;
import be.davidcorp.domain.system.RenderSystem;
import be.davidcorp.domain.system.TimeToLiveSystem;

public class Gameloop {
	private long lastFrame;
	private int fps;
	private long lastFPS;

	private static int secondsMovedInGame;
	public static int WIDTH = 800;
	public static int HEIGHT = 600;

	Thread gameThread;

//	public List<Sprite> sprites = newArrayList();

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
					
					World.getSprites().add(SpriteFactory.createPlayer());

					secondsMovedInGame = calculateSecondsMovedInGame();
					lastFPS = getTime();

					while (!Display.isCloseRequested()) {
						glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
						secondsMovedInGame = calculateSecondsMovedInGame();

						for (Sprite sprite : World.getSprites()) {
							PlayerInputSystem.getInstance().executeSystem(sprite, secondsMovedInGame);
							FiringSystem.getInstance().executeSystem(sprite, secondsMovedInGame);
							MovementSystem.getInstance().executeSystem(sprite, secondsMovedInGame);
							RenderSystem.getInstance().executeSystem(sprite, secondsMovedInGame);
							TimeToLiveSystem.getInstance().executeSystem(sprite, secondsMovedInGame);
						}
						World.update();

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

	private void initializeDisplay() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(800, 600));
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
