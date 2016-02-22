package be.davidcorp.database;

import static java.lang.Integer.parseInt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import be.davidcorp.database.filehandling.SpriteFileLoaderUtilities;
import be.davidcorp.database.filehandling.SpriteSerializer;
import be.davidcorp.database.filehandling.ZipFileWriter;
import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.repository.DefaultSpriteRepository;

public class GamefieldLoaderSaver {

	private static final String SAVEFILE_EXTENSION_ZIP = ".zip";
	private static final DefaultSpriteRepository defaultSpriteRepository = DefaultSpriteRepository
			.getInstance();

	public static Gamefield loadEntireField(ZipFile zipFile) {
		InputStream spritesFile = searchFile("sprites.ser", zipFile);
		InputStream gamefieldFile = searchFile("gamefield.txt", zipFile);

		Gamefield gamefield = loadGamefieldFromFile(gamefieldFile);
		defaultSpriteRepository.loadAllSpritesFromStream(spritesFile);
		return gamefield;
	}

	private static InputStream searchFile(String name, ZipFile zipFile) {
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			if (entry.getName().endsWith(name)) {
				try {
					return zipFile.getInputStream(entry);
				} catch (IOException e1) {
					throw new LoaderException(e1);
				}
			}
		}
		return null;
	}

	public static ZipFile saveCustomCreatedGamefieldToFile(Gamefield gamefield,
			String filePath) {
		try {
			File spritesFile = new File("sprites.ser");
			File gamefieldFile = saveGamefieldToFile(gamefield);
			SpriteSerializer.saveSpritesToFile(
					defaultSpriteRepository.getAllSprites(), spritesFile);

			String completePath = filePath
					+ File.separator + gamefield.getName()
					+ SAVEFILE_EXTENSION_ZIP;
			FileOutputStream fileOutputStream = new FileOutputStream(completePath);
			ZipOutputStream zipOutputStream = new ZipOutputStream(
					fileOutputStream);

			ZipFileWriter.addToZipFile(spritesFile, zipOutputStream);
			ZipFileWriter.addToZipFile(gamefieldFile, zipOutputStream);

			zipOutputStream.close();
			
			return new ZipFile(completePath);
		} catch (IOException e) {
			throw new LoaderException(e);
		}
	}

	private static Gamefield loadGamefieldFromFile(InputStream inputStream) {
		try {
			Map<GamefieldProperty, String> values = SpriteFileLoaderUtilities
					.getGamefieldProperties(inputStream);

			String name = values.get(GamefieldProperty.GAMEFIELDNAME);
			int width = parseInt(values.get(GamefieldProperty.WIDTH));
			int height = parseInt(values.get(GamefieldProperty.HEIGHT));

			Gamefield gamefield = new Gamefield(name, width, height);

			return gamefield;
		} catch (Exception exception) {
			throw new LoaderException(exception);
		}
	}

	private static File saveGamefieldToFile(Gamefield gamefield) {
		try {
			File file = new File("gamefield.txt");
			PrintWriter writer = new PrintWriter(file);

			writer.write(GamefieldProperty.GAMEFIELDNAME + ":"
					+ gamefield.getName() + "\n");
			writer.write(GamefieldProperty.WIDTH + ":" + gamefield.getWidth()
					+ "\n");
			writer.write(GamefieldProperty.HEIGHT + ":" + gamefield.getHeight());

			writer.close();
			return file;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
