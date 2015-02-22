package be.davidcorp.applicationLayer.dto;

import be.davidcorp.applicationLayer.dto.light.EndPoint;
import be.davidcorp.applicationLayer.dto.light.Segment;
import be.davidcorp.applicationLayer.dto.mapper.ConstructionType;

public class ConstructionSpriteDTO extends SpriteDTO{

	public EndPoint ep1;
	public EndPoint ep2;
	public EndPoint ep3;
	public EndPoint ep4;
	public EndPoint ep5;
	public EndPoint ep6;
	public EndPoint ep7;
	public EndPoint ep8;

	public Segment s1;
	public Segment s2;
	public Segment s3;
	public Segment s4;
	private ConstructionType constructionSpriteType;
	
	public ConstructionSpriteDTO(ConstructionType constructionType) {
		this.constructionSpriteType = constructionType;
	}

	public void initializeSegments() {
		ep1 = new EndPoint(getDownLeftPoint());
		ep2 = new EndPoint(getDownRightPoint());
		ep3 = new EndPoint(getUpperRightPoint());
		ep4 = new EndPoint(getUpperLeftPoint());

		ep5 = new EndPoint(getDownLeftPoint());
		ep6 = new EndPoint(getDownRightPoint());
		ep7 = new EndPoint(getUpperRightPoint());
		ep8 = new EndPoint(getUpperLeftPoint());

		s1 = new Segment(ep1, ep2, getColor());
		s2 = new Segment(ep6, ep3, getColor());
		s3 = new Segment(ep7, ep4, getColor());
		s4 = new Segment(ep8, ep5, getColor());

		ep1.segment = s1;
		ep2.segment = s1;
		ep3.segment = s2;
		ep4.segment = s3;
		ep5.segment = s4;
		ep6.segment = s2;
		ep7.segment = s3;
		ep8.segment = s4;
	}

	public ConstructionType getConstructionSpriteType() {
		return constructionSpriteType;
	}
}
