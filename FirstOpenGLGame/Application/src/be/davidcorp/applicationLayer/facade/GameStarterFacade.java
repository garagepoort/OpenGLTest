package be.davidcorp.applicationLayer.facade;

import static be.davidcorp.domain.game.CurrentGameFieldManager.getCurrentGameField;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.applicationLayer.exception.ServiceException;
import be.davidcorp.config.ConfigurationManager;
import be.davidcorp.config.ImageLocationManager;
import be.davidcorp.database.GamefieldLoaderSaver;
import be.davidcorp.domain.game.CurrentGameFieldManager;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;

public class GameStarterFacade {

	public void startApplication() {
		loadResources();
		initializeGamefield("FirstDayOfInvasion");
	}

	public void startGameCreatorApplication() {
		loadResources();
	}

	public void saveCustomCreatedGamefield(String filename) {
		GamefieldLoaderSaver.saveCustomCreatedGamefieldToFile(getCurrentGameField(), filename);
	}

	public void openGameMap(String fileName) {
		try {
			ZipFile zipFile = new ZipFile(fileName);
			GamefieldLoaderSaver.loadEntireField(zipFile);
		} catch (IOException e) {
			throw new ServiceException(e);
		}

	}

	private void loadResources() {
		ConfigurationManager.importProperties();
		ImageLocationManager.importProperties();
		File playerFile = new File("resources/playerfiles/AllPlayers.txt");

		PlayerManager.loadPlayers(playerFile);
	}
	
	private void initializeGamefield(String name){
		try {
			CurrentGameFieldManager.loadGamefieldFromExistingGamefield(name);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}
}
