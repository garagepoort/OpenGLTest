package be.davidcorp.domain.game;

import static be.davidcorp.database.Filename.SAVE_FILE_LOCATION;
import static java.io.File.pathSeparator;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

import org.apache.commons.lang.StringUtils;

import be.davidcorp.database.GamefieldLoaderSaver;
import be.davidcorp.domain.exception.GameFieldException;

public class CurrentGameFieldManager {

	private static Gamefield gamefield = null;
	
	public static void loadGamefieldFromExistingGamefield(String gamefieldName){
		ZipFile zipFile= getMapFileFromGamefieldDir(new File(SAVE_FILE_LOCATION + pathSeparator + gamefieldName), gamefieldName);
		gamefield = GamefieldLoaderSaver.loadEntireField(zipFile);
	}
	
	public static void loadGameMapFromZipFile(ZipFile zipFile){
		gamefield = GamefieldLoaderSaver.loadEntireField(zipFile);
		gamefield.setGamefieldUpdater(new GamefieldCreatorUpdater());
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
	
	
	private static ZipFile getMapFileFromGamefieldDir(File gamefieldDir, String gamefieldName){
		File[] files = gamefieldDir.listFiles();
		String mapFileName = gamefieldName + "_MAPFILE.zip";
		for (File file : files) {
			if(StringUtils.equals(file.getName(), mapFileName)){
				try {
					return new ZipFile(file);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}
}
