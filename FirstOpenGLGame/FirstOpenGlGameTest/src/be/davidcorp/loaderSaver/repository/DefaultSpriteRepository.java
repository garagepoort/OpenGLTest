package be.davidcorp.loaderSaver.repository;

import java.util.List;
import java.util.Map;

import be.davidcorp.domain.sprite.Sprite;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
	
	public List<Sprite> getAllSprites(){
		List<Sprite> sprites = Lists.newArrayList();
		
		sprites.addAll(enemyRepository.getAllSprites());
		sprites.addAll(weaponRepository.getAllSprites());
		sprites.addAll(constructionSpriteRepository.getAllSprites());
		sprites.addAll(lightRepository.getAllSprites());
		sprites.addAll(itemRepository.getAllSprites());
		return sprites;
	}
	
	public Map<Integer, Sprite> getAllSpritesMap(){
		Map<Integer, Sprite> sprites = Maps.newHashMap();
		
		sprites.putAll(EnemyRepository.enemies);
		sprites.putAll(WeaponRepository.weapons);
		sprites.putAll(ConstructionSpriteRepository.constructionSprites);
		sprites.putAll(LightRepository.lights);
		sprites.putAll(ItemRepository.items);
		return sprites;
	}
	
}
