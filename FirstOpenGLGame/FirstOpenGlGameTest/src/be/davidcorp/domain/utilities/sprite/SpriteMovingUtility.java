package be.davidcorp.domain.utilities.sprite;

import static be.davidcorp.domain.sprite.organic.player.PlayerManager.getCurrentPlayer;
import be.davidcorp.WindDirection;
import be.davidcorp.domain.game.CurrentGameFieldManager;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.metric.Point;
import be.davidcorp.metric.Vector;

public class SpriteMovingUtility {

	
	public static void movePlayerInGameField(WindDirection direction, float distance){
		getCurrentPlayer().move(direction, distance);
		if (CurrentGameFieldManager.getCurrentGameField().doesPlayerCollideWithAnyConstructionItem() || playerAgainstBoundary()) {
			reverseMovement(direction, distance);
		}
	}

	public static Point moveSpriteOnHisDirectionVector(Sprite sprite, float distance) {
		Point start = new Point(sprite.getX(), sprite.getY(), 0);
		sprite.getDirectionVector().normalize();
		sprite.getDirectionVector().mult(distance);
		start.add(sprite.getDirectionVector());
		sprite.setX(start.x);
		sprite.setY(start.y);
		sprite.setMoving(true);
		return start;
	}
	
	public static void moveSpriteInGameField(Sprite sprite, WindDirection direction, float distance) {
		switch (direction) {
			case NORTH :
				sprite.setY(sprite.getY() + distance);
				break;
			case NORTHEAST :
				sprite.setX(sprite.getX() + distance);
				sprite.setY(sprite.getY() + distance);
				break;
			case EAST :
				sprite.setX(sprite.getX() + distance);
				break;
			case SOUTHEAST :
				sprite.setX(sprite.getX() + distance);
				sprite.setY(sprite.getY() - distance);
				break;
			case SOUTH :
				sprite.setY(sprite.getY() - distance);
				break;
			case SOUTHWEST :
				sprite.setX(sprite.getX() - distance);
				sprite.setY(sprite.getY() - distance);
				break;
			case WEST :
				sprite.setX(sprite.getX() - distance);
				break;
			case NORTHWEST :
				sprite.setX(sprite.getX() - distance);
				sprite.setY(sprite.getY() + distance);
				break;
		}
		sprite.setMoving(true);
	}
	
	public static void turnSpriteTowardsPoint(Sprite turningSprite, Point point){
		turningSprite.setDirectionVector(new Vector(turningSprite.getCenter(), point));
		turningSprite.setRotationAngle(SpriteRotator.calcRotationAngle(turningSprite.getDirectionVector()));
	}
	
	private static void reverseMovement(WindDirection direction, float distance) {
		if(direction == WindDirection.NORTH){
			getCurrentPlayer().move(WindDirection.SOUTH, distance);
		}
		if(direction == WindDirection.SOUTH){
			getCurrentPlayer().move(WindDirection.NORTH, distance);
		}
		if(direction == WindDirection.EAST){
			getCurrentPlayer().move(WindDirection.WEST, distance);
		}
		if(direction == WindDirection.WEST){
			getCurrentPlayer().move(WindDirection.EAST, distance);
		}
		getCurrentPlayer().setMoving(false);
	}

	private static boolean playerAgainstBoundary() {
		return playerAgainstLeftBoundary() || playerAgainstRightBoundary() || playerAgainstLowerBoundary() || playerAgainstUpperBoundary();
	}

	private static boolean playerAgainstLeftBoundary() {
		return getCurrentPlayer().getX() <= 0;
	}

	private static boolean playerAgainstLowerBoundary() {
		return getCurrentPlayer().getY() <= 0;
	}

	private static boolean playerAgainstUpperBoundary() {
		return getCurrentPlayer().getY() >= CurrentGameFieldManager.getCurrentGameField().getHeight() - getCurrentPlayer().getHeight();
	}

	private static boolean playerAgainstRightBoundary() {
		return getCurrentPlayer().getX() >= CurrentGameFieldManager.getCurrentGameField().getWidth() - getCurrentPlayer().getWidth();
	}
}
