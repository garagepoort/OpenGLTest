package be.davidcorp.engine;

import java.util.ArrayList;
import java.util.List;

import be.davidcorp.metric.Point;

public class Model {

	private List<Point> points = new ArrayList<>();
	private List<Color> colors = new ArrayList<>();
	private int numberOfVertices;
	private boolean usesColors;

	public Model(List<Point> points) {
		this.points = points;
	}

	public Model(List<Point> points, List<Color> colors) {
		this.points = points;
		this.colors = colors;
	}

	public boolean usesColors() {
		return usesColors;
	}

	public List<Point> getPoints() {
		return points;
	}

	public List<Color> getColors() {
		return colors;
	}

	public int getNumberOfVertices() {
		return points.size();
	}
}
