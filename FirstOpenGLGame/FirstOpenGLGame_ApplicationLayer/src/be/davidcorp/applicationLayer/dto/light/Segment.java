package be.davidcorp.applicationLayer.dto.light;

import be.davidcorp.applicationLayer.dto.color.ColorDTO;

public class Segment {

	public float distance;
	public EndPoint p1;
	public EndPoint p2;
	public ColorDTO color;
	public Segment(EndPoint p1, EndPoint p2, ColorDTO color){
		this.p1=p1;
		this.p2=p2;
		this.color=color;
	}
	
	
}
