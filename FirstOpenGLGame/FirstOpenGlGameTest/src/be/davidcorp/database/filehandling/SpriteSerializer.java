package be.davidcorp.database.filehandling;

import static com.google.common.collect.Lists.newArrayList;
import static java.io.File.separator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import be.davidcorp.config.ConfigurationManager;
import be.davidcorp.config.ConfigurationManager.Property;
import be.davidcorp.database.repository.ConstructionSpriteRepository;
import be.davidcorp.database.repository.EnemyRepository;
import be.davidcorp.database.repository.ItemRepository;
import be.davidcorp.database.repository.LightRepository;
import be.davidcorp.database.repository.MiscRepository;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;

import com.google.common.collect.Lists;

public class SpriteSerializer {

	private static final String SPRITES_SAVE_FILE = "sprites.ser";

	private File file;

	private ArrayList<Enemy> enemies = newArrayList();
	private ArrayList<ConstructionSprite> constructionSprites = newArrayList();
	private ArrayList<Item> items = newArrayList();
	private ArrayList<Light> lights = newArrayList();
	private ArrayList<Sprite> miscs = newArrayList();

	public SpriteSerializer() {
		this.file = new File(createSaveFilePath());
	}
	
	SpriteSerializer(File file) {
		this.file = file;
	}
	
	public void saveSprites(List<? extends Sprite> sprites) {
		try {

			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(sprites);
			oos.close();
			System.out.println("sprites Saved");

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@SuppressWarnings("unchecked")
	List<Sprite> deserializeSprites() {
		List<Sprite> sprites = Lists.newArrayList();
		try {
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fin);
			sprites = (List<Sprite>) ois.readObject();
			ois.close();
			return sprites;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sprites;
	}

	public void loadAllSprites() {
		List<Sprite> deserializeSprites = deserializeSprites();
		for (Sprite sprite : deserializeSprites) {
			addSpriteToList(sprite);
		}
		fillRepositories();
	}

	private void fillRepositories() {
		new EnemyRepository().loadSprites(enemies);
		new ItemRepository().loadSprites(items);
		new LightRepository().loadSprites(lights);
		new MiscRepository().loadSprites(miscs);
		new ConstructionSpriteRepository().loadSprites(constructionSprites);
	}

	private void addSpriteToList(Sprite sprite) {
		if (sprite instanceof Enemy) {
			enemies.add((Enemy) sprite);
		}
		if (sprite instanceof ConstructionSprite) {
			constructionSprites.add((ConstructionSprite) sprite);
		}
		if (sprite instanceof Item) {
			items.add((Item) sprite);
		}
		if (sprite instanceof Light) {
			lights.add((Light) sprite);
		}
		if (sprite.getType() == SpriteType.MISC) {
			miscs.add(sprite);
		}
	}

	private String createSaveFilePath() {
		String gamefieldName = GameFieldManager.getCurrentGameField().getName();
		return ConfigurationManager.getProperty(Property.SAVEFILE_BASEDIR) + separator + gamefieldName + separator + SPRITES_SAVE_FILE;
	}

}
