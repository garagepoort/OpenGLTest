package be.davidcorp.domain.system;

import be.davidcorp.domain.components.InputComponent;
import be.davidcorp.domain.components.PositionComponent;
import be.davidcorp.domain.components.SpeedComponent;
import be.davidcorp.domain.entity.Sprite;

public class MovementSystem implements System{

	private static MovementSystem instance = new MovementSystem();
	
	private MovementSystem(){}
	
	public static MovementSystem getInstance(){
		return instance;
	}
	
	@Override
	public void executeSystem(Sprite sprite, float secondsMovedInGame) {
		SpeedComponent speedComponent = sprite.getComponent(SpeedComponent.class);
		PositionComponent positionComponent = sprite.getComponent(PositionComponent.class);
		InputComponent inputComponent = sprite.getComponent(InputComponent.class);
		
		if(positionComponent != null && speedComponent != null && inputComponent != null){
			float currentSpeed = speedComponent.speed*secondsMovedInGame;
			if(inputComponent.keyMoveRight) positionComponent.x += currentSpeed;
			if(inputComponent.keyMoveLeft) positionComponent.x -= currentSpeed;
			if(inputComponent.keyMoveDown) positionComponent.y -= currentSpeed;
			if(inputComponent.keyMoveUp) positionComponent.y += currentSpeed;
		}		
	}
}
