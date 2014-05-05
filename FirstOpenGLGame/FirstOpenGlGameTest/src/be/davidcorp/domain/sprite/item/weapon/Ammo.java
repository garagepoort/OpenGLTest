package be.davidcorp.domain.sprite.item.weapon;

import static be.davidcorp.domain.game.CurrentGameFieldManager.getCurrentGameField;
import be.davidcorp.component.TimeToLiveComponent;
import be.davidcorp.domain.game.CurrentGameFieldManager;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.utilities.sprite.SpriteCollisionChecker;
import be.davidcorp.domain.utilities.sprite.SpriteMovingUtility;
import be.davidcorp.metric.Point;
import be.davidcorp.metric.Vector;

public abstract class Ammo extends Item {
	private static final long serialVersionUID = 8930664867463298725L;
	private int damage = 50;

	public Ammo(float x, float y, int width, int height, int TimeToLive) {
		super(x, y);
		addComponent(new TimeToLiveComponent(TimeToLive));
		setWidth(width);
		setHeight(height);
		setColor(new Color(0, 255, 0));
	}

	public void launch(Point p) {
		setDirectionVector(new Vector(new Point(getX() + getWidth() / 2, getY()
				+ getHeight() / 2, 0), p));
	}

	@Override
	public void updateSprite(float secondsMovedInGame) {
		super.updateSprite(secondsMovedInGame);
		SpriteMovingUtility.moveSpriteOnHisDirectionVector(this, secondsMovedInGame * this.getSpeed());
		
		for (int i = 0; i < getCurrentGameField().getSpritesInWorld().size() && isAlive(); i++) {
			Sprite sprite = getCurrentGameField().getSpritesInWorld().get(i);
			if(sprite instanceof ConstructionSprite){
				if (SpriteCollisionChecker.doesCollisionExist(this, sprite)) {
					hitSprite(sprite);
				}
			}
		}
		
		for (int i = 0; i < CurrentGameFieldManager.getCurrentGameField().getSpritesInWorld().size() && isAlive(); i++) {
			Sprite sprite = CurrentGameFieldManager.getCurrentGameField().getSpritesInWorld().get(i);
			if (SpriteCollisionChecker.doesCollisionExist(this, sprite)) {
				hitSprite(sprite);
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
