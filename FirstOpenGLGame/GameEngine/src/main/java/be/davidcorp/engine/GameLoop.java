package be.davidcorp.engine;

public class GameLoop {

	private FpsCalculator fpsCalculator = new FpsCalculator();

	public void execute() {
		fpsCalculator.calculateDelta();
		//render
		fpsCalculator.updateFPS();
	}
}
