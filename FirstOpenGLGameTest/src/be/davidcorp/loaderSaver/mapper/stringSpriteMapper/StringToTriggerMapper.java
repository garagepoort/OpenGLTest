package be.davidcorp.loaderSaver.mapper.stringSpriteMapper;

import java.util.Scanner;

import be.davidcorp.domain.trigger.Trigger;
import be.davidcorp.loaderSaver.repository.DefaultSpriteRepository;

public class StringToTriggerMapper {

	public Trigger mapStringToTrigger(String triggerString) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(triggerString);
		while(scanner.hasNext()){
			Scanner lineScanner = new Scanner(scanner.next());
			
		}
		return null;
	}

	public void setDefaultSpriteRepository(DefaultSpriteRepository defaultSpriteRepository) {
		// TODO Auto-generated method stub
		
	}

}
