package be.davidcorp.domain.system;

import be.davidcorp.domain.World;
import be.davidcorp.domain.components.InputComponent;
import be.davidcorp.domain.components.PositionComponent;
import be.davidcorp.domain.entity.Sprite;
import be.davidcorp.domain.entity.SpriteFactory;
import be.davidcorp.metric.Point;

public class FiringSystem extends System {

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

		if (containsNecessaryComponents(inputComponent, positionComponent) && inputComponent.leftMouseButtonDown) {
			Point targetPoint = new Point(inputComponent.mouseXPosition, inputComponent.mouseYPosition, 0);
			World.addSprite(SpriteFactory.createBullet(positionComponent.x, positionComponent.y, targetPoint));
		}
	}

}
