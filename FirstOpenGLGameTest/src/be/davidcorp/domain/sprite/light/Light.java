package be.davidcorp.domain.sprite.light;

import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.trigger.TriggerWhen;

public class Light extends Sprite{

	//public Color color = new Color(255, 246, 133);
	private int radius = 50;
	private boolean lightOn = true;


	public Light(float x, float y, Color color, int radius, boolean lightOn){
		super(x, y, 10, 10);
		setLightOn(lightOn);
		setColor(color);
		this.radius=radius;
	}


	public boolean isLightOn() {
		return lightOn;
	}


	public void setLightOn(boolean lightOn) {
		this.lightOn = lightOn;
		if(lightOn){
			checkTriggers(TriggerWhen.ONLIGHTON, null);
		}else{
			checkTriggers(TriggerWhen.ONLIGHTOFF, null);
		}
	}


	public int getRadius() {
		return radius;
	}

	public String toString(){
		return "Light"; 
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
