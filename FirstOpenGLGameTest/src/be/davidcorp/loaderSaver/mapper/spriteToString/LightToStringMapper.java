package be.davidcorp.loaderSaver.mapper.spriteToString;

import static be.davidcorp.loaderSaver.SpriteProperty.LIGHTON;
import static be.davidcorp.loaderSaver.SpriteProperty.RADIUS;
import be.davidcorp.domain.sprite.light.Light;

public class LightToStringMapper extends SpriteToStringMapper<Light> {

	
	@Override
	public void addProperties() {
		super.addProperties();
		properties.put(LIGHTON, valueToString(sprite.isLightOn()));
		properties.put(RADIUS, valueToString(sprite.getRadius()));
	}

	@Override
	public String getSpriteClass() {
		return "LIGHT";
	}

	@Override
	public String getSpriteType() {
		return "LIGHT";
	}

}
