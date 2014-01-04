package be.davidcorp.loaderSaver.filehandling;

import static com.google.common.collect.Lists.newArrayList;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import be.davidcorp.FileUtility;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.loaderSaver.LoaderException;
import be.davidcorp.loaderSaver.mapper.stringSpriteMapper.StringToConstructionSpriteMapper;
import be.davidcorp.loaderSaver.mapper.stringSpriteMapper.StringToEnemyMapper;
import be.davidcorp.loaderSaver.mapper.stringSpriteMapper.StringToItemMapper;
import be.davidcorp.loaderSaver.mapper.stringSpriteMapper.StringToLightMapper;
import be.davidcorp.loaderSaver.mapper.stringSpriteMapper.StringToSpriteSpawnerMapper;
import be.davidcorp.loaderSaver.repository.ConstructionSpriteRepository;
import be.davidcorp.loaderSaver.repository.EnemyRepository;
import be.davidcorp.loaderSaver.repository.ItemRepository;
import be.davidcorp.loaderSaver.repository.LightRepository;
import be.davidcorp.loaderSaver.repository.MiscRepository;

public class SpriteFileLoader {

	private ArrayList<Enemy> enemies = newArrayList(); 
	private ArrayList<ConstructionSprite> constructionSprites = newArrayList();
	private ArrayList<Item> items = newArrayList();
	private ArrayList<Light> lights= newArrayList();
	private ArrayList<Sprite> miscs= newArrayList();
	
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
		new EnemyRepository().loadSprites(enemies);
		new ItemRepository().loadSprites(items);
		new LightRepository().loadSprites(lights);
		new MiscRepository().loadSprites(miscs);
		new ConstructionSpriteRepository().loadSprites(constructionSprites);
	}
	
	private void addSpriteStringToList(StringBuilder builder, String type) {
		if (type.equals(SpriteType.ENEMY.toString())) {
			enemies.add(new StringToEnemyMapper().mapSprite(builder.toString()));
		}
		if (type.equals(SpriteType.CONSTRUCTION.toString())) {
			constructionSprites.add(new StringToConstructionSpriteMapper().mapSprite(builder.toString()));
		}
		if (type.equals(SpriteType.ITEM.toString())) {
			items.add(new StringToItemMapper().mapSprite(builder.toString()));
		}
		if (type.equals(SpriteType.LIGHT.toString())) {
			lights.add(new StringToLightMapper().mapSprite(builder.toString()));
		}
		if (type.equals(SpriteType.MISC.toString())) {
			miscs.add(new StringToSpriteSpawnerMapper().mapSprite(builder.toString()));
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
