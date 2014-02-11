package be.davidcorp.domain.utilities;

public class PauseManager {

	private static boolean paused;
	
	public static boolean isGamePaused(){
		return paused;
	}

	public static void setGamePaused(boolean pausedGame){
		paused = pausedGame;
	}
	
}
