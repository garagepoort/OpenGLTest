package be.davidcorp.domain.test.builder;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;

public class GamefieldTestBuilder {

	private String name = "GAMEFIELD";
	private int width = 1800;
	private int height = 1800;
	
	private List<Enemy> enemies = newArrayList();
	private List<Item> items = newArrayList();
	private List<Light> lights = newArrayList();
	private List<ConstructionSprite> constructionSprites = newArrayList();
	private int id;

	public Gamefield build(){
		Gamefield gamefield = new Gamefield(name, width, height);
		gamefield.setID(id);
		for (Enemy enemy : enemies) {
			gamefield.addEnemyToWorld(enemy);
		}
		for (ConstructionSprite constructionSprite : constructionSprites) {
			gamefield.addConstructionItem(constructionSprite);
		}
		for (Light light : lights) {
			gamefield.addLight(light);
		}
		for (Item item : items) {
			gamefield.addGroundItem(item);
		}
		return gamefield;
	}
	
	public GamefieldTestBuilder withName(String name){
		this.name = name;
		return this;
	}
	
	public GamefieldTestBuilder withEnemy(Enemy enemy) {
		enemies.add(enemy);
		return this;
	}
	
	public GamefieldTestBuilder withConstructionSprite(ConstructionSprite constructionSprite) {
		constructionSprites.add(constructionSprite);
		return this;
	}
	
	public GamefieldTestBuilder withLight(Light light) {
		lights.add(light);
		return this;
	}
	
	public GamefieldTestBuilder withGroundItem(Item item) {
		items.add(item);
		return this;
	}

	public GamefieldTestBuilder withID(int id) {
		this.id = id;
		return this;
	}
}