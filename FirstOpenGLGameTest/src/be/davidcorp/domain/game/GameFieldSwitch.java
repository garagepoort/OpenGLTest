package be.davidcorp.domain.game;

import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.organic.player.Player;
import be.davidcorp.metric.Point;

/**
 * 
 * @author david
 *
 * <p>A class that defines to which {@link Gamefield} should be switched if a Player stands on this {@link GameFieldSwitch}.
 * It also indicates the position this {@link Player} will have in the new {@link Gamefield}.<p>
 */
public class GameFieldSwitch extends Sprite{

	private String gameFieldName;
	private Point spawnPoint;
	
	public GameFieldSwitch(float x, float y, int width, int height, String gamefieldName) throws SpriteException {
		super(x, y, width, height);
		setGameFieldName(gamefieldName);
	}
	
	public String getGameFieldName() {
		return gameFieldName;
	}

	public void setGameFieldName(String gameFieldName) {
		this.gameFieldName = gameFieldName;
	}
	
	
	public Point getSpawnPoint() {
		return spawnPoint;
	}

	public void setSpawnPoint(Point spawnPoint) {
		this.spawnPoint = spawnPoint;
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub

	}


}
