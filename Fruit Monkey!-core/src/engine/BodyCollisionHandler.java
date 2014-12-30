package engine;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import views.screens.GameScreen.LocalGameListener;
import engine.managers.GameScrollManager;
import entities.Monkey;
import entities.Monkey.MonkeyState;

public class BodyCollisionHandler extends Actor {

	private Monkey monkey;
	private GameScrollManager scroller;
	private LocalGameListener localGameListener;

	public BodyCollisionHandler(Monkey monkey, GameScrollManager scroller) {
		this.monkey = monkey;
		this.scroller = scroller;
	}

	public void setListener(LocalGameListener localGameListener) {
		this.localGameListener = localGameListener;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		checkCollision();
	}

	private void checkCollision() {
		if (monkey.state != MonkeyState.DEAD) {
			// Check collision with fruits
			int points = scroller.getFruitsManager().isCollidedWithMonkeyFruits(monkey.getBody());
			if (points != 0) {
				localGameListener.notifyScoreIncreasment(
						new Vector2(monkey.getX(), monkey.getY()), points);
			}
			else if (scroller.getAnimalsManager().isCollidedWithMonkey(
					monkey.getBody())) {
				localGameListener.notifiyGameOver();
			}
		}
	}
}
