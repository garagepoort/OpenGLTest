package be.davidcorp.loaderSaver.mapper.stringSpriteMapper;

import static be.davidcorp.loaderSaver.SpriteProperty.LIGHTON;
import static java.lang.Boolean.parseBoolean;

import java.util.Map;

import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.loaderSaver.SpriteProperty;

public class StringToLightMapper extends StringToSpriteMapper<Light>{

	@Override
	protected void doExtraMapping(Light sprite, Map<SpriteProperty, String> properties) {
		sprite.setLightOn(parseBoolean(properties.get(LIGHTON)));
		sprite.setRadius(Integer.parseInt(properties.get(SpriteProperty.RADIUS)));
		setLightColor(sprite, properties);
	}

	private void setLightColor(Light sprite, Map<SpriteProperty, String> properties) {
		int red = Integer.parseInt(properties.get(SpriteProperty.RED));
		int green = Integer.parseInt(properties.get(SpriteProperty.GREEN));
		int blue = Integer.parseInt(properties.get(SpriteProperty.BLUE));
		Color color = new Color(red, green, blue);
		sprite.setColor(color);
	}

}
