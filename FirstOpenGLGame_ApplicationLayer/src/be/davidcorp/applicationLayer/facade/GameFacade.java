package be.davidcorp.applicationLayer.facade;

import static be.davidcorp.domain.game.GameFieldManager.getCurrentGameField;

import java.io.File;
import java.io.IOException;

import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.mission.MissionManager;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.loaderSaver.GamefieldLoaderSaver;
import be.davidcorp.loaderSaver.filehandling.SpriteFileWriter;
import be.davidcorp.loaderSaver.repository.ConstructionSpriteRepository;
import be.davidcorp.loaderSaver.repository.EnemyRepository;
import be.davidcorp.loaderSaver.repository.ItemRepository;
import be.davidcorp.loaderSaver.repository.LightRepository;

public class GameFacade {

	public void startApplication() {
		File playerFile = new File("resources/playerfiles/AllPlayers.txt");
		File gamefieldFile = new File("resources/saveFiles/gamefields.txt");

		try {
			// SoundManager.loadSounds(soundsFile);
			PlayerManager.loadPlayers(playerFile);

			new GamefieldLoaderSaver().loadAllGamefieldsToRepository(gamefieldFile);
			new GameFieldFacade().initializeGameFieldWithName("field1");
			MissionManager.createFirstMission();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startApplicationWithNewGamefield(String gamefieldName, int width, int height) {
		File f = new File("resources/playerfiles/AllPlayers.txt");
		try {
			PlayerManager.loadPlayers(f);
			new GameFieldFacade().createNewGamefield(gamefieldName, width, height);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveTheGame() {
		Gamefield gamefield = getCurrentGameField();
		File file = new File("resources/saveFiles/" + gamefield.getName() + "/sprites.txt");
		file.mkdirs();
		SpriteFileWriter spriteFileWriter = new SpriteFileWriter(file);
		try {
			spriteFileWriter.saveSprites(new ConstructionSpriteRepository().getAllSprites());
			spriteFileWriter.saveSprites(new EnemyRepository().getAllSprites());
			spriteFileWriter.saveSprites(new LightRepository().getAllSprites());
			spriteFileWriter.saveSprites(new ItemRepository().getAllSprites());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// save the player.
		// saves the spriteRepositories
		// save the triggers.
		// save the gamefield links.
	}
}
