package be.davidcorp.domain.ui;


public class GameStarter {

	public static void main(String[] args) {
		try {
			new Gameloop().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
