package be.davidcorp.domain.sprite.construction;


import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.utilities.sprite.SpriteRotator;

public class Door extends ConstructionSprite{

	private boolean open;
	private float openSpeed = 0.7f;
	private boolean switched;
	
	/**Constructor 
	 * @see ConstructionSprite
	 */
	public Door(float x, float y, int width, int height) {
		super(x, y, width, height);
	}

	public Door() {
		super();
		setColor(new Color(0, 255, 0));
	}

	public void openDoor(Sprite sprite){
			open = !open;
			switched=true;
	}

	@Override
	public void updateSprite(float secondsMovedInGame){
		super.updateSprite(secondsMovedInGame);
		if(isDoorOpening()){
			openDoor(secondsMovedInGame);
		}else if(isDoorClosing()){
			closeDoor(secondsMovedInGame);
		}else{
//			SoundManager.stopSound("door");
		}
		if(GameFieldManager.getCurrentGameField().againstOrganicSprite(this)){
			reverseUpdateDoor();
		}
	}

	private boolean isDoorClosing() {
		return !open && getRotationAngle()>0;
	}

	private boolean isDoorOpening() {
		return open && getRotationAngle()<90;
	}

	private void openDoor(float secondsMovedInGame) {
		SpriteRotator.rotateSprite(this, openSpeed * secondsMovedInGame, getHitBox().getUpperLeftPoint());
		if(switched){
//			SoundManager.replaySound("door");
			switched=false;
		}
	}

	private void closeDoor(float secondsMovedInGame) {
		SpriteRotator.rotateSprite(this, -openSpeed * secondsMovedInGame, getHitBox().getUpperLeftPoint());
		if(switched){
//			SoundManager.replaySound("door");
			switched=false;
		}
	}
	
	
	private void reverseUpdateDoor(){
		if(isDoorOpening()){
			SpriteRotator.rotateSprite(this, -openSpeed, getHitBox().getUpperLeftPoint());
		}else if(isDoorClosing()){
			SpriteRotator.rotateSprite(this, +openSpeed, getHitBox().getUpperLeftPoint());
		}
	}

	@Override
	public void onDeath() {
		super.onDeath();
//		SoundManager.stopSound("door");
	}

	public void onUse(Sprite sprite) {
		openDoor(sprite);
	}

	@Override
	public SpriteType getType() {
		return SpriteType.DOOR;
	}
	
	

}
