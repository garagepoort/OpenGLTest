package be.davidcorp.applicationLayer.facade;

import static be.davidcorp.domain.game.GameFieldManager.getCurrentGameField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import be.davidcorp.config.ConfigurationManager;
import be.davidcorp.database.GamefieldLoaderSaver;
import be.davidcorp.database.GamefieldSpriteLinkSaver;
import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.repository.DefaultSpriteRepository;

public class GameFacade {

	private GameFieldFacade gameFieldFacade = new GameFieldFacade();
	public void startApplication() {
		loadResources();
		gameFieldFacade.initializeGameFieldWithName("FirstDayOfInvasion", false);
	}

	public void startGameCreatorApplication() {
		gameFieldFacade.initializeGameFieldWithName("FirstDayOfInvasion", true);
	}

	public void startApplicationWithNewGamefield(String gamefieldName,
			int width, int height) {
		File f = new File("resources/playerfiles/AllPlayers.txt");
		try {
			PlayerManager.loadPlayers(f);
			new GameFieldFacade().createNewGamefield(gamefieldName, width,
					height);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveTheGame() {
		Gamefield gamefield = getCurrentGameField();

		File file = new File("resources/saveFiles/" + gamefield.getName()
				+ "/sprites.ser");
		File gamefieldlinks = new File("resources/saveFiles/"
				+ gamefield.getName() + "/gamefieldLinks.txt");

		emptyFile(file);
		emptyFile(gamefieldlinks);

		GamefieldSpriteLinkSaver gamefieldSpriteLinkSaver = new GamefieldSpriteLinkSaver(
				gamefieldlinks);
		gamefieldSpriteLinkSaver.saveSpritesFromGamefield(gamefield);

		DefaultSpriteRepository.getInstance().persistAllSprites();
	}

	private void emptyFile(File file) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
			writer.print("");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
		}
	}
	
	private void loadResources() {
		ConfigurationManager.importProperties();
		File playerFile = new File("resources/playerfiles/AllPlayers.txt");
		File gamefieldFile = new File("resources/saveFiles/gamefields.txt");

		PlayerManager.loadPlayers(playerFile);
		new GamefieldLoaderSaver().loadAllGamefieldsToRepository(gamefieldFile);
	}
}
