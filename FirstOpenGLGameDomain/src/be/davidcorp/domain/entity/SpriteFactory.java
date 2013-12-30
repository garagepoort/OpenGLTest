package be.davidcorp.domain.entity;

import be.davidcorp.domain.components.DirectionComponent;
import be.davidcorp.domain.components.InputComponent;
import be.davidcorp.domain.components.PlayerComponent;
import be.davidcorp.domain.components.PositionComponent;
import be.davidcorp.domain.components.RenderComponent;
import be.davidcorp.domain.components.RotationComponent;
import be.davidcorp.domain.components.TimeToLiveComponent;
import be.davidcorp.domain.components.VelocityComponent;
import be.davidcorp.metric.Point;
import be.davidcorp.metric.Vector;

public class SpriteFactory {

	public static Sprite createPlayer(){
		return new Sprite(
				new PlayerComponent(),
				new InputComponent(), 
				new PositionComponent(10, 10), 
				new VelocityComponent(0.5f),
				new RotationComponent(0),
				new RenderComponent("resources/images/player/playerstanding.png"));
//				new RenderComponent("resources/images/light.png"));
	}
	
	public static Sprite createBullet(float x, float y, Point targetPoint){
		return new Sprite(
				new PositionComponent(x, y), 
				new VelocityComponent(1f),
				new TimeToLiveComponent(10),
				new DirectionComponent(new Vector(new Point(x, y, 0), targetPoint)),
//				new RenderComponent("resources/images/player/playerstanding.png"));
				new RenderComponent("resources/images/redMage.png"));
	}
}
