package be.davidcorp.database.mapper.spriteToString;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.trigger.Trigger;
import be.davidcorp.domain.trigger.Triggerable;
import be.davidcorp.domain.trigger.triggerableEvents.TriggerableEvent;

public class TriggerToStringMapper {
	
	@SuppressWarnings("rawtypes")
	public String mapTriggerToString(Trigger trigger){
		String result = "TRIGGER\n"
				+ "ID:" + trigger.getID() + "\n"
				+ "TRIGGERWHEN:" + trigger.triggerWhen + "\n"
				+ "SOURCE:" + trigger.getSource().getID() + "\n";
				for(Triggerable triggerable : trigger.getTriggerables()){
					result += "TRIGGERABLE:" + ((Sprite) triggerable).getID() + "\n";
					for (TriggerableEvent event : triggerable.getTriggerableEvents().get(trigger)) {
						result += "EVENT:" + event.getType();
					}
				}
				result += "END";
		return result;
	}
}
