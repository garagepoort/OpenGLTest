package be.davidcorp.database.filehandling;

import static be.davidcorp.config.ConfigurationManager.Property.SAVEFILE_BASEDIR;
import static com.google.common.collect.Lists.newArrayList;
import static java.io.File.separator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import be.davidcorp.config.ConfigurationManager;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.repository.DefaultSpriteRepository;

import com.google.common.collect.Lists;

public class SpriteSerializer {

	private static final DefaultSpriteRepository SPRITE_REPOSITORY = DefaultSpriteRepository.getInstance();
	private static final String SPRITES_SAVE_FILE = "sprites.ser";

	private File file;

	private List<Sprite> sprites = newArrayList();

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
		SPRITE_REPOSITORY.loadSprites(sprites);
	}

	private void addSpriteToList(Sprite sprite) {
		sprites.add(sprite);
	}

	private String createSaveFilePath() {
		String gamefieldName = GameFieldManager.getCurrentGameField().getName();
		String baseDirectory = ConfigurationManager.getProperty(SAVEFILE_BASEDIR);
		
		return baseDirectory + separator + gamefieldName + separator + SPRITES_SAVE_FILE;
	}

}
