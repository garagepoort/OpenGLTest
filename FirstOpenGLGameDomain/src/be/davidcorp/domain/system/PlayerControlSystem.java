package be.davidcorp.domain.system;

import org.lwjgl.input.Mouse;

import be.davidcorp.domain.components.PositionComponent;
import be.davidcorp.domain.components.RotationComponent;
import be.davidcorp.domain.entity.Sprite;
import be.davidcorp.metric.Point;
import be.davidcorp.metric.Vector;

public class PlayerControlSystem implements System{

private static PlayerControlSystem instance = new PlayerControlSystem();
	
	private PlayerControlSystem(){}
	
	public static PlayerControlSystem getInstance(){
		return instance;
	}
	
	@Override
	public void executeSystem(Sprite sprite, float secondsMovedInGame) {
		RotationComponent rotationComponent = sprite.getComponent(RotationComponent.class);
		PositionComponent positionComponent = sprite.getComponent(PositionComponent.class);
		
		if(rotationComponent != null){
			Point beginPointOfVector = new Point(positionComponent.x, positionComponent.y, 0);
			Point EndPointOfVector = new Point(Mouse.getX(), Mouse.getY(), 0);
			Vector directionVector = new Vector(beginPointOfVector, EndPointOfVector);
			rotationComponent.rotationAngle = calcRotationAngle(directionVector);
		}
	}

	public static void lookInRightDirection(float x, float y) {
	}
	
	public static float calcRotationAngle(Vector lookVector) {
		Vector vector = new Vector(new Point(0, 0, 0), new Point(0, 1, 0));
		lookVector.normalize();
		double computed = Math.atan2(lookVector.y, lookVector.x) - Math.atan2(vector.y, vector.x);
		return ((float) Math.toDegrees(computed));
	}
	
}
