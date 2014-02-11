package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.light.Light;

public class LightTestBuilder extends SpriteBuilder<Light>{

	private int radius;
	private boolean lightOn;

	@Override
	protected Light createInstance() {
		return new Light();
	}

	@Override
	protected Light buildBasic() {
		Light light = super.buildBasic();
		light.setRadius(radius);
		light.setLightOn(lightOn);
		return light;
	}
	
	public LightTestBuilder withLightOn(boolean lightOn){
		this.lightOn = lightOn;
		return this;
	}
	
	public LightTestBuilder withRadius(int radius){
		this.radius = radius;
		return this;
	}
	

}
