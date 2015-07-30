package main.java.be.davidcorp.view.light;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.dto.light.EndPoint;
import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.applicationLayer.dto.light.LightTriangle;
import be.davidcorp.applicationLayer.dto.light.Segment;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.metric.Point;

public class LightManager {
	private PlayerFacade playerFacade = new PlayerFacade();
	private GameFieldFacade gameFieldFacade = new GameFieldFacade();

	private static ArrayList<EndPoint> endpoints = new ArrayList<>();
	private static ArrayList<Segment> segments = new ArrayList<>();
	private static ArrayList<Segment> open = new ArrayList<>();
	private static ArrayList<LightDTO> gamefieldLights = new ArrayList<LightDTO>();

	private static LightDTO playerFlashLight;
	private static LightDTO playerLineOfSight;

	public void traceAllLights() {
		gamefieldLights.clear();
		endpoints.clear();
		segments.clear();
		open.clear();


		for (ConstructionSpriteDTO constructionSpriteDTO : gameFieldFacade.getConstructionSpritesFromWorld()) {
			addConstructionSprite(constructionSpriteDTO);
		}
		traceGamefieldLights();
		tracePlayerFlashLight();
		tracePlayerLineOfSight();
		
	}
	private void traceGamefieldLights() {
		for (LightDTO lightDTO : gameFieldFacade.getLightsFromWorld()) {
			traceLight(lightDTO);
			gamefieldLights.add(lightDTO);
		}
	}
	private void tracePlayerLineOfSight() {
		LightDTO lineOfSight = playerFacade.getLineOfSight();
		if (lineOfSight != null){
			traceLight(lineOfSight);
			playerLineOfSight = lineOfSight;
		}else{
			playerLineOfSight = null;
		}
	}
	private void tracePlayerFlashLight() {
		LightDTO flashLight = playerFacade.getFlashLight();
		if (flashLight != null) {
			traceLight(flashLight);
			playerFlashLight = flashLight;
		}else{
			playerFlashLight = null;
		}
	}
	private void traceLight(LightDTO lightDTO) {
		Point centerOfLight = lightDTO.getCenter();
		// LightTriangle lt = new LightTriangle(new Point(p.x-50, p.y+250, 0),
		// new Point(p.x+50, p.y+250, 0), getPlayer().getCenter());
		lightDTO.removeAllLightTriangles();
		for (Segment segment : segments) {
			double dx = 0.5 * (segment.p1.point.x + segment.p2.point.x) - centerOfLight.x;
			double dy = 0.5 * (segment.p1.point.y + segment.p2.point.y) - centerOfLight.y;
			
			// NOTE: we only use this for comparison so we can use distance squared instead of distance
			segment.distance = (float) (dx * dx + dy * dy);

			// NOTE: future optimization: we could record the quadrant and the y/x or x/y ratio, 
			//and sort by (quadrant,ratio), instead of calling atan2.
			segment.p1.angle = (float) Math.atan2(segment.p1.point.y - centerOfLight.y, segment.p1.point.x - centerOfLight.x);
			segment.p2.angle = (float) Math.atan2(segment.p2.point.y - centerOfLight.y, segment.p2.point.x - centerOfLight.x);

			double dAngle = segment.p2.angle - segment.p1.angle;
			if (dAngle <= -Math.PI) {
				dAngle += 2 * Math.PI;
			}
			if (dAngle > Math.PI) {
				dAngle -= 2 * Math.PI;
			}
			segment.p1.begin = (dAngle > 0.0);
			segment.p2.begin = !segment.p1.begin;
		}
		Collections.sort(endpoints);
		sweep(360, centerOfLight, lightDTO);
	}

	private void sweep(float maxAngle, Point center, LightDTO lightDTO) {
		// output = []; // output set of triangles
		// demo_intersectionsDetected = [];
		// endpoints.sort(_endpoint_compare, true);

		float beginAngle = 0.0f;

		// At the beginning of the sweep we want to know which
		// segments are active. The simplest way to do this is to make
		// a pass collecting the segments, and make another pass to
		// both collect and process them. However it would be more
		// efficient to go through all the segments, figure out which
		// ones intersect the initial sweep line, and then sort them.
		for (int pass = 0; pass < 2; pass++) {
			for (EndPoint p : endpoints) {
				double angle = Math.toDegrees(p.angle);
				if (Math.toDegrees(p.angle) < 0) {
					angle = 180 - angle;
				}
				if (pass == 1 && angle > maxAngle) {
					// Early exit for the visualization to show the sweep
					// process
					break;
				}

				Segment current_old = open.isEmpty() ? null : open.get(0);

				if (p.begin) {
					// Insert into the right place in the list
					Segment node = open.isEmpty() ? null : open.get(0);
					for (int i = 1; node != null && i < open.size() && _segment_in_front_of(p.segment, node, center); i++) {
						node = open.get(i);
					}
					// while (node != null && _segment_in_front_of(p.segment,
					// node, center)) {
					// node = node.next;
					// }
					if (node == null) {
						open.add(p.segment);
					} else {
						open.add(open.indexOf(node), p.segment);
					}
				} else {
					open.remove(p.segment);
				}

				Segment current_new = open.isEmpty() ? null : open.get(0);
				if (current_old != current_new) {
					if (pass == 1) {
						addTriangle(beginAngle, p.angle, current_old, center, lightDTO);
					}
					beginAngle = p.angle;
				}

			}
		}
	}

