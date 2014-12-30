package views.renderers;

import views.GraphicsHelper;
import views.hud.ParticleEffectsManager;
import views.hud.views.ViewsManager;
import views.screens.GameScreen.LocalGameListener;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import engine.Values;
import engine.utils.general.AssetsLoader;
import entities.Monkey;

/**
 * HudManager class. Takes care of all the hud work in the game, such as: hud
 * bar, pause view, game over view, surprising messages, etc...
 * 
 * @author David
 * 
 */
public class HUDManager extends Renderer {

	private Skin skin;
	private Actor oneTimeActor;
	private ViewsManager viewManager;
	private ParticleEffectsManager particles;
	private LocalGameListener listener;

	public HUDManager(LocalGameListener listener) {
		this.listener = listener;
		initiate();
		addActor(particles);
		addActor(viewManager);
	}

	@Override
	public void initiate() {
		skin = AssetsLoader.getInstance()
				.getAsset("images/data/packs/skin.json", Skin.class);
		viewManager = new ViewsManager(skin, this);
		particles = new ParticleEffectsManager();
	}
	
	public void firstTime(final Monkey monkey) {
		final Label label = GraphicsHelper.createLabel("Collect all the \n falling fruits. \n"
				+ "Watch out from \n animals! \n Tap to start", Color.ORANGE, skin);
		label.setPosition(0-label.getWidth(), Values.SCREEN_HEIGHT/2);
		label.setOrigin(Align.center);
		label.setAlignment(Align.center);
		slideFromLeft(label, new Vector2(0-label.getWidth(), Values.SCREEN_HEIGHT/2), 
				new Vector2(Values.SCREEN_WIDTH/2-label.getWidth()/2, Values.SCREEN_HEIGHT/2), 2f, false);
		oneTimeActor = new Actor();
		oneTimeActor.setWidth(Values.SCREEN_WIDTH);
		oneTimeActor.setHeight(Values.SCREEN_HEIGHT);
		oneTimeActor.addCaptureListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GraphicsHelper.tweenMonkeyMovement(monkey, Values.SCREEN_WIDTH/2-monkey.getBody().width, monkey.getY(), 2f);
				viewManager.setVisible(true);
				viewManager.setTouchable(Touchable.enabled);
				HUDManager.this.setVisible(true);
				HUDManager.this.setTouchable(Touchable.enabled);
				listener.enableMonkeyInput();
				label.remove();
				oneTimeActor.remove();
			}
		});
		addActor(oneTimeActor);
		viewManager.setVisible(false);
		viewManager.setTouchable(Touchable.disabled);
	}

	public void showMessage(String msg, Vector2 position, Color color) {
		Label label = GraphicsHelper.createLabel(msg, color, skin);
		label.setColor(label.getColor().r, label.getColor().g,
				label.getColor().b, 0);
		if (position.x + label.getWidth() > Values.SCREEN_WIDTH)
			position.x -= label.getWidth() / 2;
		else if (position.y + label.getHeight() > Values.SCREEN_HEIGHT)
			position.y -= label.getHeight();
		addActor(label);
		GraphicsHelper.floatAndFade(label, position, 4f, true);
	}

	
	public void slideFromLeft(final Actor actor, Vector2 startPos,
			Vector2 target, float duration, boolean removeAtEnd) {
		addActor(actor);
		GraphicsHelper.slide(actor, startPos, target, duration,
				removeAtEnd);
	}

	public void showParticles(Vector2 position, boolean special) {
		particles.addEffect(position, special);
	}

	public void scaleAndMove(final Actor actor, float targetScale,
			Vector2 targetPos, boolean reverse) {
		GraphicsHelper.scaleAndMove(actor, targetScale, targetPos, reverse);
	}

	public void changeCrystal(String color) {
		viewManager.changeCrystal(color);
	}

	public void scaleCrystal(float duration) {
		viewManager.scaleCrystal(duration);
	}

	public boolean isPauseWindowVisible() {
		return viewManager.isPauseWindowVisible();
	}

	
	public void gameOverEffect() {
		Image whiteImage = new Image(skin.getDrawable("gameover"));
		whiteImage.setSize(Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);
		whiteImage.setPosition(0, 0);
		GraphicsHelper.hitEffect(whiteImage);
		addActor(whiteImage);
	}
	
	public void spawnGameOverWindow() {
		viewManager.spawnGameOverWindow();
	}

	public void spawnPauseWindow() {
		viewManager.spawnPauseWindow();
	}

	public void tweenMonkeyDeath(Monkey monkey) {
		GraphicsHelper.tweenMonkeyDead(monkey);
	}

	public void hideBar() {
		viewManager.remove();
	}

	public LocalGameListener getListener() {
		return listener;
	}
}
