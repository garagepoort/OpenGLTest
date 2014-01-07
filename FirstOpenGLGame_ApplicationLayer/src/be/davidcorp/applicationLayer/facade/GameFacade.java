package be.davidcorp.applicationLayer.facade;

import static be.davidcorp.domain.game.GameFieldManager.getCurrentGameField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import be.davidcorp.config.ConfigurationManager;
import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.mission.MissionManager;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.loaderSaver.GamefieldLoaderSaver;
import be.davidcorp.loaderSaver.GamefieldSpriteLinkSaver;
import be.davidcorp.loaderSaver.filehandling.SpriteFileWriter;
import be.davidcorp.loaderSaver.repository.ConstructionSpriteRepository;

public class GameFacade {

	public void startApplication() {
		ConfigurationManager.importProperties();
		
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
		File gamefieldlinks = new File("resources/saveFiles/" + gamefield.getName() + "/gamefieldLinks.txt");
		emptyFile(file);
		emptyFile(gamefieldlinks);
		SpriteFileWriter spriteFileWriter = new SpriteFileWriter(file);
		GamefieldSpriteLinkSaver gamefieldSpriteLinkSaver = new GamefieldSpriteLinkSaver(gamefieldlinks);
		try {
			spriteFileWriter.saveSprites(new ConstructionSpriteRepository().getAllSprites());
			gamefieldSpriteLinkSaver.saveSpritesFromGamefield(gamefield);
//			spriteFileWriter.saveSprites(new EnemyRepository().getAllSprites());
//			spriteFileWriter.saveSprites(new LightRepository().getAllSprites());
//			spriteFileWriter.saveSprites(new ItemRepository().getAllSprites());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// save the player.
		// saves the spriteRepositories
		// save the triggers.
		// save the gamefield links.
	}
	
	private void emptyFile(File file){
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
			writer.print("");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(writer != null) writer.close();
		}
	}
}
