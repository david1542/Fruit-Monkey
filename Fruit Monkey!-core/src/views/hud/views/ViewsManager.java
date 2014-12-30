package views.hud.views;

import views.GraphicsHelper;
import views.renderers.HUDManager;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import engine.Values;

public class ViewsManager extends Table {

	private Skin skin;
	private HUDManager hudManager;
	// Different Views in the Bar
	private ScoreView scoreView;
	private ImageButton pauseButton;
	private StrikesView strikesView;
	private PauseView pause;
	
	
	public ViewsManager(Skin skin, HUDManager hudManager) {
		this.skin = skin;
		this.hudManager = hudManager;
		initiate();
	}

	private void initiate() {
		Vector2 position = new Vector2();
		pauseButton = new ImageButton(skin.getDrawable("pause"));
		pauseButton.setSize(Values.HUD_Bar_Image_Width,
				Values.HUD_Bar_Image_Height);
		position.set(Values.SCREEN_WIDTH - Values.HUD_Bar_Image_Width,
				Values.SCREEN_HEIGHT - Values.HUD_Bar_Image_Height);
		pauseButton.setPosition(position.x, position.y);
		pauseButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				spawnPauseWindow();
				setTouchable(Touchable.disabled);
			}
		});
		scoreView = new ScoreView(skin, hudManager);
		strikesView = new StrikesView(skin, hudManager);
		addActor(pauseButton);
		addActor(scoreView);
		addActor(strikesView);
	}

	public void spawnPauseWindow() {
		pause = new PauseView(skin, hudManager);
		pause.getPauseWindow().setVisible(true);
		pause.align(Align.center);
		addActor(pause);
		hudManager.slideFromLeft(pause.getPauseWindow(), new Vector2(0 - pause
				.getPauseWindow().getWidth(), Values.SCREEN_HEIGHT / 2
				- pause.getPauseWindow().getHeight() / 2),
				new Vector2(Values.SCREEN_WIDTH / 2
						- pause.getPauseWindow().getWidth() / 2,
						Values.SCREEN_HEIGHT / 2
								- pause.getPauseWindow().getHeight() / 2),
				0.3f, false);
		hudManager.getListener().pauseGame();
	}

	public void spawnGameOverWindow() {
		GameOverView gameOver = new GameOverView(skin, hudManager);
		hudManager.slideFromLeft(gameOver.getWindow(), new Vector2(0 - gameOver
				.getWindow().getWidth(), Values.SCREEN_HEIGHT / 2),
				new Vector2(Values.SCREEN_WIDTH / 2
						- gameOver.getWindow().getWidth() / 2,
						Values.SCREEN_HEIGHT / 2
								- gameOver.getWindow().getHeight() / 2), 0.3f,
				false);
	}

	public void scaleCrystal(float duration) {
		GraphicsHelper.scale(scoreView.getCrystal(), 1.5f, duration);
	}

	public void changeCrystal(String color) {
		scoreView.getCrystal().changeTexture(
				skin.get(color, TextureRegion.class), 8, 1, 1 / 10f);
	}

	public boolean isPauseWindowVisible() {
		if(pause != null)
			return pause.isVisible();
		return false;
	}

}
