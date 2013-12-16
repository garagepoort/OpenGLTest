package be.davidcorp.domain.utilities.sprite;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.metric.Point;

public class SpriteCollisionChecker {

	public static boolean doesCollisionExist(Sprite sprite1, Sprite sprite2){
		if (doesSpriteCollideWithPoint(sprite1, sprite2.getHitBox().getDownLeftPoint()) || doesSpriteCollideWithPoint(sprite1, sprite2.getHitBox().getDownRightPoint())
				|| doesSpriteCollideWithPoint(sprite1, sprite2.getHitBox().getUpperRightPoint()) || doesSpriteCollideWithPoint(sprite1, sprite2.getHitBox().getUpperLeftPoint())) {
			return true;
		}
		if (doesSpriteCollideWithPoint(sprite2, sprite1.getHitBox().getDownLeftPoint()) || doesSpriteCollideWithPoint(sprite2, sprite1.getHitBox().getDownRightPoint())
				|| doesSpriteCollideWithPoint(sprite2, sprite1.getHitBox().getUpperRightPoint()) || doesSpriteCollideWithPoint(sprite2, sprite1.getHitBox().getUpperLeftPoint())) {
			return true;
		}
		return false;
	}
	
	public static boolean doesSpriteCollideWithPoint(Sprite sprite, Point point) {

		if (!isPointOnLeftSideOfLine(sprite.getHitBox().getDownLeftPoint(), sprite.getHitBox().getDownRightPoint(), point)) {
			return false;
		}
		if (!isPointOnLeftSideOfLine(sprite.getHitBox().getDownRightPoint(), sprite.getHitBox().getUpperRightPoint(), point)) {
			return false;
		}
		if (!isPointOnLeftSideOfLine(sprite.getHitBox().getUpperRightPoint(), sprite.getHitBox().getUpperLeftPoint(), point)) {
			return false;
		}
		if (!isPointOnLeftSideOfLine(sprite.getHitBox().getUpperLeftPoint(), sprite.getHitBox().getDownLeftPoint(), point)) {
			return false;
		}
		return true;
	}
	
	
	private static boolean isPointOnLeftSideOfLine(Point linePointA, Point linePointB, Point c) {
		return ((linePointB.x - linePointA.x) * (c.y - linePointA.y) - (linePointB.y - linePointA.y) * (c.x - linePointA.x)) > 0;
	}
}
