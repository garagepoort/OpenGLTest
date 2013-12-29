package be.davidcorp.domain.components;

import be.davidcorp.metric.Point;
import be.davidcorp.metric.Vector;

public class DirectionComponent implements Component{

	public Vector vector = new Vector(new Point(0, 0, 0), new Point(0, 300, 0));
	
}
