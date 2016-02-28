package be.davidcorp.engine;

public class FpsCalculator {

	private long lastFrame;
	private int lastFPS;
	private int fps;
	private int delta;

	public int calculateDelta() {
		long time = getTime();
		delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	public int getDelta() {
		return delta;
	}

	private long getTime() {
		return (long) (org.lwjgl.glfw.GLFW.glfwGetTime() * 1000);
	}

	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
}
