package be.davidcorp.loaderSaver.filehandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.loaderSaver.mapper.spriteToString.ConstructionSpriteToStringMapper;
import be.davidcorp.loaderSaver.mapper.spriteToString.EnemyToStringMapper;
import be.davidcorp.loaderSaver.mapper.spriteToString.ItemToStringMapper;
import be.davidcorp.loaderSaver.mapper.spriteToString.LightToStringMapper;
import be.davidcorp.loaderSaver.mapper.spriteToString.MapperException;
import be.davidcorp.loaderSaver.mapper.spriteToString.SpriteToStringMapper;

public class SpriteFileWriter {

	private File file;

	public SpriteFileWriter(File file) {
		this.file = file;
	}

	@SuppressWarnings("unchecked")
	public void saveSprites(List<Sprite> sprites) {
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(file);
			for (Sprite sprite : sprites) {
				String spriteString = getMapperForSprite(sprite).buildStringFromSprite(sprite);
				printWriter.println(spriteString);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null)
				printWriter.close();
		}
	}

	@SuppressWarnings("rawtypes")
	private SpriteToStringMapper getMapperForSprite(Sprite sprite) {
		if (sprite instanceof Enemy) {
			return new EnemyToStringMapper();
		}
		if (sprite instanceof Light) {
			return new LightToStringMapper();
		}
		if (sprite instanceof Item) {
			return new ItemToStringMapper();
		}
		if (sprite instanceof ConstructionSprite) {
			return new ConstructionSpriteToStringMapper();
		}
		throw new MapperException("No mapper for sprite: " + sprite);
	}
}
