package be.davidcorp.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import be.davidcorp.domain.trigger.Trigger;

public class TriggerRepository {

	private static Map<Integer, Trigger> triggers = new ConcurrentHashMap<>();
	
	public void addTrigger(Trigger trigger){
		triggers.put(trigger.getID(), trigger);
	}
	
	public Trigger getTrigger(int id){
		return triggers.get(id);
	}
}
