package be.davidcorp.loaderSaver.repository;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.loaderSaver.LoaderException;
import be.davidcorp.loaderSaver.SpriteLoaderSaver;
import be.davidcorp.loaderSaver.SpriteLoaderSaver.SpriteLoaderEvent;
import be.davidcorp.loaderSaver.SpriteProperty;

public class LightRepository implements SpriteRepository<Light> {

	private static HashMap<Integer, Light> lights = new HashMap<Integer, Light>();

	public void saveLight(Light light) {
		throw new UnsupportedOperationException("No tyet implemented");
	}

	@Override
	public void loadSprites(String type, ArrayList<String> spriteStrings) throws LoaderException {
		for (String lightString : spriteStrings) {
			Light light = new SpriteLoaderSaver<Light>().loadSprite(lightString, new LightLoaderEvent());
			lights.put(light.getID(), light);
			if(type.equals("gamefield")){
				GameFieldManager.getCurrentGameField().addLight(light);
			}
		}
	}

	@Override
	public Light getSprite(int id) {
		return lights.get(id);
	}

	@Override
	public Light createSprite(Light light) throws SpriteRepositoryException {
		int id = IDGenerator.generateIdForSprites(lights);
		light.setID(id);
		lights.put(id, light);
		return light;
	}

	private static class LightLoaderEvent extends SpriteLoaderEvent<Light> {

		@Override
		public Light createSprite(Map<SpriteProperty, String> values) throws LoaderException {
			try {
				boolean lightOn = parseBoolean(values.get(SpriteProperty.LIGHTON));
				float x = parseFloat(values.get(SpriteProperty.X));
				float y = parseFloat(values.get(SpriteProperty.Y));
				int radius = parseInt(values.get(SpriteProperty.RADIUS));
				int r = parseInt(values.get(SpriteProperty.RED));
				int g = parseInt(values.get(SpriteProperty.GREEN));
				int b = parseInt(values.get(SpriteProperty.BLUE));
				
				return new Light(x, y, new Color(r, g, b), radius, lightOn);
			} catch (Exception e) {
				throw new LoaderException(e);
			}
		}
	}

	@Override
	public void updateSprite(Light spriteToUpdate) throws SpriteException {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void emptyRepository(){
		lights.clear();
	}
}
