package be.davidcorp.loaderSaver.filehandling;

import static com.google.common.collect.Lists.newArrayList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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
import be.davidcorp.loaderSaver.repository.MiscRepository;

import com.google.common.collect.Lists;

public class SpriteSerializer {

	private File file;
	
	private ArrayList<Enemy> enemies = newArrayList(); 
	private ArrayList<ConstructionSprite> constructionSprites = newArrayList();
	private ArrayList<Item> items = newArrayList();
	private ArrayList<Light> lights= newArrayList();
	private ArrayList<Sprite> miscs= newArrayList();
	
	public SpriteSerializer(File file) {
		this.file = file;
	}

	public void serializeSprites(List<? extends Sprite> sprites) throws IOException {
		try {

			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(sprites);
			oos.close();
			System.out.println("sprites Saved");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Sprite> deserializeSprites() {
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
		for(Sprite sprite : deserializeSprites){
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

}
