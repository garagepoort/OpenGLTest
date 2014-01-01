package be.davidcorp.domain.sprite.item.weapon;

import be.davidcorp.component.TimeToLiveComponent;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.utilities.sprite.SpriteCollisionChecker;
import be.davidcorp.domain.utilities.sprite.SpriteMovingUtility;
import be.davidcorp.metric.Point;
import be.davidcorp.metric.Vector;

public abstract class Ammo extends Item {
	private int damage = 50;

	public Ammo(float x, float y, int width, int height, int TimeToLive) {
		super(x, y);
		addComponent(new TimeToLiveComponent(TimeToLive));
		setWidth(width);
		setHeight(height);
		setColor(new Color(0, 1, 0));
	}

	public void launch(Point p) {
		setDirectionVector(new Vector(new Point(getX() + getWidth() / 2, getY()
				+ getHeight() / 2, 0), p));
	}

	@Override
	public void updateSprite(float secondsMovedInGame) {
		super.updateSprite(secondsMovedInGame);
		SpriteMovingUtility.moveSpriteOnHisDirectionVector(this, secondsMovedInGame * this.getSpeed());
		for (int i = 0; i < GameFieldManager.getCurrentGameField().getConstructionItems().size() && isAlive(); i++) {
			if (SpriteCollisionChecker.doesCollisionExist(this, GameFieldManager.getCurrentGameField().getConstructionItems().get(i))) {
				hitSprite(GameFieldManager.getCurrentGameField().getConstructionItems().get(i));
			}
		}
		for (int i = 0; i < GameFieldManager.getCurrentGameField().getEnemiesInWorld().size() && isAlive(); i++) {
			if (SpriteCollisionChecker.doesCollisionExist(this, GameFieldManager.getCurrentGameField().getEnemiesInWorld().get(i))) {
				hitSprite(GameFieldManager.getCurrentGameField().getEnemiesInWorld().get(i));
			}
		}
	}

	public void hitSprite(Sprite s) {
		s.removeHealth(damage);
		kill();
	}

	public int getDamage() {
		return damage;
	}

	protected void setDamage(int damage) {
		this.damage = damage;
	}

}
