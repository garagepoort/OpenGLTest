package be.davidcorp.domain.system;

import be.davidcorp.domain.World;
import be.davidcorp.domain.components.InputComponent;
import be.davidcorp.domain.components.PositionComponent;
import be.davidcorp.domain.entity.Sprite;
import be.davidcorp.domain.entity.SpriteFactory;

public class FiringSystem implements System {

	private static FiringSystem instance = new FiringSystem();

	private FiringSystem() {
	}

	public static FiringSystem getInstance() {
		return instance;
	}

	@Override
	public void executeSystem(Sprite sprite, float secondsMovedInGame) {
		InputComponent inputComponent = sprite.getComponent(InputComponent.class);
		PositionComponent positionComponent = sprite.getComponent(PositionComponent.class);
		
		if (inputComponent != null 
				&& positionComponent != null 
				&& inputComponent.leftMouseButtonDown) {
			World.addSprite(SpriteFactory.createBullet(positionComponent.x, positionComponent.y));
		}
	}

}
