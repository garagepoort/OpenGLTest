package be.davidcorp.domain.game;

import java.util.ArrayList;

import be.davidcorp.domain.sprite.GuideArea;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.utilities.sprite.SpriteCollisionChecker;



public class Guide {

	private ArrayList<GuideArea> areas = new ArrayList<>();
	private String currentGuidance;
	private GuideArea area;
	private String filePath;
	private boolean guidanceOn = false;
	
	public void checkCollisionWithGuideArea(Sprite sprite, float secondsMovedInGame){
		for(GuideArea area : areas){
			if(area.isAlive() && area!=this.area){
				if(SpriteCollisionChecker.doesCollisionExist(sprite, area)){
					setCurrentGuidance(area.getGuidance());

					if(this.area!=null){
						this.area.kill();
					}
					this.area=area;
				}
			}
		}

		guidanceOn=false;
		if(area!=null && area.isAlive()){
			area.updateSprite(secondsMovedInGame);
			setCurrentGuidance(area.getGuidance());
			guidanceOn=true;
		}
	}

	public boolean isGuidanceOn(){
		return guidanceOn;
	}

	public GuideArea getArea() {
		return area;
	}

	public void setArea(GuideArea area) {
		this.area = area;
	}

	public String getCurrentGuidance() {
		return currentGuidance;
	}

	public void setCurrentGuidance(String currentGuidance) {
		this.currentGuidance = currentGuidance;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


}
