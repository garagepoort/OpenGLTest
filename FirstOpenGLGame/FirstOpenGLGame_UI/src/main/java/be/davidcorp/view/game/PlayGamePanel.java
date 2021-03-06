package main.java.be.davidcorp.view.game;

import java.io.IOException;

import main.java.be.davidcorp.inputControl.GamePanelInputController;
import main.java.be.davidcorp.view.drawer.PlayerDrawer;

public class PlayGamePanel extends GamePanel {


	public PlayGamePanel() throws IOException {
		super();
		setInputController(new GamePanelInputController(this));
	}

	@Override
	public void render() throws IOException {
		super.render();
		PlayerDrawer.drawHealth();
		PlayerDrawer.drawStamina();
	}

}
