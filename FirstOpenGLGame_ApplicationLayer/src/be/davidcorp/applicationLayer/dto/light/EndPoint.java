package be.davidcorp.applicationLayer.dto.light;

import be.davidcorp.metric.Point;

public class EndPoint implements Comparable<EndPoint>{
	
	public Point point;
	public float angle;
	public boolean begin;
	public Segment segment;
	public EndPoint(Point p){
		point = p;
	}
	
	@Override
	public int compareTo(EndPoint e) {
		if (angle > e.angle) return 1;
        if (angle < e.angle) return -1;
        // But for ties (common), we want Begin nodes before End nodes
        if (!begin && e.begin) return 1;
        if (begin && !e.begin) return -1;
        return 0;
	}
	
	

}
