package be.davidcorp.domain.game;

import be.davidcorp.loaderSaver.GamefieldLoaderSaver;
import be.davidcorp.loaderSaver.repository.DefaultSpriteRepository;

public class GameFieldManager {

	private static Gamefield gamefield;
	
	public static void setCurrentGameField(Gamefield field) {
		gamefield = field;
		new DefaultSpriteRepository().emptyAllRepositories();
		new GamefieldLoaderSaver().loadEntireField(gamefield.getName());
	}

	public static Gamefield getCurrentGameField() {
		return gamefield;
	}

}
