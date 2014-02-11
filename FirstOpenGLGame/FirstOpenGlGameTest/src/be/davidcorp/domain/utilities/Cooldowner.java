package be.davidcorp.domain.utilities;

import java.io.Serializable;

public class Cooldowner implements Serializable{

	private static final long serialVersionUID = -2351645229186446004L;
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
