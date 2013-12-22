package be.davidcorp.loaderSaver.repository;

import java.util.HashMap;
import java.util.List;

import be.davidcorp.domain.sprite.light.Light;

public class LightRepository implements SpriteRepository<Light> {

	private static HashMap<Integer, Light> lights = new HashMap<Integer, Light>();

	public void saveLight(Light light) {
		throw new UnsupportedOperationException("No tyet implemented");
	}

	@Override
	public Light getSprite(int id) {
		return lights.get(id);
	}

	@Override
	public Light createSprite(Light light) {
		int id = IDGenerator.generateIdForSprites(lights);
		light.setID(id);
		lights.put(id, light);
		return light;
	}

	@Override
	public void updateSprite(Light spriteToUpdate) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void emptyRepository(){
		lights.clear();
	}

	@Override
	public void loadSprites(List<Light> sprites) {
		for (Light light : sprites) {
			lights.put(light.getID(), light);
		}
	}
}
