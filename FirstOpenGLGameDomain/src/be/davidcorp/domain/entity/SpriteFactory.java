package be.davidcorp.domain.entity;

import be.davidcorp.domain.components.InputComponent;
import be.davidcorp.domain.components.PlayerComponent;
import be.davidcorp.domain.components.PositionComponent;
import be.davidcorp.domain.components.RenderComponent;
import be.davidcorp.domain.components.SpeedComponent;
import be.davidcorp.domain.components.TimeToLiveComponent;

public class SpriteFactory {

	public static Sprite createPlayer(){
		return new Sprite(
				new PlayerComponent(),
				new InputComponent(), 
				new PositionComponent(10, 10), 
				new SpeedComponent(0.5f),
//				new RenderComponent("resources/images/player/playerstanding.png"));
				new RenderComponent("resources/images/light.png"));
	}
	
	public static Sprite createBullet(float x, float y){
		return new Sprite(
				new PositionComponent(x, y), 
				new SpeedComponent(1f),
				new TimeToLiveComponent(30),
//				new RenderComponent("resources/images/player/playerstanding.png"));
				new RenderComponent("resources/images/redMage.png"));
	}
}
