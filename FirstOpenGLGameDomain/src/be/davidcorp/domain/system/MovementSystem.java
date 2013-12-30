package be.davidcorp.domain.system;

import be.davidcorp.domain.components.PositionComponent;
import be.davidcorp.domain.components.VelocityComponent;
import be.davidcorp.domain.entity.Sprite;

public class MovementSystem extends System{

	private static MovementSystem instance = new MovementSystem();
	
	private MovementSystem(){}
	
	public static MovementSystem getInstance(){
		return instance;
	}
	
	@Override
	public void executeSystem(Sprite sprite, float secondsMovedInGame) {
		VelocityComponent velocityComponent = sprite.getComponent(VelocityComponent.class);
		PositionComponent positionComponent = sprite.getComponent(PositionComponent.class);
		
		if(containsNecessaryComponents(velocityComponent, positionComponent)){
			positionComponent.x += velocityComponent.xVelocity;
			positionComponent.y += velocityComponent.yVelocity;
		}
	}
	
}
