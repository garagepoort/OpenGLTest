package be.davidcorp.view.ui.panel;

import be.davidcorp.metric.Point;
import be.davidcorp.view.ui.UIComponent;

public class UIComponentCollisionChecker {


	public static boolean isPointInsidePanel(UIComponent component, Point point) {
		if (component.isClosed() || !component.isVisible())
			return false;
		if (!isLeft(new Point(component.getX(), component.getY(), 0), new Point(component.getX() + component.getWidth(), component.getY(), 0), point)) {
			return false;
		}
		if (!isLeft(new Point(component.getX() + component.getWidth(), component.getY(), 0), new Point(component.getX() + component.getWidth(), component.getY() + component.getHeight(), 0), point)) {
			return false;
		}
		if (!isLeft(new Point(component.getX() + component.getWidth(), component.getY() + component.getHeight(), 0), new Point(component.getX(), component.getY() + component.getHeight(), 0), point)) {
			return false;
		}
		if (!isLeft(new Point(component.getX(), component.getY() + component.getHeight(), 0), new Point(component.getX(), component.getY(), 0), point)) {
			return false;
		}
		return true;

	}

	private static boolean isLeft(Point a, Point b, Point c) {
		return ((b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x)) > 0;
	}
}
