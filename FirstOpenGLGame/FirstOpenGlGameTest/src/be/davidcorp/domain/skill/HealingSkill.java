package be.davidcorp.domain.skill;

import java.util.ArrayList;

import be.davidcorp.domain.exception.SkillException;
import be.davidcorp.domain.sprite.organic.OrganicSprite;

public class HealingSkill extends Skill<OrganicSprite> {
	private int healingPoints = 10;

	public HealingSkill() {
		setManaCost(10);
	}
	
	public void useSkill(OrganicSprite s, int healingPoints) {
		s.addHealth(healingPoints);
	}

	@Override
	public void useSkill(OrganicSprite healer, OrganicSprite healy)  {
		if (healer.getStamina() < getManaCost()) {
			throw new SkillException("Healer cannot perform skill because he has not enough mana");
		}
		if (isCooledDown()) {
			healy.addHealth(getHealingPoints());
			healer.removeStamina(getManaCost());
			resetCooldown();
		}
	}

	public int getHealingPoints() {
		return healingPoints;
	}

	public void setHealingPoints(int healingPoints) {
		this.healingPoints = healingPoints;
	}

	@Override
	public void useSkill(OrganicSprite healer, ArrayList<OrganicSprite> healies)  {
		if (healer.getStamina() < getManaCost()) {
			throw new SkillException("Healer cannot perform skill because he has not enough mana");
		}
		if (isCooledDown()) {
			for (OrganicSprite healy : healies) {
				healy.addHealth(getHealingPoints());
				healer.removeStamina(getManaCost());
				resetCooldown();
			}
		}
	}

}
