package be.davidcorp.domain.mission;

import static be.davidcorp.domain.trigger.TriggerBuilder.aTrigger;
import static be.davidcorp.domain.trigger.TriggerWhen.ONUSE;
import be.davidcorp.domain.mission.acceptionCriteria.AcceptationCriteria;
import be.davidcorp.domain.mission.acceptionCriteria.AcceptationCriteriaHandler;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.domain.trigger.triggerableEvents.EndMissionEvent;
import be.davidcorp.repository.DefaultSpriteRepository;
import be.davidcorp.repository.SpriteRepository;

public class MissionManager {

	//TODO david remove created mission
	private static SpriteRepository spriteRepository = DefaultSpriteRepository.getInstance();
	
	public static void createFirstMission(){
		AcceptationCriteriaHandler acceptationCriteriaHandler = new AcceptationCriteriaHandler()
			.withCriteria(new LightMissionAcceptationCriteria());
		
		Mission mission = new Mission("Turn the light on. (Press 'E' near the wall)", acceptationCriteriaHandler);
//		Mission healthMission = new Mission("Find 4 healthpotions", new AcceptationCriteriaHandler().withCriteria(new FindHealthCriteria()));
		ConstructionSprite constructionSprite = (ConstructionSprite) spriteRepository.getSprite(1);
		aTrigger()
			.withID(8)
			.triggeredWhen(ONUSE)
			.withSource(constructionSprite)
			.withAnotherTriggerable(mission, new EndMissionEvent())
//			.withAnotherTriggerable(healthMission, new BeginMissionEvent())
			.build();
		
		
		PlayerManager.getCurrentPlayer().setCurrentMission(mission);
	}
	
	private static class LightMissionAcceptationCriteria implements AcceptationCriteria {
		
		@Override
		public boolean isCriteriaOK() {
			Light sprite = (Light) spriteRepository.getSprite(6);
			return sprite.isLightOn();
		}
	}
}
