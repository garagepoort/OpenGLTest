package be.davidcorp.domain.skill;

import java.util.ArrayList;

import be.davidcorp.domain.exception.SkillException;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.organic.OrganicSprite;
import be.davidcorp.domain.utilities.Cooldowner;



public abstract class Skill<USEDON extends Sprite> {
	
	private Cooldowner cooldowner = new Cooldowner(200);
	private int manaCost;
	
	public abstract void useSkill(OrganicSprite user, USEDON usedOn) throws SkillException;
	
	public abstract void useSkill(OrganicSprite user, ArrayList<USEDON> usedOns) throws SkillException;
	
	public void updateCooldown(){
		cooldowner.progressCooldown();
	}

	public boolean isCooledDown(){
		return cooldowner.isCoolDowned();
	}
	
	public void resetCooldown(){
		cooldowner.resetCooldownProgress();
	}

	public int getManaCost() {
		return manaCost;
	}

	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}
}
