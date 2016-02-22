package be.davidcorp.domain.mission.acceptionCriteria;

import static com.google.common.collect.Lists.newArrayList;
import java.util.List;

public class AcceptationCriteriaHandler {

	private List<AcceptationCriteria> acceptationCriterias = newArrayList();
	
	public AcceptationCriteriaHandler withCriteria(AcceptationCriteria acceptationCriteria){
		acceptationCriterias.add(acceptationCriteria);
		return this;
	}
	
	public boolean areAllCriteriasOK(){
		for (AcceptationCriteria acceptationCriteria : acceptationCriterias) {
			if(!acceptationCriteria.isCriteriaOK()) return false;
		}
		return true;
	}
}
