package views.screens;

import views.GraphicsHelper;
import views.renderers.GameRenderer;
import views.renderers.HUDManager;
import engine.GameWorld;
import engine.Settings;
import engine.Values;
import engine.interfaces.GameListener;
import engine.managers.general.LogicManager;
import engine.managers.general.PlayerDataManager;
import engine.utils.EntityFactory;
import engine.utils.general.AssetsLoader;
import engine.utils.general.GameInputHandler;
import engine.utils.general.MediaPlayer;
import engine.utils.general.MotionController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/* Created by David Lasry : 10/25/14 */
public class GameScreen extends ScreenWrapper {

	private GameWorld gameWorld;
	private HUDManager hudManager;
	private GameRenderer renderer;
	private LocalGameListener localGameListener;
	private Stage gameStage;
	private Music gameMusic;
	private Sound gamePoints;
	private MotionController motionManager;
	private InputMultiplexer inputs;

	public GameScreen() {
		super();
		AssetsLoader.getInstance().loadAsstes(2, this);
		gameStage = new Stage(new StretchViewport(Values.SCREEN_WIDTH,
				Values.SCREEN_HEIGHT));
		gameWorld = new GameWorld();
		localGameListener = new LocalGameListener();
		motionManager = new MotionController();
		// Set listeners
		gameWorld.getCollisionHandler().setListener(localGameListener);
		gameWorld.getScrollManager().setListener(localGameListener);
		gameWorld.getLogicHelper().setListener(localGameListener);
		camera = (OrthographicCamera) gameStage.getCamera();
		inputs = new InputMultiplexer();
		inputs.addProcessor(gameStage);
		inputs.addProcessor(new GameInputHandler(gameWorld.getMonkeyManager(),
				camera, localGameListener));
		Gdx.input.setInputProcessor(inputs);
		gameStage.addActor(gameWorld);
	}

	@Override
	public void render(float delta) {
		switch (state) {
		case RUN:
			gameStage.act(delta * motionManager.getRate());
			motionManager.update(delta);
			gameStage.draw();
			break;
		case PAUSE:
			gameStage.draw();
			hudManager.act(delta);
			break;
		case GAME_OVER:
			gameStage.draw();
			hudManager.act(delta);
			break;
		}
	}

	// Callback method that is called when the assets are loaded
	@Override
	public void assetsLoaded() {
		super.assetsLoaded();
		renderer = new GameRenderer(gameWorld);
		hudManager = new HUDManager(localGameListener);
		gameMusic = AssetsLoader.getInstance().getAsset("music/game.mp3",
				Music.class);
		gamePoints = AssetsLoader.getInstance().getAsset("sfx/points.wav",
				Sound.class);
		gameStage.addActor(renderer);
		gameStage.addActor(hudManager);
		MediaPlayer.playMusic(gameMusic, true);
		if (Settings.getInstance().isFirstTime()) {
			hudManager.firstTime(gameWorld.getMonkeyManager().getMonkey());
			gameWorld.getScrollManager().stop();
			localGameListener.disableMonkeyInput();
			Settings.getInstance().setFirstTime(false);
			Settings.getInstance().save();
		} else {
			GraphicsHelper.tweenMonkeyMovement(gameWorld.getMonkeyManager()
					.getMonkey(), Values.SCREEN_WIDTH / 2, gameWorld
					.getMonkeyManager().getMonkey().getY(), 2f);
		}
	}

	@Override
	public void resize(int width, int height) {
		gameStage.getViewport().update(width, height);
	}

	public HUDManager getHudManager() {
		return hudManager;
	}

	public GameWorld getWorld() {
		return gameWorld;
	}

	@Override
	public void resume() {
		if (!hudManager.isPauseWindowVisible() && state != State.GAME_OVER)
			hudManager.spawnPauseWindow();
	}

	@Override
	public void pause() {
		if (state != State.GAME_OVER)
			super.pause();
	}

	public void gameOver() {
		state = State.GAME_OVER;
	}

	public State getGameState() {
		return state;
	}

	@Override
	public void dispose() {
		gameWorld.clear();
		gameStage.dispose();
		AssetsLoader.getInstance().unload(2);
		EntityFactory.getInstance().getPools().clearAll();
		MediaPlayer.clear();
	}

	/* Created by David Lasry 10/23/14 */
	public class LocalGameListener implements GameListener {

		private PlayerDataManager playerDataManager;
		private LogicManager logicManager;

		public LocalGameListener() {
			this.playerDataManager = getWorld().getDataManager();
			this.logicManager = getWorld().getLogicHelper();
		}

		public int getPlayerScore() {
			return playerDataManager.getPlayerScore();
		}

		public int getPlayerStrikes() {
			return playerDataManager.getPlayerStrikes();
		}

		@Override
		public int getBestScore() {
			return playerDataManager.getBestScore();
		}

		@Override
		public void setBestScore(int bestScore) {
			Settings.getInstance().setBestScore(bestScore);
			Settings.getInstance().save();
		}

		@Override
		public void determineBestScore() {
			logicManager.determineBestScore();
		}

		// This class is notified when the monkey is dead(game over)
		@Override
		public void notifiyGameOver() {
			Sound hit = AssetsLoader.getInstance().getAsset("sfx/punch.mp3",
					Sound.class);
			Sound monkey = AssetsLoader.getInstance().getAsset(
					"sfx/monkey.wav", Sound.class);
			MediaPlayer.removeMusic(gameMusic);
			MediaPlayer.playSound(hit);
			MediaPlayer.playSound(monkey);
			getHudManager().gameOverEffect();
			gameOver();
			getWorld().endGame();
			getHudManager().hideBar();
			getHudManager().tweenMonkeyDeath(
					getWorld().getMonkeyManager().getMonkey());
			determineBestScore();
		}

		@Override
		public void notifyScoreIncreasment(Vector2 position, int points) {
			playerDataManager.increaseScore(position, points);
			getWorld().getLogicHelper().pointsLogic(
					playerDataManager.getPlayerScore(), points, position);
			MediaPlayer.playSound(gamePoints);
		}

		@Override
		public void notifyStrikesDecreasment() {
			playerDataManager.decreaseStrikes();
			logicManager.strikesLogic();
		}

		@Override
		public State getState() {
			return getGameState();
		}

		@Override
		public void setSlowMotionOn(float duration) {
			motionManager.setSlowMotion(0.2f, duration);
		}

		@Override
		public void pauseGame() {
			pause();
		}

		@Override
		public void resumeGame() {
			state = State.RUN;
			for (Actor actor : getHudManager().getChildren()) {
				actor.setTouchable(Touchable.enabled);
			}
		}

		@Override
		public void goToMainMenu() {
			ScreenHandler.getInstance().show(ScreenType.MAIN_MENU);
			ScreenHandler.getInstance().dispose(ScreenType.GAME);
		}

		@Override
		public void restartGame() {
			ScreenHandler.getInstance().dispose(ScreenType.GAME);
			ScreenHandler.getInstance().show(ScreenType.GAME);
		}

		@Override
		public void disableMonkeyInput() {
			for (InputProcessor processor : GameScreen.this.inputs
					.getProcessors()) {
				if (processor instanceof GameInputHandler) {
					((GameInputHandler) processor).enable(false);
					return;
				}
			}
		}

		@Override
		public void enableMonkeyInput() {
			getWorld().getScrollManager().resume();
			for (InputProcessor processor : GameScreen.this.inputs
					.getProcessors()) {
				if (processor instanceof GameInputHandler) {
					((GameInputHandler) processor).enable(true);
					return;
				}
			}
		}
	}
}