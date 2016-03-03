package be.davidcorp.engine;

public class GameLoop {

	private FpsCalculator fpsCalculator = new FpsCalculator();
	private GameLogicExecutor gameLogicExecutor;
	private GameDrawer gameDrawer;

	public GameLoop(GameLogicExecutor gameLogicExecutor, GameDrawer gameDrawer) {
		this.gameLogicExecutor = gameLogicExecutor;
		this.gameDrawer = gameDrawer;
	}

	public void execute(ModelDrawer modelDrawer) {
		fpsCalculator.calculateDelta();

		gameLogicExecutor.executeGameLogic(fpsCalculator.getDelta());
		gameDrawer.draw(modelDrawer);

		fpsCalculator.updateFPS();
	}
}
