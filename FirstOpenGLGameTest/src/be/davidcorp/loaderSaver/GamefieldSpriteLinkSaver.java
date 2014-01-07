package be.davidcorp.loaderSaver;

import static com.google.common.collect.Lists.newArrayList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.sprite.Sprite;

public class GamefieldSpriteLinkSaver {

	private File file;

	public GamefieldSpriteLinkSaver(File file) {
		this.file = file;
	}

	public void saveSpritesFromGamefield(Gamefield gamefield) {
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			printWriter.println("GAMEFIELD:" + gamefield.getID());
			for (Sprite sprite : getAllGamefieldSprites(gamefield)) {
				if (sprite.getID() > 0) {
					printWriter.println("SPRITE:" + sprite.getID());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null)
				printWriter.close();
		}
	}

	private List<Sprite> getAllGamefieldSprites(Gamefield gamefield) {
		List<Sprite> sprites = newArrayList();
		// sprites.addAll(gamefield.getEnemiesInWorld());
		// sprites.addAll(gamefield.getGroundItems());
		// sprites.addAll(gamefield.getLightsFromWorld());
		sprites.addAll(gamefield.getConstructionItems());
		return sprites;
	}
}
