package be.davidcorp.domain.game;

import be.davidcorp.database.GamefieldLoaderSaver;
import be.davidcorp.domain.exception.GameFieldException;
import be.davidcorp.repository.DefaultSpriteRepository;

public class GameFieldManager {

	private static Gamefield gamefield = null;
	
	public static void setCurrentGameField(Gamefield field) {
		gamefield = field;
		DefaultSpriteRepository.getInstance().emptyRepository();
		new GamefieldLoaderSaver().loadEntireField(gamefield.getName());
	}

	public static Gamefield getCurrentGameField() {
		if(gamefield == null){
			throw new GameFieldException("No current gamefield initialized");
		}
		return gamefield;
	}

	public static boolean isGamefieldInitialized(){
		return gamefield!=null;
	}
}
