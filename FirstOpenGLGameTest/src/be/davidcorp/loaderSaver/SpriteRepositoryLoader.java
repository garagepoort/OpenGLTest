package be.davidcorp.loaderSaver;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import be.davidcorp.FileUtility;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.loaderSaver.repository.ConstructionSpriteRepository;
import be.davidcorp.loaderSaver.repository.EnemyRepository;
import be.davidcorp.loaderSaver.repository.ItemRepository;
import be.davidcorp.loaderSaver.repository.LightRepository;
import be.davidcorp.loaderSaver.repository.WeaponRepository;

public class SpriteRepositoryLoader {

	private ArrayList<String> enemies = new ArrayList<String>(); 
	private ArrayList<String> weapons = new ArrayList<String>();
	private ArrayList<String> constructionSprites = new ArrayList<String>();
	private ArrayList<String> items = new ArrayList<String>();
	private ArrayList<String> lights = new ArrayList<String>();
	
	private File file;
	private FileUtility fileUtility;

	public SpriteRepositoryLoader(File file) {
		this.file = file;
		fileUtility = new FileUtility();
	}

	public void loadAllSprites(String type) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(fileUtility.getFileContent(file));
			while (scanner.hasNextLine()) {
				StringBuilder builder = new StringBuilder();
				addSpriteStringToList(builder, createSpriteString(scanner, builder));
			}
			fillRepositories(type);

		} catch (Exception e) {
			throw new LoaderException(e);
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}

	private void fillRepositories(String type) {
		new EnemyRepository().loadSprites(type, enemies);
		new WeaponRepository().loadSprites(type, weapons);
		new ConstructionSpriteRepository().loadSprites(type, constructionSprites);
		new ItemRepository().loadSprites(type, items);
		new LightRepository().loadSprites(type, lights);
	}

	private void addSpriteStringToList(StringBuilder builder, String type) {
		if (type.equals(SpriteType.ENEMY.toString())) {
			enemies.add(builder.toString());
		}
		if (type.equals(SpriteType.WEAPON.toString())) {
			weapons.add(builder.toString());
		}
		if (type.equals(SpriteType.CONSTRUCTION.toString())) {
			constructionSprites.add(builder.toString());
		}
		if (type.equals(SpriteType.ITEM.toString())) {
			items.add(builder.toString());
		}
		if (type.equals(SpriteType.LIGHT.toString())) {
			lights.add(builder.toString());
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
