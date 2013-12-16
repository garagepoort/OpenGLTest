package be.davidcorp.domain.utilities;

public class Cooldowner {

	private int cooldownProgress;
	private int whenCooldowned;
	
	public Cooldowner(int whenCoolDowned){
		this.whenCooldowned = whenCoolDowned;
	}
	
	public boolean isCoolDowned(){
		return cooldownProgress == whenCooldowned;
	}
	
	public void progressCooldown(){
		cooldownProgress++;
	}
	
	public void resetCooldownProgress(){
		cooldownProgress = 0;
	}
	
}
