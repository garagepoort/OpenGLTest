package be.davidcorp.domain.trigger;

import static com.google.common.collect.Maps.newHashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import be.davidcorp.domain.trigger.triggerableEvents.TriggerableEvent;


public class Triggerable {
	
	@SuppressWarnings("rawtypes")
	private Map<Trigger, List<TriggerableEvent>> events = newHashMap();
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void handleTriggerEvent(Trigger trigger){
		for(TriggerableEvent event : events.get(trigger)){
			event.doTriggerEvent(trigger, this);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void addTriggerableEvent(Trigger trigger, TriggerableEvent event){
		if(!events.containsKey(trigger)){
			events.put(trigger, new ArrayList<TriggerableEvent>());
		}
		events.get(trigger).add(event);
	}
	
	@SuppressWarnings("rawtypes")
	public Map<Trigger, List<TriggerableEvent>> getTriggerableEvents() {
		return events;
	}
//	@SuppressWarnings("rawtypes")
//	public void removeTriggerableEvent(TriggerableEvent event){
//		events.remove(event);
//	}
}
