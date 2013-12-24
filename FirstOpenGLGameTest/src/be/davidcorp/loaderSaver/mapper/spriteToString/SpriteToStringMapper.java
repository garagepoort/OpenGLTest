package be.davidcorp.loaderSaver.mapper.spriteToString;

import static be.davidcorp.loaderSaver.SpriteProperty.HEIGHT;
import static be.davidcorp.loaderSaver.SpriteProperty.WIDTH;
import static be.davidcorp.loaderSaver.SpriteProperty.X;
import static be.davidcorp.loaderSaver.SpriteProperty.Y;
import static java.lang.System.lineSeparator;

import java.util.HashMap;
import java.util.Map;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.loaderSaver.SpriteProperty;

public abstract class SpriteToStringMapper<SPRITE extends Sprite> {
	
	protected SPRITE sprite;
	protected Map<SpriteProperty, String> properties = new HashMap<SpriteProperty, String>();
	
	public String buildStringFromSprite(SPRITE sprite){
		this.sprite = sprite;
		addProperties();
		
		StringBuilder builder = new StringBuilder();
		builder.append(getSpriteClass() + lineSeparator());
		builder.append("SPRITETYPE:" + getSpriteType() + lineSeparator());
		
		for (Map.Entry<SpriteProperty, String> entry : properties.entrySet()) {
			builder.append(entry.getKey() + ":" + entry.getValue() + lineSeparator());
		}
		
		return builder.toString();
	}
	
	public void addProperties(){
		properties.put(X, valueToString(sprite.getX()));
		properties.put(Y, valueToString(sprite.getY()));
		properties.put(WIDTH, valueToString(sprite.getWidth()));
		properties.put(HEIGHT, valueToString(sprite.getHeight()));
	}

	public abstract String getSpriteClass();
	
	public abstract String getSpriteType();
	
	protected String valueToString(int value){
		return Integer.toString(value);
	}
	
	protected String valueToString(float value){
		return Float.toString(value);
	}
	
	protected String valueToString(boolean value){
		return Boolean.toString(value);
	}
}
