package be.davidcorp.applicationLayer.facade;

import java.util.zip.ZipFile;

import be.davidcorp.database.GamefieldLoaderSaver;
import be.davidcorp.domain.game.CurrentGameFieldManager;
import be.davidcorp.domain.game.Gamefield;

public class GamecreationFacade {

	public ZipFile createAndSaveGamefieldToLocation(String gamefieldName, String saveLocation){
		Gamefield gamefield= new  Gamefield(gamefieldName, 1600, 1800);
		return GamefieldLoaderSaver.saveCustomCreatedGamefieldToFile(gamefield, saveLocation);
	}
	
	public void loadCreatedGamefield(ZipFile file){
		CurrentGameFieldManager.loadGameMapFromZipFile(file);
	}
	
	
}
