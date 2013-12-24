package be.davidcorp.loaderSaver;

import static com.google.common.collect.Lists.newArrayList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import be.davidcorp.FileUtility;
import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.loaderSaver.filehandling.SpriteFileLoader;
import be.davidcorp.loaderSaver.filehandling.SpriteFileLoaderUtilities;
import be.davidcorp.loaderSaver.filehandling.SpriteFileWriter;
import be.davidcorp.loaderSaver.repository.GamefieldRepository;

public class GamefieldLoaderSaver {

	private FileUtility fileUtility = new FileUtility();
	private GamefieldRepository gamefieldRepository = new GamefieldRepository();
	private List<String> gamefieldStrings = new ArrayList<String>();

	public void save(File file, Gamefield gamefield) {
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			HashMap<GamefieldProperty, String> properties = new HashMap<>();
			fillGamefieldProperties(gamefield, properties);
			SpriteFileLoaderUtilities.addGamefieldPropertiesToPrintWriter(properties, printWriter);
		} catch (Exception exception) {
			throw new LoaderException(exception);
		} finally {
			if (printWriter != null)
				printWriter.close();
		}
	}

	public void loadAllGamefieldsToRepository(File file) throws IOException {
		Scanner scanner = null;
		try {
			scanner = new Scanner(fileUtility.getFileContent(file));
			while (scanner.hasNextLine()) {
				StringBuilder builder = new StringBuilder();
				gamefieldStrings.add(createGamefieldString(scanner, builder));
			}
			gamefieldRepository.loadGamefields(gamefieldStrings);
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}

	private String createGamefieldString(Scanner scanner, StringBuilder builder) {
		while (scanner.hasNextLine()) {
			String nextline = scanner.nextLine();
			if (nextline.contains("END"))
				break;
			builder.append(nextline + "\n");
		}
		builder.setLength(builder.length() - 1);
		return builder.toString();
	}

	/*
	 * For tests only
	 */
	private void fillGamefieldProperties(Gamefield gamefield, HashMap<GamefieldProperty, String> properties) {
		properties.put(GamefieldProperty.GAMEFIELDNAME, gamefield.getName());
		properties.put(GamefieldProperty.ID, String.valueOf(gamefield.getID()));
		properties.put(GamefieldProperty.WIDTH, String.valueOf(gamefield.getWidth()));
		properties.put(GamefieldProperty.HEIGHT, String.valueOf(gamefield.getHeight()));
	}

	public void setFileUtility(FileUtility fileUtility) {
		this.fileUtility = fileUtility;
	}

	public void loadEntireField(String name) {
		File spritesFile = new File("resources/saveFiles/" + name + "/sprites.txt");
		File triggerLinksFile = new File("resources/saveFiles/" + name + "/triggerLinks.txt");

		new SpriteFileLoader(spritesFile).loadAllSprites();
		new TriggerLoader().loadTriggers((triggerLinksFile));
	}

	public void saveEntireField(Gamefield gamefield) {
		File spritesFile = new File("resources/saveFiles/" + gamefield.getName() + "/sprites.txt");
		File triggerLinksFile = new File("resources/saveFiles/" + gamefield.getName() + "/triggerLinks.txt");
		spritesFile.getParentFile().mkdirs();
		triggerLinksFile.getParentFile().mkdirs();

		new SpriteFileWriter(spritesFile).saveSprites(getAllGamefieldSprites(gamefield));
		// new
		// TriggerLoader().loadTriggers(fileUtility.getFileContent(triggerLinksFile));
	}

	private List<Sprite> getAllGamefieldSprites(Gamefield gamefield) {
		List<Sprite> sprites = newArrayList();
		sprites.addAll(gamefield.getEnemiesInWorld());
		sprites.addAll(gamefield.getGroundItems());
		sprites.addAll(gamefield.getLightsFromWorld());
		sprites.addAll(gamefield.getConstructionItems());
		return sprites;
	}
}