	private void addTriangle(float angle1, float angle2, Segment segment, Point center, LightDTO l) {
		Point p1 = center;
		Point p2 = new Point((float) (center.x + Math.cos(angle1)), (float) (center.y + Math.sin(angle1)), 0);
		Point p3 = new Point(0, 0, 0);
		Point p4 = new Point(0, 0, 0);

		if (segment != null) {
			//
			p3.x = segment.p1.point.x;
			p3.y = segment.p1.point.y;
			p4.x = segment.p2.point.x;
			p4.y = segment.p2.point.y;

		} else {
			// Stop the triangle at a fixed distance; this probably is
			// not what we want, but it never gets used in the demo
			p3.x = (float) (center.x + Math.cos(angle1) * 10);
			p3.y = (float) (center.y + Math.sin(angle1) * 10);
			p4.x = (float) (center.x + Math.cos(angle2) * 10);
			p4.y = (float) (center.y + Math.sin(angle2) * 10);
		}

		Point pBegin = lineIntersection(p3, p4, p1, p2);

		p2.x = (float) (center.x + Math.cos(angle2));
		p2.y = (float) (center.y + Math.sin(angle2));
		Point pEnd = lineIntersection(p3, p4, p1, p2);

		l.addLightTriangle(new LightTriangle(pBegin, pEnd, center));

	}

	private Boolean leftOf(Segment s, Point p) {
		float cross = (s.p2.point.x - s.p1.point.x) * (p.y - s.p1.point.y) - (s.p2.point.y - s.p1.point.y) * (p.x - s.p1.point.x);
		return cross < 0;
	}

	private Point interpolate(Point p, Point q, float f) {
		return new Point(p.x * (1 - f) + q.x * f, p.y * (1 - f) + q.y * f, 0);
	}

	private Point lineIntersection(Point p1, Point p2, Point p3, Point p4) {
		// From http://paulbourke.net/geometry/lineline2d/
		float s = ((p4.x - p3.x) * (p1.y - p3.y) - (p4.y - p3.y) * (p1.x - p3.x)) / ((p4.y - p3.y) * (p2.x - p1.x) - (p4.x - p3.x) * (p2.y - p1.y));
		return new Point(p1.x + s * (p2.x - p1.x), p1.y + s * (p2.y - p1.y), 0);
	}

	private boolean _segment_in_front_of(Segment a, Segment b, Point relativeTo) {
		// NOTE: we slightly shorten the segments so that
		// intersections of the endpoints (common) don't count as
		// intersections in this algorithm
		boolean A1 = leftOf(a, interpolate(b.p1.point, b.p2.point, 0.01f));
		boolean A2 = leftOf(a, interpolate(b.p2.point, b.p1.point, 0.01f));
		boolean A3 = leftOf(a, relativeTo);
		boolean B1 = leftOf(b, interpolate(a.p1.point, a.p2.point, 0.01f));
		boolean B2 = leftOf(b, interpolate(a.p2.point, a.p1.point, 0.01f));
		boolean B3 = leftOf(b, relativeTo);

		// NOTE: this algorithm is probably worthy of a short article
		// but for now, draw it on paper to see how it works. Consider
		// the line A1-A2. If both B1 and B2 are on one side and
		// relativeTo is on the other side, then A is in between the
		// viewer and B. We can do the same with B1-B2: if A1 and A2
		// are on one side, and relativeTo is on the other side, then
		// B is in between the viewer and A.
		if (B1 == B2 && B2 != B3)
			return true;
		if (A1 == A2 && A2 == A3)
			return true;
		if (A1 == A2 && A2 != A3)
			return false;
		if (B1 == B2 && B2 == B3)
			return false;

		// If A1 != A2 and B1 != B2 then we have an intersection.
		// Expose it for the GUI to show a message. A more robust
		// implementation would split segments at intersections so
		// that part of the segment is in front and part is behind.

		// demo_intersectionsDetected.push([a.p1, a.p2, b.p1, b.p2]);
		return false;

		// NOTE: previous implementation was a.d < b.d. That's simpler
		// but trouble when the segments are of dissimilar sizes. If
		// you're on a grid and the segments are similarly sized, then
		// using distance will be a simpler and faster implementation.
	}

	public List<Segment> getSegments() {
		return Collections.unmodifiableList(segments);
	}

	private void addConstructionSprite(ConstructionSpriteDTO constructionSprite) {
		endpoints.add(constructionSprite.ep1);
		endpoints.add(constructionSprite.ep2);
		endpoints.add(constructionSprite.ep3);
		endpoints.add(constructionSprite.ep4);
		endpoints.add(constructionSprite.ep5);
		endpoints.add(constructionSprite.ep6);
		endpoints.add(constructionSprite.ep7);
		endpoints.add(constructionSprite.ep8);

		segments.add(constructionSprite.s1);
		segments.add(constructionSprite.s2);
		segments.add(constructionSprite.s3);
		segments.add(constructionSprite.s4);
	}

	public List<LightDTO> getGamefieldLights() {
		return Collections.unmodifiableList(gamefieldLights);
	}

	public LightDTO getPlayerFlashLight() {
		return playerFlashLight;
	}
	
	public LightDTO getPlayerLineOfSight() {
		return playerLineOfSight;
	}
}
