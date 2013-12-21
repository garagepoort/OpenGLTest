package be.davidcorp.loaderSaver;

import static be.davidcorp.loaderSaver.FileLoaderUtilities.addSpritePropertiesToPrintWriter;
import static be.davidcorp.loaderSaver.FileLoaderUtilities.getSpriteProperties;
import static java.lang.Integer.parseInt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;

import be.davidcorp.domain.sprite.Sprite;

public class SpriteLoaderSaver<T extends Sprite> {

	public void saveSprite(File file, T sprite, Map<SpriteProperty, String> properties) {
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			addSpritePropertiesToPrintWriter(properties, printWriter);
		} catch (Exception exception) {
			throw new LoaderException(exception);
		} finally {
			if (printWriter != null)
				printWriter.close();
		}
	}

	@SuppressWarnings("unchecked")
	public T loadSprite(String spriteString, SpriteLoaderEvent<T> spriteLoaderEvent) {
		try {
			Map<SpriteProperty, String> properties = getSpriteProperties(spriteString);
			Sprite sprite = spriteLoaderEvent.createSprite(properties);
			sprite.setID(parseInt(properties.get(SpriteProperty.ID)));
			return (T) sprite;
		} catch (Exception exception) {
			throw new LoaderException(exception);
		}
	}

	public static abstract class SpriteLoaderEvent<T>{
		public abstract T createSprite(Map<SpriteProperty, String> values);
	}
}
