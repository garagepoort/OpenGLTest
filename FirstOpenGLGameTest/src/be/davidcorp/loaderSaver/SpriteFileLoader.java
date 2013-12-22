package be.davidcorp.loaderSaver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import be.davidcorp.FileUtility;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.loaderSaver.repository.ConstructionSpriteRepository;
import be.davidcorp.loaderSaver.repository.EnemyRepository;
import be.davidcorp.loaderSaver.repository.ItemRepository;
import be.davidcorp.loaderSaver.repository.LightRepository;
import be.davidcorp.loaderSaver.repository.SpriteRepository;
import be.davidcorp.loaderSaver.stringSpriteMapper.StringToConstructionSpriteMapper;
import be.davidcorp.loaderSaver.stringSpriteMapper.StringToEnemyMapper;
import be.davidcorp.loaderSaver.stringSpriteMapper.StringToItemMapper;
import be.davidcorp.loaderSaver.stringSpriteMapper.StringToLightMapper;
import be.davidcorp.loaderSaver.stringSpriteMapper.StringToSpriteMapper;

public class SpriteFileLoader {

	private ArrayList<String> enemiesStrings = new ArrayList<String>(); 
	private ArrayList<String> constructionSpritesStrings = new ArrayList<String>();
	private ArrayList<String> itemsStrings = new ArrayList<String>();
	private ArrayList<String> lightsStrings = new ArrayList<String>();

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>(); 
	private ArrayList<ConstructionSprite> constructionSprites = new ArrayList<ConstructionSprite>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Light> lights= new ArrayList<Light>();
	
	private File file;
	private FileUtility fileUtility;

	public SpriteFileLoader(File file) {
		this.file = file;
		fileUtility = new FileUtility();
	}

	public void loadAllSprites() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(fileUtility.getFileContent(file));
			while (scanner.hasNextLine()) {
				StringBuilder builder = new StringBuilder();
				String spriteType = createSpriteString(scanner, builder);
				addSpriteStringToList(builder, spriteType);
			}
			fillRepositories();

		} catch (Exception e) {
			throw new LoaderException(e);
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}

	private void fillRepositories() {
		mapSpriteAndAddToRepository(enemiesStrings, enemies, new EnemyRepository(), new StringToEnemyMapper());
		mapSpriteAndAddToRepository(itemsStrings, items, new ItemRepository(), new StringToItemMapper());
		mapSpriteAndAddToRepository(lightsStrings, lights, new LightRepository(), new StringToLightMapper());
		mapSpriteAndAddToRepository(constructionSpritesStrings, constructionSprites, new ConstructionSpriteRepository(), new StringToConstructionSpriteMapper());
	}
	
	private <SPRITE extends Sprite> void mapSpriteAndAddToRepository(List<String> spriteStrings, List<SPRITE> sprites, SpriteRepository<SPRITE> repository, StringToSpriteMapper<SPRITE> mapper){
		for (String spriteString : spriteStrings) {
			sprites.add(mapper.loadSprite(spriteString));
		}
		repository.loadSprites(sprites);
	}

	private void addSpriteStringToList(StringBuilder builder, String type) {
		if (type.equals(SpriteType.ENEMY.toString())) {
			enemiesStrings.add(builder.toString());
		}
		if (type.equals(SpriteType.CONSTRUCTION.toString())) {
			constructionSpritesStrings.add(builder.toString());
		}
		if (type.equals(SpriteType.ITEM.toString())) {
			itemsStrings.add(builder.toString());
		}
		if (type.equals(SpriteType.LIGHT.toString())) {
			lightsStrings.add(builder.toString());
		}
	}

	private String createSpriteString(Scanner scanner, StringBuilder builder) {
		String type = scanner.nextLine();
		while (scanner.hasNextLine()) {
			String nextline = scanner.nextLine();
			if (nextline.contains("END"))
				break;
			builder.append(nextline + "\n");
		}
		builder.setLength(builder.length() - 1);
		return type;
	}

	/*
	 * Only for test
	 */
	public void setFileUtility(FileUtility fileUtility) {
		this.fileUtility = fileUtility;
	}
}
