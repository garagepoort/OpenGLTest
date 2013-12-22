package be.davidcorp.loaderSaver.repository;

import static be.davidcorp.domain.trigger.TriggerManager.createTrigger;
import static be.davidcorp.domain.trigger.TriggerWhen.ONLIGHTOFF;
import static be.davidcorp.domain.trigger.TriggerWhen.ONUSE;
import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.construction.Wall;
import be.davidcorp.domain.sprite.item.potion.HealthPotion;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Zombie;
import be.davidcorp.domain.trigger.triggerableEvents.LightSwitchEvent;
import be.davidcorp.loaderSaver.SpriteFileLoaderUtilities;
import be.davidcorp.loaderSaver.GamefieldProperty;
import be.davidcorp.loaderSaver.LoaderException;

public class GamefieldRepository {

	private static HashMap<Integer, Gamefield> gamefields = new HashMap<Integer, Gamefield>();

	private void addTestGamefield() {
		Gamefield gamefield = new Gamefield("field", 2000, 2000);
		gamefield.setID(6);

		Zombie zombie = new Zombie(200, 200);
		HealthPotion healthPotion = new HealthPotion(500, 500);
		Wall wall = new Wall(100, 100, 100, 100);
		Light light = new Light(200, 200, new Color(255, 0, 0), 200, true);
		Light secondLight = new Light(400, 400, new Color(0, 255, 0), 200, true);

		gamefield.addConstructionItem(wall);
		gamefield.addLight(light);
		gamefield.addLight(secondLight);
		gamefield.addEnemyToWorld(zombie);
		gamefield.addGroundItem(healthPotion);

		light.addTrigger(createTrigger(1, ONLIGHTOFF, secondLight, new LightSwitchEvent()));
		wall.addTrigger(createTrigger(2, ONUSE, light, new LightSwitchEvent()));

		gamefields.put(gamefield.getID(), gamefield);
	}

	public void loadGamefields(List<String> gamefieldStrings) {
		addTestGamefield();
		for (String gamefieldString : gamefieldStrings) {
			Gamefield gamefield = load(gamefieldString);
			gamefields.put(gamefield.getID(), gamefield);
		}
	}

	public Gamefield getGamefield(int id) {
		return gamefields.get(id);
	}

	public List<Gamefield> getAllGamefields() {
		List<Gamefield> results = new ArrayList<>();
		for (Entry<Integer, Gamefield> entry : gamefields.entrySet()) {
			results.add(entry.getValue());
		}
		return results;
	}

	public Gamefield getGamefield(String name) {
		for (Map.Entry<Integer, Gamefield> entry : gamefields.entrySet()) {
			if (entry.getValue().getName().equals(name)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public void createGamefield(String gamefieldName, int width, int height) {
		int id = IDGenerator.generateIdForGamefields(gamefields);
		Gamefield gamefield = new Gamefield(gamefieldName, width, height);
		gamefield.setID(id);
		gamefields.put(id, gamefield);
	}

	private Gamefield load(String gamefieldString) {
		try {
			Map<GamefieldProperty, String> values = SpriteFileLoaderUtilities.getGamefieldProperties(gamefieldString);

			String name = values.get(GamefieldProperty.GAMEFIELDNAME);
			int width = parseInt(values.get(GamefieldProperty.WIDTH));
			int height = parseInt(values.get(GamefieldProperty.HEIGHT));

			Gamefield gamefield = new Gamefield(name, width, height);
			gamefield.setID(parseInt(values.get(GamefieldProperty.ID)));
			gamefield.setName(name);

			return gamefield;
		} catch (Exception exception) {
			throw new LoaderException(exception);
		}
	}
}
