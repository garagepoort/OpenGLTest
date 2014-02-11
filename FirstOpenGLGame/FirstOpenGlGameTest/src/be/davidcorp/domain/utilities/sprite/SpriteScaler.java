package be.davidcorp.domain.utilities.sprite;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.metric.transfo.ScaleTransfo;

public class SpriteScaler {

	public SpriteScaler() {
		// TODO Auto-generated constructor stub
	}

	public static void scaleSprite(Sprite sprite, float sx, float sy, float sz) {
		ScaleTransfo str = new ScaleTransfo(sx, sy, sz);
		sprite.getHitBox().setDownLeftPoint(str.mat.mult(sprite.getHitBox().getDownLeftPoint()));
		sprite.getHitBox().setDownRightPoint(str.mat.mult(sprite.getHitBox().getDownRightPoint()));
		sprite.getHitBox().setUpperRightPoint(str.mat.mult(sprite.getHitBox().getUpperRightPoint()));
		sprite.getHitBox().setUpperLeftPoint(str.mat.mult(sprite.getHitBox().getUpperLeftPoint()));
	}
}
