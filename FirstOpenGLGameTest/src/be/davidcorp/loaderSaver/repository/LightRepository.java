package be.davidcorp.loaderSaver.repository;

import static com.google.common.collect.Maps.newConcurrentMap;

import java.util.List;
import java.util.Map;

import be.davidcorp.domain.sprite.light.Light;

import com.google.common.collect.Lists;

public class LightRepository implements SpriteRepository<Light> {

	protected static Map<Integer, Light> lights = newConcurrentMap();

	public void saveLight(Light light) {
		throw new UnsupportedOperationException("No tyet implemented");
	}

	@Override
	public Light getSprite(int id) {
		return lights.get(id);
	}

	@Override
	public Light createSprite(Light light) {
		int id = IDGenerator.generateIdForSprites();
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
	
	@Override
	public List<Light> getAllSprites() {
		return Lists.newArrayList(lights.values());
	}
	
	@Override
	public void deleteSprite(int id) {
		lights.remove(id);
	}
}
