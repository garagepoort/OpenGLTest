package be.davidcorp.loaderSaver.repository;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import be.davidcorp.domain.trigger.Trigger;

public class TriggerRepository {

	private static Map<Integer, Trigger> triggers = newHashMap();
	
	public void addTrigger(Trigger trigger){
		triggers.put(trigger.getID(), trigger);
	}
	
	public Trigger getTrigger(int id){
		return triggers.get(id);
	}
}
