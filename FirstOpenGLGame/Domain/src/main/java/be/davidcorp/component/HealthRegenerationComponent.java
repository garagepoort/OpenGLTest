package be.davidcorp.component;

import static be.davidcorp.component.ComponentType.HEALTHREGENERATION;

public class HealthRegenerationComponent implements Component{

	public int healthRegeneratedPerSecond;

	
	public HealthRegenerationComponent(int healthRegeneratedPerSecond) {
		this.healthRegeneratedPerSecond = healthRegeneratedPerSecond;
	}

	@Override
	public ComponentType getType() {
		return HEALTHREGENERATION;
	}
}
