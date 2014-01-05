package be.davidcorp.domain.sprite.construction;

import static be.davidcorp.domain.sprite.SpriteType.WALL;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.test.builder.ConstructionSpriteBuilder;

public class ConstructionSpriteFactory {

	public static ConstructionSprite createWall(float x, float y, int width, int height) {
		return new ConstructionSpriteBuilder()
			.withX(x)
			.withY(y)
			.withHeight(height)
			.withWidth(width)
			.withSpriteType(WALL)
			.withColor(new Color(255, 0, 0))
			.build();
	}
}
