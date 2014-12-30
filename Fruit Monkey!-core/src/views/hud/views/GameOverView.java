package views.hud.views;

import views.renderers.HUDManager;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import engine.Values;
import engine.utils.general.AssetsLoader;
import engine.utils.general.MediaPlayer;

public class GameOverView extends GameSubView {

	private Window gameOverWindow;
	private Label actualScore, bestScore;
	private ImageButton retry, quit;
	private Table buttons;
	private boolean count = false;
	private final int oldBestScore = hudManager.getListener().getBestScore();
	private Sound sound;

	public GameOverView(Skin skin, HUDManager hudManager) {
		super(skin, hudManager);
		initiate();
	}

	@Override
	public void initiate() {
		initiateLabels();
		initiateWindow();
		sound = AssetsLoader.getInstance().getAsset("sfx/highscore.wav", Sound.class);
	}

	private void initiateWindow() {
		gameOverWindow = new Window("Game Over", skin, "default");
		gameOverWindow.setVisible(true);
		gameOverWindow.setSize(Values.Window_Width, Values.Window_Height);
		gameOverWindow.align(Align.center);
		gameOverWindow.setPosition(0 - gameOverWindow.getWidth(),
				Values.SCREEN_HEIGHT / 2 - gameOverWindow.getHeight() / 2);
		hudManager.slideFromLeft(
				gameOverWindow,
				new Vector2(0 - gameOverWindow.getWidth(),
						Values.SCREEN_HEIGHT / 2),
				new Vector2(Values.SCREEN_WIDTH / 2 - gameOverWindow.getWidth()
						/ 2, Values.SCREEN_HEIGHT / 2
						- gameOverWindow.getHeight() / 2), 0.3f, false);
		retry = new ImageButton(skin.getDrawable("replay"));
		retry.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				hudManager.getListener().restartGame();
				remove();
			}
		});
		quit = new ImageButton(skin.getDrawable("menue"));
		quit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				hudManager.getListener().goToMainMenu();
				remove();
			}
		});
		gameOverWindow.align(Align.center);
		gameOverWindow.add(actualScore).row();
		gameOverWindow.add(bestScore).row();
		buttons = new Table();
		buttons.align(Align.center);
		buttons.add(retry)
		.size(Values.HUD_Bar_Image_Width * 1.3f,
				Values.HUD_Bar_Image_Height * 1.3f).pad(5);
		buttons.add(quit)
		.size(Values.HUD_Bar_Image_Width * 1.3f,
				Values.HUD_Bar_Image_Height * 1.3f).pad(5);
		gameOverWindow.add(buttons);
		addActor(gameOverWindow);
	}

	private void initiateLabels() {
		actualScore = new Label("Your score: 0", skin, "window-style") {
			private final float duration = 1f;
			private float time = 0, alpha;
			private int lastScore = 0, actualScore = 0, visualScore;

			public void act(float delta) {
				super.act(delta);
				if(actualScore == 0)
					actualScore = hudManager.getListener().getPlayerScore();
				if (actualScore > lastScore) {
					if (time < duration) {
						alpha = Math.min(1, time / duration);
						visualScore = MathUtils.round(Interpolation.linear
								.apply(lastScore, actualScore, alpha));
						setText("Your score: " + visualScore);
						time += delta;
					} else {
						lastScore = actualScore;
						if(visualScore < actualScore)
							setText("Your score" + (visualScore+1));
						time = 0;
						count = true;
					}
				}
			}
		};
		bestScore = new Label("Best score: " + oldBestScore, skin,
				"window-style") {
			private final float duration = 1f;
			private float time = 0, alpha;
			private int lastScore = oldBestScore, actualScore, visualScore;

			public void act(float delta) {
				super.act(delta);
				if (count) {
					if(actualScore == 0)
						actualScore = hudManager.getListener().getPlayerScore();
					if (actualScore > lastScore) {
						if (time < duration) {
							alpha = Math.min(1, time / duration);
							visualScore = MathUtils.round(Interpolation.linear
									.apply(lastScore, actualScore, alpha));
							setText("Best score: " + visualScore);
							time += delta;
						} else {
							lastScore = actualScore;
							if(visualScore < actualScore)
								setText("Your score" + (visualScore+1));
							time = 0;
							if (oldBestScore < actualScore) {
								hudManager.showMessage("New Best Score!",
										new Vector2(gameOverWindow.getX(), gameOverWindow.getY()
												+ Values.Window_Height / 2),
										Color.ORANGE);
								MediaPlayer.playSound(sound);
							}
						}
					}
				}
			}
		};
		actualScore.setFontScale(Values.Scalar_Width*0.8f, Values.Scalar_Height*0.8f);
		actualScore.setAlignment(Align.center);
		bestScore.setFontScale(Values.Scalar_Width*0.8f, Values.Scalar_Height*0.8f);
		bestScore.setAlignment(Align.center);
	}
	
	public Window getWindow() {
		return gameOverWindow;
	}
}
