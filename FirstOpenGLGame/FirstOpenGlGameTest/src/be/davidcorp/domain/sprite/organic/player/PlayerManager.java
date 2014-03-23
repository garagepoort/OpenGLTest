package be.davidcorp.domain.sprite.organic.player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class PlayerManager {

	private static HashMap<String, Player> players = new HashMap<>();
	private static Player currentPlayer;

	public static void loadPlayers(File file) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);

			while (scanner.hasNext()) {
				String path = scanner.next();
				File f = new File(path);
				Player p = loadPlayer(f);
				players.put(p.getName(), p);
				setCurrentPlayer(p);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			scanner.close();
		}
	}

	public static Player loadPlayer(File file) throws IOException {
		return new Player(200, 250);
	}

	public static HashMap<String, Player> getPlayers() {
		return players;
	}

	public static void setPlayers(HashMap<String, Player> players) {
		PlayerManager.players = players;
	}

	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	public static void setCurrentPlayer(Player currentPlayer) {
		PlayerManager.currentPlayer = currentPlayer;
	}

}
