package be.davidcorp.applicationLayer.dto.light;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.davidcorp.applicationLayer.dto.SpriteDTO;

public class LightDTO extends SpriteDTO{

	private int radius = 50;
	private boolean lightOn = true;
	private ArrayList<LightTriangle> lightTriangles = new ArrayList<>();
	
	
	public void setLightOn(boolean lightOn) {
		this.lightOn = lightOn;
	}
	
	public boolean isLightOn() {
		return lightOn;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public int getRadius() {
		return radius;
	}
	

	public List<LightTriangle> getLightTriangles() {
		return Collections.unmodifiableList(lightTriangles);
	}

	public void addLightTriangle(LightTriangle lightTriangle) {
		lightTriangles.add(lightTriangle);
	}

	public void removeAllLightTriangles() {
		lightTriangles.clear();
	}

}
