package views.screens;

import views.renderers.MenuRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import engine.Values;
import engine.utils.general.AssetsLoader;
import engine.utils.general.MediaPlayer;

/* Created by David Lasry */
public class MenuScreen extends ScreenWrapper {

	private Stage stage;
	private Music music;
	private MenuRenderer renderer;

	public MenuScreen() {
		super();
		AssetsLoader.getInstance().loadAsstes(1, this);
		stage = new Stage(new StretchViewport(Values.SCREEN_WIDTH,
				Values.SCREEN_HEIGHT));
		camera = (OrthographicCamera) stage.getCamera();
		batch = (SpriteBatch) stage.getBatch();
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		switch (state) {
		case RUN:
			stage.act(delta);
			stage.draw();
			break;
		case PAUSE:
			break;
		case GAME_OVER:
			break;
		}
	}

	@Override
	public void pause() {
		super.pause();
		MediaPlayer.muteMusic();
	}

	@Override
	public void resume() {
		super.resume();
		MediaPlayer.enableMusic();
	}

	@Override
	public void assetsLoaded() {
		super.assetsLoaded();
		renderer = new MenuRenderer(stage, camera, batch);
		music = AssetsLoader.getInstance().getAsset("music/menu.mp3",
				Music.class);
		stage.addActor(renderer);
		MediaPlayer.playMusic(music, true);
	}

	@Override
	public void hide() {
		AssetsLoader.getInstance().unload(1);
	}

	@Override
	public void dispose() {
		renderer.dispose();
		stage.dispose();
		MediaPlayer.clear();
	}
}
