package be.davidcorp.database.filehandling;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import be.davidcorp.database.GamefieldProperty;

public class SpriteFileLoaderUtilities {

	private SpriteFileLoaderUtilities() {
	}

	public static Map<GamefieldProperty, String> getGamefieldProperties(
			InputStream inputstream) {
			Map<GamefieldProperty, String> properties = new HashMap<GamefieldProperty, String>();
			Scanner scanner = new Scanner(inputstream);
			while (scanner.hasNextLine()) {
				Scanner lineScanner = new Scanner(scanner.nextLine());
				lineScanner.useDelimiter(":");
				properties.put(GamefieldProperty.valueOf(lineScanner.next()),
						lineScanner.next());
				lineScanner.close();
			}
			scanner.close();
			return properties;
	}

}
