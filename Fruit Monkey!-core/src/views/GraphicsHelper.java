package views;

import views.screens.GameScreen;
import views.screens.ScreenHandler;
import views.screens.ScreenType;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Back;
import aurelienribon.tweenengine.equations.Elastic;
import aurelienribon.tweenengine.equations.Quad;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import engine.Values;
import engine.utils.accessors.ActorAccessor;
import engine.utils.accessors.MonkeyAccessor;
import engine.utils.general.AssetsLoader;
import entities.Monkey;
import entities.Monkey.MonkeyState;

/**
 * This class holds some useful methods related to graphics. The implementation
 * is hidden and thus, convenient.
 * 
 * @author David Lasry
 *
 */
public final class GraphicsHelper {

	private static TweenManager tweenManager = ScreenHandler.getInstance()
			.getGame().getTweenManager();

	public static Label createLabel(String msg, Color color, Skin skin) {
		Label label = new Label(msg, skin, "nice-style-default");
		label.setColor(color);
		label.setFontScale(Values.Scalar_Width * 1.1f,
				Values.Scalar_Height * 1.1f);
		return label;
	}

	public static void hitEffect(final Image whiteImage) {
		Timeline.createSequence()
				.push(Tween.set(whiteImage, ActorAccessor.OPACITY).target(1f))
				.push(Tween.to(whiteImage, ActorAccessor.OPACITY, 0.15f)
						.target(1f))
				.setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int arg0, BaseTween<?> arg1) {
						whiteImage.remove();
					}
				}).start(tweenManager);
	}

	public static Image createCoolText() {
		Image coolText = new Image(AssetsLoader
				.getInstance()
				.getAsset("images/data/packs/text/coolwords.txt",
						TextureAtlas.class)
				.createSprite("" + MathUtils.random(1, 6)));
		coolText.setSize(
				(float) (coolText.getWidth() * (0.3 * Values.Scalar_Width)),
				(float) (coolText.getHeight() * (0.8 * Values.Scalar_Height)));
		coolText.setPosition(0 - coolText.getWidth(), Values.SCREEN_HEIGHT / 3
				- coolText.getHeight() / 2);
		return coolText;
	}

	public static void floatAndFade(final Actor actor, Vector2 position,
			float duration, boolean up) {
		assert duration > 1;
		float dir = up ? 1f : -1f;
		Vector2 startingPosition = position.cpy();
		Timeline.createSequence()
				.push(Tween.set(actor, ActorAccessor.POSITION_XY).target(
						position.x, position.y))
				.push(Tween.set(actor, ActorAccessor.OPACITY).target(0))
				.push(Tween.to(actor, ActorAccessor.OPACITY, 1f).target(1f)
						.ease(Quad.INOUT))
				.beginParallel()
				.push(Tween
						.to(actor, ActorAccessor.POSITION_XY, duration - 1)
						.target(startingPosition.x,
								startingPosition.y + dir * actor.getHeight()
										* 2).ease(Quad.INOUT))
				.push(Tween.to(actor, ActorAccessor.OPACITY, duration - 1)
						.target(0.0f).ease(Quad.OUT)).end()
				.setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int arg0, BaseTween<?> arg1) {
						actor.remove();
					}
				}).start(tweenManager);
	}

	public static void slide(final Actor actor, Vector2 startPos,
			Vector2 target, float duration, boolean removeAtEnd) {
		assert duration > 1;
		if (removeAtEnd) {
			Tween.set(actor, ActorAccessor.POSITION_XY).target(startPos.x,
					startPos.y);
			Tween.to(actor, ActorAccessor.POSITION_XY, duration)
					.target(target.x, target.y).start(tweenManager)
					.setCallback(new TweenCallback() {
						
						@Override
						public void onEvent(int arg0, BaseTween<?> arg1) {
							actor.remove();
						}
					}).start(tweenManager);
		} else {
			Tween.set(actor, ActorAccessor.POSITION_XY).target(
					0 - actor.getWidth(),
					Values.SCREEN_HEIGHT / 3 - actor.getHeight() / 2);
			Tween.to(actor, ActorAccessor.POSITION_XY, duration)
					.target(target.x, target.y).start(tweenManager);
		}
	}

	public static void slideInFromRight(final Actor actor, Vector2 target,
			float duration) {
		assert duration > 1;
		Tween.set(actor, ActorAccessor.POSITION_XY).target(
				Values.SCREEN_WIDTH + actor.getWidth(),
				Values.SCREEN_HEIGHT / 3 - actor.getHeight() / 2);
		Tween.to(actor, ActorAccessor.POSITION_XY, duration)
				.target(target.x, target.y).start(tweenManager)
				.setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int arg0, BaseTween<?> arg1) {
						actor.remove();
					}
				}).start(tweenManager);
	}

	public static void slideOffScreen(final Actor actor, float duration,
			boolean left, boolean removeAtEnd) {
		assert duration > 1;
		if (removeAtEnd) {
			if (left) {
				Tween.to(actor, ActorAccessor.POSITION_XY, duration)
						.target(0 - actor.getWidth(), actor.getY())
						.setCallback(new TweenCallback() {
							
							@Override
							public void onEvent(int arg0, BaseTween<?> arg1) {
								actor.remove();
							}
						}).start(tweenManager);
			} else {
				Tween.to(actor, ActorAccessor.POSITION_XY, duration)
						.target(Values.SCREEN_WIDTH, actor.getY())
						.setCallback(new TweenCallback() {
							
							@Override
							public void onEvent(int arg0, BaseTween<?> arg1) {
								actor.remove();
							}
						}).start(tweenManager);
			}
		} else {
			if (left) {
				Tween.to(actor, ActorAccessor.POSITION_XY, duration)
						.target(0 - actor.getWidth(), actor.getY())
						.start(tweenManager);
			} else {
				Tween.to(actor, ActorAccessor.POSITION_XY, duration)
						.target(Values.SCREEN_WIDTH + actor.getWidth(),
								actor.getY()).start(tweenManager);
			}
		}
	}

	public static void scaleAndMove(final Actor actor, float targetScale,
			Vector2 targetPos, boolean reverse) {
		if (reverse) {
			Timeline.createSequence()
					.push(Tween.to(actor, ActorAccessor.POSITION_XY, 1f)
							.target(targetPos.x, targetPos.y))
					.push(Tween.to(actor, ActorAccessor.SCALE, 1.5f)
							.target(targetScale).ease(Back.INOUT))
					.push(Tween.to(actor, ActorAccessor.SCALE, 1f).target(1f))
					.start(tweenManager);
		} else {
			Timeline.createSequence()
					.push(Tween.to(actor, ActorAccessor.POSITION_XY, 1f)
							.target(targetPos.x, targetPos.y))
					.push(Tween.to(actor, ActorAccessor.SCALE, 1.5f)
							.target(targetScale).ease(Back.INOUT))
					.start(tweenManager);
		}
	}

	public static void incomingMainLogo(final Actor actor) {
		Timeline.createSequence()
				.push(Tween.set(actor, ActorAccessor.POSITION_XY).target(
						Values.SCREEN_WIDTH / 2 - Values.Logo_Width / 2,
						Values.SCREEN_HEIGHT))
				.push(Tween.to(actor, ActorAccessor.POSITION_XY, 2f).target(
						Values.SCREEN_WIDTH / 2 - Values.Logo_Width / 2,
						Values.SCREEN_HEIGHT - 300 * Values.Scalar_Height))
				.push(Tween.to(actor, ActorAccessor.SCALE, 1.5f).target(1.3f)
						.ease(Back.INOUT)).start(tweenManager);
	}

	// Scale for actors
	public static void scale(final Actor actor, float target, float duration) {
		Timeline.createSequence()
				.push(Tween.to(actor, ActorAccessor.SCALE, duration).target(
						target))
				.push(Tween.to(actor, ActorAccessor.SCALE, duration).target(1f))
				.start(tweenManager);
	}

	// Monkey tweens
	public static void tweenMonkeyDead(Monkey monkey) {
		float xDead;
		if (monkey.getX() > Values.SCREEN_WIDTH / 2)
			xDead = Values.SCREEN_WIDTH / 2 + Values.Trail_Width;
		else
			xDead = Values.SCREEN_WIDTH / 5;
		Timeline.createSequence()
				.push(Tween
						.to(monkey, MonkeyAccessor.POSITION_XY, 2f)
						.ease(Elastic.OUT)
						.target(xDead,
								monkey.getY() + Values.Monkey_Height / 2
										* Values.Scalar_Height))
				.push(Tween.to(monkey, MonkeyAccessor.POSITION_XY, 1f).target(
						xDead, 0 - Values.Monkey_Height)).delay(0f)
				.setCallback(new TweenCallback() {
					@Override
					public void onEvent(int arg0, BaseTween<?> arg1) {
						((GameScreen) ScreenHandler.getInstance().getScreen(
								ScreenType.GAME)).getHudManager()
								.spawnGameOverWindow();
					}
				}).start(tweenManager);
	}

	public static void tweenMonkeyMovement(final Monkey monkey, float x, float y, float duration) {
		if(x > monkey.getX())
			monkey.state = MonkeyState.WALKING_RIGHT;
		else
			monkey.state = MonkeyState.WALKING_LEFT;
		Tween.to(monkey, MonkeyAccessor.POSITION_XY, duration).target(x, y).setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				if(monkey.state != MonkeyState.DEAD){
					monkey.state = MonkeyState.STAND;
				}
			}
		})
				.start(tweenManager);
	}

}
