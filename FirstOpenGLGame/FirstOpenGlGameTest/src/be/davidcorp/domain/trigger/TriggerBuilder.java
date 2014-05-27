package be.davidcorp.domain.trigger;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.trigger.triggerableEvents.TriggerableEvent;

public class TriggerBuilder {

	private Sprite source;
	private int ID;
	@SuppressWarnings("rawtypes")
	private  Map<Triggerable, List<TriggerableEvent>> triggerablesEventsMap = Maps.newHashMap();
	private TriggerWhen triggerWhen;
	
	private TriggerBuilder(){}
	
	public static TriggerBuilder aTrigger(){
		return new TriggerBuilder();
	}
	
	@SuppressWarnings("rawtypes")
	public Trigger build(){
		Trigger trigger = new Trigger(source, triggerWhen);
		trigger.setID(ID);
		for (Entry<Triggerable, List<TriggerableEvent>> valueKeyPair : triggerablesEventsMap.entrySet()) {
			trigger.addTriggerable(valueKeyPair.getKey());
			for (TriggerableEvent event : valueKeyPair.getValue()) {
				valueKeyPair.getKey().addTriggerableEvent(trigger, event);
			}
		}
		return trigger;
	}
	
	public TriggerBuilder withID(int id){
		this.ID = id;
		return this;
	}
	
	public TriggerBuilder triggeredWhen(TriggerWhen triggerWhen){
		this.triggerWhen = triggerWhen;
		return this;
	}
	
	public TriggerBuilder withSource(Sprite source){
		this.source = source;
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public TriggerBuilder withAnotherTriggerable(Triggerable triggerable, TriggerableEvent... events){
		triggerablesEventsMap.put(triggerable, Lists.newArrayList(events));
		return this;
	}
}
