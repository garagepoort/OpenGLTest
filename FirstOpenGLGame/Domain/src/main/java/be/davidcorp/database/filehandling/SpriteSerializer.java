package be.davidcorp.database.filehandling;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import be.davidcorp.domain.sprite.Sprite;

import com.google.common.collect.Lists;

public class SpriteSerializer {


	private SpriteSerializer() {
	}
	
	public static List<Sprite> loadAllSpritesFromFile(InputStream fileInputStream) {
		List<Sprite> deserializeSprites = deserializeSprites(fileInputStream);
		return deserializeSprites;
	}
	
	public static void saveSpritesToFile(List<? extends Sprite> sprites, File file) {
		try {
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(sprites);
			oos.close();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@SuppressWarnings("unchecked")
	static List<Sprite> deserializeSprites(InputStream fileInputStream) {
		List<Sprite> sprites = Lists.newArrayList();
		try {
			ObjectInputStream ois = new ObjectInputStream(fileInputStream);
			sprites = (List<Sprite>) ois.readObject();
			ois.close();
			return sprites;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sprites;
	}

}
