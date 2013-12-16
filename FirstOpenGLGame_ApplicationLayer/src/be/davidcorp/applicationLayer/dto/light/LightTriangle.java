package be.davidcorp.applicationLayer.dto.light;

import org.newdawn.slick.Color;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.utilities.sprite.SpriteCollisionChecker;
import be.davidcorp.metric.Point;

public class LightTriangle {

	public Point p1;
	public Point p2;
	public Point p3;
	public Color color;

	public LightTriangle(Point p1, Point p2, Point p3) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		color = new Color(255, 255, 255);
	}

	public boolean collision(Sprite s) {

		if (collision(s.getHitBox().getDownLeftPoint()) || collision(s.getHitBox().getDownRightPoint())
				|| collision(s.getHitBox().getUpperRightPoint()) || collision(s.getHitBox().getUpperLeftPoint())) {
			return true;
		}

		if (SpriteCollisionChecker.doesSpriteCollideWithPoint(s, p1)
				|| SpriteCollisionChecker.doesSpriteCollideWithPoint(s, p2)
				|| SpriteCollisionChecker.doesSpriteCollideWithPoint(s, p3)) {
			return true;
		}
		return false;
	}

	public boolean collision(Segment s) {

		if (collision(s.p1.point) || collision(s.p2.point)) {
			return true;
		}
		return false;
	}

	public boolean collision(Point p) {

		if (!isLeft(p1, p2, p)) {
			return false;
		}
		if (!isLeft(p2, p3, p)) {
			return false;
		}
		if (!isLeft(p3, p1, p)) {
			return false;
		}

		return true;

	}

	private boolean isLeft(Point a, Point b, Point c) {
		return ((b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x)) > 0;
	}
}
