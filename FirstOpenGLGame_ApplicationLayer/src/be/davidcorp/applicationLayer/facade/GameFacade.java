package be.davidcorp.applicationLayer.facade;


import java.io.File;
import java.io.IOException;

import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.exception.InventoryException;
import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.mission.MissionManager;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.loaderSaver.GamefieldLoaderSaver;
import be.davidcorp.loaderSaver.LoaderException;

public class GameFacade {

	public void startApplication(){
		File playerFile = new File("resources/playerfiles/AllPlayers.txt");
		File gamefieldFile = new File("resources/saveFiles/gamefields.txt");
		
		try {
//			SoundManager.loadSounds(soundsFile);
			PlayerManager.loadPlayers(playerFile);
			
			new GamefieldLoaderSaver().loadAllGamefieldsToRepository(gamefieldFile);
			new GameFieldFacade().initializeGameFieldWithName("field1");
			MissionManager.createFirstMission();
		} catch (IOException | SpriteException | InventoryException | LoaderException | ModelException e) {
			e.printStackTrace();
		}
	}
	
	public void startApplicationWithNewGamefield(String gamefieldName, int width, int height) throws ModelException{
		File f = new File("resources/playerfiles/AllPlayers.txt");
		try {
			PlayerManager.loadPlayers(f);
			new GameFieldFacade().createNewGamefield(gamefieldName, width, height);
		} catch (IOException | SpriteException | InventoryException e) {
			e.printStackTrace();
		}
	}
}
