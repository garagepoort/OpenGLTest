package be.davidcorp.domain.mission;

import be.davidcorp.domain.mission.acceptionCriteria.AcceptationCriteriaHandler;
import be.davidcorp.domain.trigger.Triggerable;

public class Mission extends Triggerable{

	private AcceptationCriteriaHandler acceptationCriteriaHandler;
	private String description;
	private boolean missionCompleted;
	
	public Mission(String description, AcceptationCriteriaHandler acceptationCriteriaHandler){
		this.description = description;
		this.acceptationCriteriaHandler = acceptationCriteriaHandler;
	}
	
	public boolean checkAllCriterias(){
		return acceptationCriteriaHandler.areAllCriteriasOK();
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public boolean isMissionCompleted(){
		return missionCompleted;
	}

	public void completeMission() {
		System.out.println("MISSION COMPLETED");
		missionCompleted = true;
	}
}