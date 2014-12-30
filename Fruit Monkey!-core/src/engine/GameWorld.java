package engine;

import com.badlogic.gdx.scenes.scene2d.Group;

import engine.managers.GameScrollManager;
import engine.managers.general.LogicManager;
import engine.managers.general.PlayerDataManager;
import engine.managers.objects.MonkeyManager;

/* Created by David Lasry : 10/25/14 */
public class GameWorld extends Group{

	/*
	 * GameWorld class Takes care of all the objects in the game. From creating
	 * them to moving them according to game logics
	 */
	private GameScrollManager gameScrollManager;
	private MonkeyManager monkeyManager;
	private PlayerDataManager playerDataManager;
	private BodyCollisionHandler collisionHandler;
	private LogicManager logicManager;

	public GameWorld() {
		gameScrollManager = new GameScrollManager();
		playerDataManager = new PlayerDataManager();
		monkeyManager = new MonkeyManager(gameScrollManager);
		collisionHandler = new BodyCollisionHandler(monkeyManager.getMonkey(),
				gameScrollManager);
		logicManager = new LogicManager(gameScrollManager, playerDataManager);
		addActor(gameScrollManager);
		addActor(collisionHandler);
	}

	public BodyCollisionHandler getCollisionHandler() {
		return collisionHandler;
	}

	public GameScrollManager getScrollManager() {
		return gameScrollManager;
	}

	public MonkeyManager getMonkeyManager() {
		return monkeyManager;
	}

	public PlayerDataManager getDataManager() {
		return playerDataManager;
	}

	public LogicManager getLogicHelper() {
		return logicManager;
	}
	
	public void endGame() {
		monkeyManager.killMonkey();
		gameScrollManager.stop();
	}
	
	public void clear() {
		gameScrollManager.clear();
		monkeyManager.clear();
	}
}
