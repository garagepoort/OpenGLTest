package be.davidcorp.view.game;


import static be.davidcorp.view.game.GameStarter.HEIGHT;
import static be.davidcorp.view.game.GameStarter.WIDTH;

import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.engine.Color;
import be.davidcorp.engine.GameDrawer;
import be.davidcorp.engine.ModelDrawer;
import be.davidcorp.metric.Point;
import be.davidcorp.view.TranslationManager;

public class GameDrawerImpl implements GameDrawer {

	private GameFieldFacade gameFieldFacade = GameFieldFacade.getInstance();
	private static PlayerFacade playerFacade = new PlayerFacade();

	@Override
	public void draw(ModelDrawer modelDrawer) {
		float x = WIDTH / 2 + TranslationManager.getPlayerTranslationX();
		float y = HEIGHT / 2 + TranslationManager.getPlayerTranslationY();

		modelDrawer.drawRectangle(new Point(x, y), 10, 10, new Color(0f, 1f, 0f));
	}
}
