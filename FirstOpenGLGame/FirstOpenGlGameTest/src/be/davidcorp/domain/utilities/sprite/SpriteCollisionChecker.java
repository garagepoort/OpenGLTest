package be.davidcorp.domain.utilities.sprite;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.metric.Point;

public class SpriteCollisionChecker {

	public static boolean doesCollisionExist(Sprite sprite1, Sprite sprite2) {
		Point spriteA_upperLeftPoint = sprite1.getHitBox().getUpperLeftPoint();
		Point spriteA_downRightPoint = sprite1.getHitBox().getDownRightPoint();

		Point spriteB_upperLeftPoint = sprite2.getHitBox().getUpperLeftPoint();
		Point spriteB_downRightPoint = sprite2.getHitBox().getDownRightPoint();

		return (spriteA_upperLeftPoint.x < spriteB_downRightPoint.x 
				&& spriteA_downRightPoint.x > spriteB_upperLeftPoint.x 
				&& spriteA_upperLeftPoint.y > spriteB_downRightPoint.y 
				&& spriteA_downRightPoint.y < spriteB_upperLeftPoint.y);
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
