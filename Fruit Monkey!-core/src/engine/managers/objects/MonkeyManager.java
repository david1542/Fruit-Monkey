package engine.managers.objects;

import views.GraphicsHelper;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import engine.Values;
import engine.interfaces.MonkeyHandler;
import engine.managers.GameScrollManager;
import entities.Monkey;
import entities.Monkey.MonkeyState;


/* Created by David Lasry : 10/25/14 */
public class MonkeyManager implements MonkeyHandler {

	/*
	 * MonkeyManager class. Takes care of the monkey actions and
	 * events(movement, states, collisions) and simplify the process of
	 * interacting with the monkey
	 */
	private Monkey monkey;
	private Vector2 monkeyPosition;
	
	public MonkeyManager(GameScrollManager scroller) {
		monkey = new Monkey();
		monkeyPosition = new Vector2();
	}
	public Monkey getMonkey() {
		return monkey;
	}

	@Override
	public void setMonkeyPosition(float x, float y) {
		if (x+Values.Monkey_Width >= Values.SCREEN_WIDTH)
			x = Values.SCREEN_WIDTH - Values.Monkey_Width;
		else if (x <= 0)
			x = 0;
		if (y+Values.Monkey_Height >= Values.SCREEN_HEIGHT)
			y = Values.SCREEN_HEIGHT - Values.Scalar_Height;
		else if (y <= 0)
			y = 0;
		monkeyPosition.set(x+Values.Monkey_Width/2, y+Values.Monkey_Height/2);
		// Tween Engine Interpolation to Monkey
		GraphicsHelper.tweenMonkeyMovement(monkey, x, y, 0.1f);
	}

	@Override
	public void setMonkeyState(MonkeyState state) {
		monkey.state = state;
	}

	@Override
	public Vector2 getMonkeyPosition() {
		monkeyPosition.set(monkey.getX(), monkey.getY());
		return monkeyPosition;
	}

	@Override
	public MonkeyState getMonkeyState() {
		return monkey.state;
	}

	public void killMonkey() {
		monkey.state = MonkeyState.DEAD;
	}
	@Override
	public Rectangle getMonkeyBody() {
		return monkey.getBody();
	}
	@Override
	public Rectangle getTouchArea() {
		return monkey.getTouchArea();
	}
	
	public void clear() {
		monkey = null;
	}
}
