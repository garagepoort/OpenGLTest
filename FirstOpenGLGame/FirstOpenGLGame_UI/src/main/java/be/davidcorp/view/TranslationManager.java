package main.java.be.davidcorp.view;

import static main.java.be.davidcorp.view.game.GameLoop.HEIGHT;
import static main.java.be.davidcorp.view.game.GameLoop.WIDTH;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import main.java.be.davidcorp.view.game.GameLoop;

public class TranslationManager {

	private static PlayerFacade playerFacade = new PlayerFacade();
	private static GameFieldFacade gameFieldFacade = new GameFieldFacade();

	private static float playerTranslationX;
	private static float playerTranslationY;
	private static float gameFieldXTranslation;
	private static float gameFieldYTranslation;
	
	private TranslationManager(){}
	
	public static void initializeBeginTranslation(){
		if (playerFacade.getX() < halfOfScreenWidth()) {
			playerTranslationX = -(halfOfScreenWidth() - playerFacade.getX());
		}
		if (playerFacade.getX() >= halfOfScreenWidth() && playerFacade.getX() <= gameFieldFacade.getWidthOfGamefield() - halfOfScreenWidth()) {
			gameFieldXTranslation = halfOfScreenWidth() - playerFacade.getX();
		}

		if (playerFacade.getX() > gameFieldFacade.getWidthOfGamefield() - halfOfScreenWidth()) {
			gameFieldXTranslation = -gameFieldFacade.getWidthOfGamefield() + GameLoop.WIDTH;
			playerTranslationX = playerFacade.getX() - (gameFieldFacade.getWidthOfGamefield() - halfOfScreenWidth());
		}
		if (playerFacade.getY() < halfOfScreenHeight()) {
			playerTranslationY = -(halfOfScreenHeight() - playerFacade.getY());
		}

		if (playerFacade.getY() > halfOfScreenHeight() && playerFacade.getY() <= gameFieldFacade.getHeightOfGamefield() - halfOfScreenHeight()) {
			gameFieldYTranslation = halfOfScreenHeight() - playerFacade.getY();
		}

		if (playerFacade.getY() > gameFieldFacade.getHeightOfGamefield() - halfOfScreenHeight()) {
			gameFieldYTranslation = -gameFieldFacade.getHeightOfGamefield() + HEIGHT;
			playerTranslationY = playerFacade.getY() - (gameFieldFacade.getHeightOfGamefield() - halfOfScreenHeight());
		}
	}


	public static void initializeDownTranslation() {
		if (gameFieldYTranslation >= 0 || playerTranslationY > 0) {
			playerTranslationY -= playerFacade.getSpeed() * GameLoop.getSecondsMovedInGame();
		} else {
			gameFieldYTranslation += playerFacade.getSpeed() * GameLoop.getSecondsMovedInGame();
		}
	}
	
	public static void initializeRightTranslation() {
		if (gameFieldXTranslation <= -gameFieldFacade.getWidthOfGamefield() + WIDTH || playerTranslationX < 0) {
			playerTranslationX += playerFacade.getSpeed() * GameLoop.getSecondsMovedInGame();
		} else {
			gameFieldXTranslation -= playerFacade.getSpeed() * GameLoop.getSecondsMovedInGame();
		}
	}
	
	public static void initializeUpTranslation(){
		if (gameFieldYTranslation <= -gameFieldFacade.getHeightOfGamefield() + HEIGHT || playerTranslationY < 0) {
			playerTranslationY += playerFacade.getSpeed() * GameLoop.getSecondsMovedInGame();
		} else {
			gameFieldYTranslation -= playerFacade.getSpeed() * GameLoop.getSecondsMovedInGame();
		}
	}
	
	public static void initializeLeftTranslation(){
		if (gameFieldXTranslation >= 0 || playerTranslationX <= 0) {
			playerTranslationX -= playerFacade.getSpeed() * GameLoop.getSecondsMovedInGame();
		} else {
			gameFieldXTranslation += playerFacade.getSpeed() * GameLoop.getSecondsMovedInGame();
		}
	}
	
	public static void initializeDownTranslationForGameCreator() {
		if (gameFieldYTranslation <0) {
			gameFieldYTranslation += playerFacade.getSpeed() * GameLoop.getSecondsMovedInGame();
		}
	}
	
	public static void initializeRightTranslationForGameCreator() {
		if (gameFieldXTranslation > -gameFieldFacade.getWidthOfGamefield() + WIDTH) {
			gameFieldXTranslation -= playerFacade.getSpeed() * GameLoop.getSecondsMovedInGame();
		}
	}
	
	public static void initializeUpTranslationForGameCreator(){
		if (gameFieldYTranslation > -gameFieldFacade.getHeightOfGamefield() + HEIGHT) {
			gameFieldYTranslation -= playerFacade.getSpeed() * GameLoop.getSecondsMovedInGame();
		}
	}
	
	public static void initializeLeftTranslationForGameCreator(){
		if (gameFieldXTranslation < 0) {
			gameFieldXTranslation += playerFacade.getSpeed() * GameLoop.getSecondsMovedInGame();
		}
	}

	public static float getPlayerTranslationX() {
		return playerTranslationX;
	}

	public static float getPlayerTranslationY() {
		return playerTranslationY;
	}

	public static float getGameFieldXTranslation() {
		return gameFieldXTranslation;
	}

	public static float getGameFieldYTranslation() {
		return gameFieldYTranslation;
	}

	private static float halfOfScreenHeight() {
		return GameLoop.HEIGHT/2;
	}
	
	private static float halfOfScreenWidth() {
		return GameLoop.WIDTH/2;
	}
}
