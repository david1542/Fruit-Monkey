package engine;

import views.renderers.LoadingRenderer;
import views.screens.ScreenHandler;
import views.screens.ScreenType;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import engine.interfaces.GlobalListener;
import engine.utils.accessors.ActorAccessor;
import engine.utils.accessors.MonkeyAccessor;
import engine.utils.general.AssetsLoader;
import engine.utils.general.MediaPlayer;
import entities.Monkey;

/**
 * Created by David Lasry 10/23/14
 */
public class MonkeyRun extends Game {

	private TweenManager manager;
	private LoadingRenderer loadingRenderer;
	private GlobalGameListener gameListener;
	private Stage stage;

	@Override
	public void create() {
		Values.initiate();
		AssetsLoader.getInstance().reload();
		stage = new Stage(new StretchViewport(Values.SCREEN_WIDTH,
				Values.SCREEN_HEIGHT));
		loadingRenderer = new LoadingRenderer();
		manager = new TweenManager();
		stage.addActor(loadingRenderer);
		gameListener = new GlobalGameListener();
		ScreenHandler.getInstance().initialize(this);
		ScreenHandler.getInstance().show(ScreenType.MAIN_MENU);
		// Tween Engine Accessors:
		Tween.registerAccessor(Monkey.class, new MonkeyAccessor());
		Tween.registerAccessor(Actor.class, new ActorAccessor());
	}

	@Override
	public void render() {
		if (AssetsLoader.getInstance().getScreenToInform() != null) {
			if (!AssetsLoader.getInstance().update()) {
				stage.act(Gdx.graphics.getDeltaTime());
				stage.draw();
				loadingRenderer.setProgress(AssetsLoader.getInstance().getProgress() * 100);
				return;
			} else {
				loadingRenderer.clear();
				AssetsLoader.getInstance().getScreenToInform().assetsLoaded();
				AssetsLoader.getInstance().resetScreenToInform();
			}
		}
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		manager.update(Gdx.graphics.getDeltaTime());
	}
	@Override
	public void resume() {
		super.resume();
	}

	public TweenManager getTweenManager() {
		return manager;
	}

	public GlobalGameListener getListener() {
		return gameListener;
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetsLoader.getInstance().dispose();
		ScreenHandler.getInstance().dispose();
	}

	public class GlobalGameListener implements GlobalListener {

		@Override
		public void toggleSound() {
			if (Settings.getInstance().isSoundOn())
				MediaPlayer.muteSounds();
			else
				MediaPlayer.enableSounds();
		}

		@Override
		public void toggleMusic() {
			if (Settings.getInstance().isMusicOn())
				MediaPlayer.muteMusic();
		    else
				MediaPlayer.enableMusic();
		}
	}
	
}
