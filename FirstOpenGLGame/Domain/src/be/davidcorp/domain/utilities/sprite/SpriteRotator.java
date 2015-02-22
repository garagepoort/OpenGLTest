package be.davidcorp.domain.utilities.sprite;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.metric.Point;
import be.davidcorp.metric.Vector;

public class SpriteRotator {

	public static void rotateSprite(Sprite sprite, float angle, Point rotationPoint) { 
		rotateSpriteAroundPointWithAngle(sprite, angle, rotationPoint);
	}

	public static void rotateSpriteAroundPoint(Sprite sprite, float angle, Point rotationPoint) {
		rotateSpriteAroundPointWithAngle(sprite, angle, rotationPoint);
	}

	private static void rotateSpriteAroundPointWithAngle(Sprite sprite, float angle, Point rotationPoint) {
		sprite.setRotationAngle(sprite.getRotationAngle() + angle);
		angle = (float) Math.toRadians(angle);
		rotatePoint(sprite.getHitBox().getDownLeftPoint(), rotationPoint, angle);
		rotatePoint(sprite.getHitBox().getDownRightPoint(), rotationPoint, angle);
		rotatePoint(sprite.getHitBox().getUpperRightPoint(), rotationPoint, angle);
		rotatePoint(sprite.getHitBox().getUpperLeftPoint(), rotationPoint, angle);
	}

	private static void rotatePoint(Point point, Point rotationPoint,
			float angle) {
		point.x = (float) (Math.cos(angle) * (point.x - rotationPoint.x)
				- Math.sin(angle) * (point.y - rotationPoint.y) + rotationPoint.x);
		point.y = (float) (Math.sin(angle) * (point.x - rotationPoint.x)
				+ Math.cos(angle) * (point.y - rotationPoint.y) + rotationPoint.y);
	}
	
	public static float calcRotationAngle(Vector lookVector) {
		Vector vector = new Vector(new Point(0, 0, 0), new Point(0, 1, 0));
		lookVector.normalize();
		double computed = Math.atan2(lookVector.y, lookVector.x) - Math.atan2(vector.y, vector.x);
		return ((float) Math.toDegrees(computed));
	}
	

}
