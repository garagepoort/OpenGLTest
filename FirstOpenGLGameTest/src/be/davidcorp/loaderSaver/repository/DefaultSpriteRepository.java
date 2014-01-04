package be.davidcorp.loaderSaver.repository;

import be.davidcorp.domain.sprite.Sprite;

public class DefaultSpriteRepository {

	private static EnemyRepository enemyRepository = new EnemyRepository();
	private static WeaponRepository weaponRepository = new WeaponRepository();
	private static ItemRepository itemRepository= new ItemRepository();
	private static ConstructionSpriteRepository constructionSpriteRepository = new ConstructionSpriteRepository();
	private static LightRepository lightRepository = new LightRepository();
	private static MiscRepository miscRepository = new MiscRepository();
	
	public Sprite getSprite(int id){
		if(enemyRepository.getSprite(id) != null) return enemyRepository.getSprite(id);
		if(weaponRepository.getSprite(id) != null) return weaponRepository.getSprite(id);
		if(itemRepository.getSprite(id) != null) return itemRepository.getSprite(id);
		if(constructionSpriteRepository.getSprite(id) != null) return constructionSpriteRepository.getSprite(id);
		if(lightRepository.getSprite(id) != null) return lightRepository.getSprite(id);
		if(miscRepository.getSprite(id) != null) return miscRepository.getSprite(id);
		throw new RuntimeException("No sprite exists with id: [" + id + "]");
	}
	
	public void emptyAllRepositories(){
		enemyRepository.emptyRepository();
		weaponRepository.emptyRepository();
		itemRepository.emptyRepository();
		constructionSpriteRepository.emptyRepository();
		lightRepository.emptyRepository();
	}
	
}
