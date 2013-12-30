package be.davidcorp.domain.components;

import be.davidcorp.metric.Point;

public class TargetComponent implements Component {

	public Point targetPoint;

	public TargetComponent(Point targetPoint) {
		this.targetPoint = targetPoint;
	}

	
}
