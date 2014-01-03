package be.davidcorp.domain.sprite.construction;

import be.davidcorp.domain.sprite.Sprite;

public class ConstructionSpriteFactory {

	public static void createWall(float x, float y, int width, int height){
		ConstructionSprite constructionSprite = new ConstructionSprite() {
			
			@Override
			protected void onUse(Sprite sprite) {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
