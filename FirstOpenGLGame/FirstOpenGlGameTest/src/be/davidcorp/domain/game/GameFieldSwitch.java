package be.davidcorp.domain.game;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.metric.Point;

public class GameFieldSwitch extends Sprite{

	private static final long serialVersionUID = 2892558485334718857L;
	
	private String gameFieldName;
	private Point spawnPoint;
	
	public GameFieldSwitch(float x, float y, int width, int height, String gamefieldName) {
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

	@Override
	public SpriteType getType() {
		return null;
	}


}
