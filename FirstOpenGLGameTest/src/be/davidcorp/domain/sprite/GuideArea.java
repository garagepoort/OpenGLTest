package be.davidcorp.domain.sprite;

import java.util.HashMap;

import be.davidcorp.domain.exception.SpriteException;


public class GuideArea extends Sprite{

	private HashMap<Integer, String> guidances = new HashMap<Integer, String>();
	private int counter;
	private String guidance;
	public GuideArea(float x, float y, int width, int height) throws SpriteException {
		super(x, y, width, height);

	}

	public void updateSprite(int secondsMovedInGame){
		super.updateSprite(secondsMovedInGame);
		counter++;
		if(guidances.get(counter) != null){
			guidance = guidances.get(counter);
		}
	}

	public HashMap<Integer, String> getGuidances() {
		return guidances;
	}

	public void setGuidances(HashMap<Integer, String> guidances) {
		this.guidances = guidances;
	}

	public String getGuidance() {
		return guidance;
	}

	public void setGuidance(String guidance) {
		this.guidance = guidance;
	}

	@Override
	public void onDeath() {
	}

}
