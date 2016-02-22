package be.davidcorp.applicationLayer.dto.light;

import be.davidcorp.metric.Point;

public class LightCollisionChecker {


		public static boolean doesCollisionExist(LightDTO sprite1, LightDTO sprite2){
			if (doesSpriteCollideWithPoint(sprite1, sprite2.getDownLeftPoint()) || doesSpriteCollideWithPoint(sprite1, sprite2.getDownRightPoint())
					|| doesSpriteCollideWithPoint(sprite1, sprite2.getUpperRightPoint()) || doesSpriteCollideWithPoint(sprite1, sprite2.getUpperLeftPoint())) {
				return true;
			}
			if (doesSpriteCollideWithPoint(sprite2, sprite1.getDownLeftPoint()) || doesSpriteCollideWithPoint(sprite2, sprite1.getDownRightPoint())
					|| doesSpriteCollideWithPoint(sprite2, sprite1.getUpperRightPoint()) || doesSpriteCollideWithPoint(sprite2, sprite1.getUpperLeftPoint())) {
				return true;
			}
			return false;
		}
		
		public static boolean doesSpriteCollideWithPoint(LightDTO sprite, Point point) {

			if (!isPointOnLeftSideOfLine(sprite.getDownLeftPoint(), sprite.getDownRightPoint(), point)) {
				return false;
			}
			if (!isPointOnLeftSideOfLine(sprite.getDownRightPoint(), sprite.getUpperRightPoint(), point)) {
				return false;
			}
			if (!isPointOnLeftSideOfLine(sprite.getUpperRightPoint(), sprite.getUpperLeftPoint(), point)) {
				return false;
			}
			if (!isPointOnLeftSideOfLine(sprite.getUpperLeftPoint(), sprite.getDownLeftPoint(), point)) {
				return false;
			}
			return true;
		}
		
		
		private static boolean isPointOnLeftSideOfLine(Point linePointA, Point linePointB, Point c) {
			return ((linePointB.x - linePointA.x) * (c.y - linePointA.y) - (linePointB.y - linePointA.y) * (c.x - linePointA.x)) > 0;
		}
}
