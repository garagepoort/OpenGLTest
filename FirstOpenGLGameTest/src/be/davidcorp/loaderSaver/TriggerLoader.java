package be.davidcorp.loaderSaver;

import static com.google.common.collect.Maps.newHashMap;
import static java.lang.Integer.parseInt;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.trigger.Trigger;
import be.davidcorp.domain.trigger.TriggerManager;
import be.davidcorp.domain.trigger.TriggerWhen;
import be.davidcorp.domain.trigger.TriggerableEventFactory;
import be.davidcorp.domain.trigger.triggerableEvents.TriggerableEvent;
import be.davidcorp.domain.trigger.triggerableEvents.TriggerableEventType;
import be.davidcorp.loaderSaver.repository.DefaultSpriteRepository;
import be.davidcorp.loaderSaver.repository.TriggerRepository;

public class TriggerLoader {

	private static final String EVENT = "EVENT";
	private static final String TRIGGERABLE = "TRIGGERABLE";
	private static final String SPRITE = "SPRITE";
	private static final String ID = "ID";
	private static final String TRIGGERWHEN = "TRIGGERWHEN";

	private DefaultSpriteRepository defaultSpriteRepository = new DefaultSpriteRepository();
	private TriggerRepository triggerRepository = new TriggerRepository();

	public void loadTriggers(String loadedTriggers) {
		Scanner scanner = new Scanner(loadedTriggers);
		try {
			HashMap<String, String> triggerProperties = newHashMap();
			while (scanner.hasNextLine()) {
				/* remove first trigger */
				scanner.nextLine();
				String next = scanner.nextLine();
				while (!next.equals("END")) {
					Scanner lineScanner = new Scanner(next);
					lineScanner.useDelimiter(":");
					triggerProperties.put(lineScanner.next(), lineScanner.next());
					next = scanner.nextLine();
					lineScanner.close();
				}
				triggerRepository.addTrigger(createTrigger(triggerProperties));
			}
		} finally {
			scanner.close();
		}
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private Trigger createTrigger(Map<String, String> triggerProperties) {
		Sprite triggerable = defaultSpriteRepository.getSprite(parseInt(triggerProperties.get(TRIGGERABLE)));
		TriggerableEvent event = TriggerableEventFactory.createTriggerableEvent(TriggerableEventType.valueOf(triggerProperties.get(EVENT)));

		Trigger createdTrigger = TriggerManager.createTrigger(parseInt(triggerProperties.get(ID)), TriggerWhen.valueOf(triggerProperties.get(TRIGGERWHEN)), triggerable, event);

		defaultSpriteRepository.getSprite(parseInt(triggerProperties.get(SPRITE))).addTrigger(createdTrigger);
		return createdTrigger;
	}

	public void setDefaultSpriteRepository(DefaultSpriteRepository defaultSpriteRepository) {
		this.defaultSpriteRepository = defaultSpriteRepository;
	}
}
