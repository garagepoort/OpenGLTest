package be.davidcorp.applicationLayer.facade;

import static be.davidcorp.domain.game.GameFieldManager.getCurrentGameField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import be.davidcorp.config.ConfigurationManager;
import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.loaderSaver.GamefieldLoaderSaver;
import be.davidcorp.loaderSaver.GamefieldSpriteLinkSaver;
import be.davidcorp.loaderSaver.filehandling.SpriteSerializer;
import be.davidcorp.loaderSaver.repository.DefaultSpriteRepository;

public class GameFacade {

	public void startApplication() {
		ConfigurationManager.importProperties();
		File playerFile = new File("resources/playerfiles/AllPlayers.txt");
		File gamefieldFile = new File("resources/saveFiles/gamefields.txt");

		try {
			PlayerManager.loadPlayers(playerFile);

			new GamefieldLoaderSaver().loadAllGamefieldsToRepository(gamefieldFile);
			new GameFieldFacade().initializeGameFieldWithName("FirstDayOfInvasion");
//			MissionManager.createFirstMission();
		} catch (Exception e) {
			throw new RuntimeException(e);
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
		
		File file = new File("resources/saveFiles/" + gamefield.getName() + "/sprites.ser");
		File gamefieldlinks = new File("resources/saveFiles/" + gamefield.getName() + "/gamefieldLinks.txt");
		emptyFile(file);
		emptyFile(gamefieldlinks);
		SpriteSerializer spriteSerializer = new SpriteSerializer(file);
		GamefieldSpriteLinkSaver gamefieldSpriteLinkSaver = new GamefieldSpriteLinkSaver(gamefieldlinks);
		try {
			gamefieldSpriteLinkSaver.saveSpritesFromGamefield(gamefield);
			spriteSerializer.serializeSprites(new DefaultSpriteRepository().getAllSprites());
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
