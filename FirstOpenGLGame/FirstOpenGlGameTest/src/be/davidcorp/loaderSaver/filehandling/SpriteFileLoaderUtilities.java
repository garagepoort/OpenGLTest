package be.davidcorp.loaderSaver.filehandling;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import be.davidcorp.loaderSaver.GamefieldProperty;

public class SpriteFileLoaderUtilities {

	public static Map<GamefieldProperty, String> getGamefieldProperties(String gamefield) {
		Map<GamefieldProperty, String> properties = new HashMap<GamefieldProperty, String>();
		Scanner scanner = new Scanner(gamefield);
		while (scanner.hasNextLine()) {
			Scanner lineScanner = new Scanner(scanner.nextLine());
			lineScanner.useDelimiter(":");
			properties.put(GamefieldProperty.valueOf(lineScanner.next()), lineScanner.next());
			lineScanner.close();
		}
		scanner.close();
		return properties;
	}


	public static void addGamefieldPropertiesToPrintWriter(Map<GamefieldProperty, String> properties, PrintWriter printWriter) {
		for (Map.Entry<GamefieldProperty, String> entry : properties.entrySet()) {
			printWriter.println(entry.getKey() + ":" + entry.getValue());
		}
	}

}
