package be.davidcorp.domain.system;

import be.davidcorp.domain.components.DirectionComponent;
import be.davidcorp.domain.components.InputComponent;
import be.davidcorp.domain.components.PlayerComponent;
import be.davidcorp.domain.components.PositionComponent;
import be.davidcorp.domain.components.TargetComponent;
import be.davidcorp.domain.components.VelocityComponent;
import be.davidcorp.domain.entity.Sprite;
import be.davidcorp.metric.Point;
import be.davidcorp.metric.Vector;

public class VelocitySystem extends System {

	private static VelocitySystem instance = new VelocitySystem();

	private VelocitySystem() {
	}

	public static VelocitySystem getInstance() {
		return instance;
	}

	@Override
	public void executeSystem(Sprite sprite, float secondsMovedInGame) {

		VelocityComponent velocityComponent = sprite.getComponent(VelocityComponent.class);

		if (containsNecessaryComponents(velocityComponent)) {
			float currentSpeed = velocityComponent.defaultSpeed * secondsMovedInGame;
			
			PlayerComponent playerComponent = sprite.getComponent(PlayerComponent.class);
			InputComponent inputComponent = sprite.getComponent(InputComponent.class);

			if (containsNecessaryComponents(playerComponent, inputComponent)) 
			{
				setPlayersVelocityAccordingToInput(velocityComponent, currentSpeed, inputComponent);
			} 
			else if (playerComponent == null) 
			{
				setSpritesVelocityAccordingToDirection(sprite, velocityComponent, currentSpeed);

				TargetComponent targetComponent = sprite.getComponent(TargetComponent.class);
				if(containsNecessaryComponents(targetComponent)){
					setSpritesVelocityAccordingToTarget(velocityComponent,targetComponent, currentSpeed, sprite);
				}
			}
		}
	}

	public void setSpritesVelocityAccordingToTarget(VelocityComponent velocityComponent, TargetComponent targetComponent, float currentSpeed, Sprite sprite) {
		PositionComponent positionComponent = sprite.getComponent(PositionComponent.class);

		if (containsNecessaryComponents(targetComponent, positionComponent)) {
			Point startPoint = new Point(positionComponent.x, positionComponent.y, 0);
			Vector directionVector = new Vector(startPoint, targetComponent.targetPoint);
			directionVector.normalize();
		}
	}
	
	public void setSpritesVelocityAccordingToDirection(Sprite sprite, VelocityComponent velocityComponent, float currentSpeed){
		DirectionComponent directionComponent = sprite.getComponent(DirectionComponent.class);
		directionComponent.vector.normalize();
		velocityComponent.xVelocity = currentSpeed * directionComponent.vector.x;
		velocityComponent.yVelocity = currentSpeed * directionComponent.vector.y;
	}

	public void setPlayersVelocityAccordingToInput(VelocityComponent velocityComponent, float currentSpeed, InputComponent inputComponent) {
		if (inputComponent.keyMoveRight) {
			velocityComponent.xVelocity = currentSpeed;
		} else if (inputComponent.keyMoveLeft) {
			velocityComponent.xVelocity = -currentSpeed;
		} else {
			velocityComponent.xVelocity = 0;
		}

		if (inputComponent.keyMoveDown) {
			velocityComponent.yVelocity = -currentSpeed;
		} else if (inputComponent.keyMoveUp) {
			velocityComponent.yVelocity = currentSpeed;
		} else {
			velocityComponent.yVelocity = 0;
		}
	}

}
