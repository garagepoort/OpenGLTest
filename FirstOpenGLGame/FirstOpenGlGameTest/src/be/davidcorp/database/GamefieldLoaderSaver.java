package be.davidcorp.database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import be.davidcorp.FileUtility;
import be.davidcorp.database.repository.DefaultSpriteRepository;
import be.davidcorp.database.repository.GamefieldRepository;
import be.davidcorp.domain.game.Gamefield;

public class GamefieldLoaderSaver {

	private FileUtility fileUtility = new FileUtility();
	private GamefieldRepository gamefieldRepository = new GamefieldRepository();
	private List<String> gamefieldStrings = new ArrayList<String>();

	public void saveEntireField(Gamefield gamefield) {
		createFilesIfNoneExisting(gamefield);
//		new SpriteFileWriter(spritesFile).saveSprites(getAllGamefieldSprites(gamefield));
//		new TriggerFileWriter(triggerLinksFile).saveTriggerLinks(getAllTriggerFromSprites(getAllGamefieldSprites(gamefield)));
		// new TriggerLoader().loadTriggers(fileUtility.getFileContent(triggerLinksFile));
	}

	public void createFilesIfNoneExisting(Gamefield gamefield) {
		File spritesFile = new File("resources/saveFiles/" + gamefield.getName() + "/sprites.txt");
		File triggerLinksFile = new File("resources/saveFiles/" + gamefield.getName() + "/triggerLinks.txt");
		File gamefieldLinksFile = new File("resources/saveFiles/" + gamefield.getName() + "/gamefieldLinks.txt");
		
		spritesFile.getParentFile().mkdirs();
		triggerLinksFile.getParentFile().mkdirs();
		gamefieldLinksFile.getParentFile().mkdirs();
		
		try {
			spritesFile.createNewFile();
			triggerLinksFile.createNewFile();
			gamefieldLinksFile.createNewFile();
			
		} catch (IOException e) {
			throw new RuntimeException("Something went wrong with the creation of the gamefield files");
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

	public void loadEntireField(String name) {
		File gamefieldSpriteLinksFile = new File("resources/saveFiles/" + name + "/gamefieldLinks.txt");
		
		new DefaultSpriteRepository().loadAllSpritesFromDatabase();
		new GamefieldSpriteFiller(gamefieldSpriteLinksFile).fillGamefields();
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



	
	
//	private List<Trigger> getAllTriggerFromSprites(List<Sprite> sprites) {
//		List<Trigger> triggers = newArrayList();
//		for (Sprite sprite : sprites) {
//			triggers.addAll(sprite.getAllTriggers());
//		}
//		return triggers;
//	}
	
	/*
	 * For tests only
	 */
	protected void setFileUtility(FileUtility fileUtility) {
		this.fileUtility = fileUtility;
	}
}
