package be.davidcorp.database;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import be.davidcorp.FileUtility;
import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.repository.DefaultSpriteRepository;
import be.davidcorp.repository.GamefieldRepository;

public class GamefieldSpriteFiller {

	private static final String SPRITE = "SPRITE";
	private static final String GAMEFIELD = "GAMEFIELD";
	private Gamefield currentGamefield;
	private GamefieldRepository gamefieldRepository = new GamefieldRepository();
	private DefaultSpriteRepository defaultSpriteRepository = DefaultSpriteRepository.getInstance();
	private File file;
	private FileUtility fileUtility;

	public GamefieldSpriteFiller(File file) {
		this.file = file;
		fileUtility = new FileUtility();
	}

	public void fillGamefields() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(fileUtility.getFileContent(file));
			while (scanner.hasNext()) {
				Scanner lineScanner = new Scanner(scanner.nextLine());
				lineScanner.useDelimiter(":");

				String key = lineScanner.next();
				int id = Integer.parseInt(lineScanner.next());

				if (key.equals(GAMEFIELD)) {
					currentGamefield = gamefieldRepository.getGamefield(id);
				} else if (key.equals(SPRITE)) {
					addSpriteToCurrentGamefield(id);
				} else {
					lineScanner.close();
					throw new LoaderException("Key: [" + key + "] does not exist");
				}
				lineScanner.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
			if(scanner != null) scanner.close();
		}
	}

	private void addSpriteToCurrentGamefield(int id) {
		Sprite sprite = defaultSpriteRepository.getSprite(id);
		if (sprite instanceof ConstructionSprite)
			currentGamefield.addConstructionItem((ConstructionSprite) sprite);
		if (sprite instanceof Enemy)
			currentGamefield.addEnemyToWorld((Enemy) sprite);
		if (sprite instanceof Item)
			currentGamefield.addGroundItem((Item) sprite);
		if (sprite instanceof Light)
			currentGamefield.addLight((Light) sprite);
	}

	/*
	 * for testing purposes only
	 */
	public void setDefaultSpriteRepository(DefaultSpriteRepository defaultSpriteRepository) {
		this.defaultSpriteRepository = defaultSpriteRepository;
	}

	public void setGamefieldRepository(GamefieldRepository gamefieldRepository) {
		this.gamefieldRepository = gamefieldRepository;
	}

	public void setFileUtility(FileUtility fileUtility) {
		this.fileUtility = fileUtility;
	}
}
