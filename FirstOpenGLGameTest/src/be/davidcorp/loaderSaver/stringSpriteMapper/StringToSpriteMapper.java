package be.davidcorp.loaderSaver.stringSpriteMapper;

import static be.davidcorp.loaderSaver.filehandling.SpriteFileLoaderUtilities.addSpritePropertiesToPrintWriter;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.construction.Wall;
import be.davidcorp.domain.sprite.item.potion.HealthPotion;
import be.davidcorp.domain.sprite.item.potion.StaminaPotion;
import be.davidcorp.domain.sprite.item.weapon.Pistol;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Spider;
import be.davidcorp.domain.sprite.organic.enemy.Zombie;
import be.davidcorp.loaderSaver.LoaderException;
import be.davidcorp.loaderSaver.SpriteProperty;
import be.davidcorp.loaderSaver.filehandling.SpriteFileLoaderUtilities;

public abstract class StringToSpriteMapper<T extends Sprite> {

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

	// @SuppressWarnings("unchecked")
	// public T loadSprite(String spriteString, SpriteLoaderEvent<T>
	// spriteLoaderEvent) {
	// try {
	// Map<SpriteProperty, String> properties =
	// getSpriteProperties(spriteString);
	// Sprite sprite = spriteLoaderEvent.createSprite(properties);
	// sprite.setID(parseInt(properties.get(SpriteProperty.ID)));
	// return (T) sprite;
	// } catch (Exception exception) {
	// throw new LoaderException(exception);
	// }
	// }

	@SuppressWarnings("unchecked")
	public T loadSprite(String spriteString) {
		Map<SpriteProperty, String> properties = SpriteFileLoaderUtilities.getSpriteProperties(spriteString);
		Sprite sprite = SpriteFactory.createSprite(SpriteType.valueOf(properties.get(SpriteProperty.SPRITETYPE)));
		mapStringToSprite(properties, sprite);
		doExtraMapping((T) sprite, properties);
		return (T) sprite;
	}

	private void mapStringToSprite(Map<SpriteProperty, String> spriteProperties, Sprite sprite) {
		if(spriteProperties.containsKey(SpriteProperty.ID)) sprite.setID(parseInt(spriteProperties.get(SpriteProperty.ID)));
		if(spriteProperties.containsKey(SpriteProperty.X)) sprite.setX(parseFloat(spriteProperties.get(SpriteProperty.X)));
		if(spriteProperties.containsKey(SpriteProperty.Y)) sprite.setY(parseFloat(spriteProperties.get(SpriteProperty.Y)));
		if(spriteProperties.containsKey(SpriteProperty.WIDTH)) sprite.setWidth(parseInt(spriteProperties.get(SpriteProperty.WIDTH)));
		if(spriteProperties.containsKey(SpriteProperty.HEIGHT)) sprite.setHeight(parseInt(spriteProperties.get(SpriteProperty.HEIGHT)));
	}

	protected abstract void doExtraMapping(T sprite, Map<SpriteProperty, String> properties);

	private static class SpriteFactory {
		public static Sprite createSprite(SpriteType type) {
			// TODO david haal IO-exception weg
				switch (type) {
					case HEALTHPOTION :
						return new HealthPotion();
					case STAMINAPOTION :
						return new StaminaPotion();
					case ZOMBIE :
						return new Zombie();
					case SPIDER :
						return new Spider();
					case WALL :
						return new Wall();
					case LIGHT :
						return new Light();
					case PISTOL :
						return new Pistol();
					default :
						throw new RuntimeException("No sprite found for this type");
				}
		}
	}

}
