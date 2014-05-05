package be.davidcorp.domain.sprite.construction;

import static be.davidcorp.domain.sprite.SpriteType.WALL;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.test.builder.ConstructionSpriteBuilder;

public class ConstructionSpriteFactory {

	public static ConstructionSprite createWall(float x, float y, int width, int height) {
		return createWallWithID(0, x, y, width, height);
	}
	
	public static ConstructionSprite createDesk(float x, float y) {
		return createDeskWithID(0, x, y);
	}
	
	public static ConstructionSprite createWallWithID(int ID, float x, float y, int width, int height) {
		return new ConstructionSpriteBuilder()
			.withX(x)
			.withY(y)
			.withHeight(height)
			.withWidth(width)
			.withSpriteType(WALL)
			.withColor(new Color(255, 0, 0))
			.withID(ID)
			.withInWorld(true)
			.build();
	}
	
	public static ConstructionSprite createDeskWithID(int ID, float x, float y) {
		return new ConstructionSpriteBuilder()
			.withX(x)
			.withY(y)
			.withHeight(128)
			.withWidth(64)
			.withSpriteType(SpriteType.DESK)
			.withID(ID)
			.withInWorld(true)
			.build();
	}
}
