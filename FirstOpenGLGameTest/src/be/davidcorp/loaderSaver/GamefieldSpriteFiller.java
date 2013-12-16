package be.davidcorp.loaderSaver;

import java.util.Scanner;

import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.loaderSaver.repository.DefaultSpriteRepository;
import be.davidcorp.loaderSaver.repository.GamefieldRepository;

public class GamefieldSpriteFiller {

	private static final String SPRITE = "SPRITE";
	private static final String GAMEFIELD = "GAMEFIELD";
	private Gamefield currentGamefield;
	private GamefieldRepository gamefieldRepository = new GamefieldRepository();
	private DefaultSpriteRepository defaultSpriteRepository = new DefaultSpriteRepository();

	public void fillGamefields(String gamefieldSpriteLinks) throws LoaderException {
		Scanner scanner = new Scanner(gamefieldSpriteLinks);
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
		scanner.close();
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
}
