package views.hud.views;

import views.renderers.HUDManager;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import engine.Values;
import engine.utils.graphics.AnimationRegions;

class ScoreView extends GameSubView {

	private Label points;
	private AnimationRegions crystal;

	public ScoreView(Skin skin, HUDManager hudManager) {
		super(skin, hudManager);
		initiate();
	}

	@Override
	public void initiate() {
		crystal = new AnimationRegions(skin.get("green", TextureRegion.class), 8, 1, 1 / 10f);
		points = new Label("0", skin, "nice-style-default") {
			private final float duration = 0.7f;
			private float time = 0, alpha;
			private int lastScore = 0, actualScore, visualScore;
			public void act(float delta) {
				super.act(delta);
				actualScore = hudManager.getListener().getPlayerScore();
				if (actualScore > lastScore) {
					if (time < duration) {
						alpha = Math.min(1, time / duration);
						visualScore = MathUtils.round(Interpolation.linear
								.apply(lastScore, actualScore, alpha));
						setText("" + visualScore);
						time += delta;
					} else {
						lastScore = actualScore;
						time = 0;
					}
				}
			}
		};
		Vector2 position = new Vector2(10, Values.SCREEN_HEIGHT
				- Values.Coin_Height - 15);
		crystal.setPosition(10, Values.SCREEN_HEIGHT - Values.Coin_Height - 10);
		points.setOrigin(Align.bottomLeft);
		points.setFontScale(Values.Scalar_Width*1.1f, Values.Scalar_Height*1.1f);
		add(crystal).width(Values.Coin_Width).height(Values.Coin_Height)
				.padRight(15);
		add(points);
		pack();
		setPosition(position.x, position.y);
	}
	
	public AnimationRegions getCrystal() {
		return crystal;
	}
}
