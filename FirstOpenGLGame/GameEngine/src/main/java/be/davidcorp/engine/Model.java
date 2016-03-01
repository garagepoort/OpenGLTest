package be.davidcorp.engine;

import java.util.List;

import be.davidcorp.metric.Point;

public class Model {

	private final List<Point> points;
	private int numberOfVertices;

	public Model(List<Point> points) {
		this.points = points;
	}

	public List<Point> getPoints() {
		return points;
	}

	public int getNumberOfVertices() {
		return points.size();
	}
}
