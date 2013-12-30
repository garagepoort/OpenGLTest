package be.davidcorp.domain.components;

public class VelocityComponent implements Component {

	public float defaultSpeed;
	public float xVelocity;
	public float yVelocity;

	public VelocityComponent(float speed) {
		this.defaultSpeed = speed;
	}
}
