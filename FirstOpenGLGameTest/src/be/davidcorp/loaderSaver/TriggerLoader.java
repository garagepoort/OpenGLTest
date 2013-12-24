package be.davidcorp.loaderSaver;

import static be.davidcorp.domain.trigger.TriggerManager.createTriggerOnSprite;
import static be.davidcorp.domain.trigger.TriggerableEventFactory.createTriggerableEvent;
import static com.google.common.collect.Maps.newHashMap;
import static java.lang.Integer.parseInt;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import be.davidcorp.FileUtility;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.trigger.Trigger;
import be.davidcorp.domain.trigger.TriggerWhen;
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
	private FileUtility fileUtility = new FileUtility();

	public void loadTriggers(File file) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(fileUtility.getFileContent(file));
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
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (scanner != null)
				scanner.close();
		}
	}

	@SuppressWarnings({"rawtypes"})
	private Trigger createTrigger(Map<String, String> triggerProperties) {
		int TRIGGERID = parseInt(triggerProperties.get(ID));
		int SPRITEID = parseInt(triggerProperties.get(SPRITE));
		int TRIGGERABLEID = parseInt(triggerProperties.get(TRIGGERABLE));


		Sprite sprite = defaultSpriteRepository.getSprite(SPRITEID);
		Sprite triggerable = defaultSpriteRepository.getSprite(TRIGGERABLEID);
		TriggerWhen triggerWhen = TriggerWhen.valueOf(triggerProperties.get(TRIGGERWHEN));
		TriggerableEventType eventType = TriggerableEventType.valueOf(triggerProperties.get(EVENT));

		TriggerableEvent event = createTriggerableEvent(eventType);
		Trigger createdTrigger = createTriggerOnSprite(TRIGGERID, triggerWhen, triggerable, event, sprite);
		return createdTrigger;
	}

	public void setDefaultSpriteRepository(DefaultSpriteRepository defaultSpriteRepository) {
		this.defaultSpriteRepository = defaultSpriteRepository;
	}

	/*
	 * Only for testing
	 */
	public void setFileUtility(FileUtility fileUtility) {
		this.fileUtility = fileUtility;
	}
}
