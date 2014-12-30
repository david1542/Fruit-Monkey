package entities;

import com.badlogic.gdx.math.Rectangle;

import engine.Values;

/**
 * Monkey class. This is the entity of the monkey.
 * It holds the body, position and state of the monkey.
 * Note - this is an independent entity. It doesn't inherit from other classes
 * because of its uniqueness.
 * @author David
 *
 */
public class Monkey {
	/* Monkey class */
	public enum MonkeyState {

		STAND, WALKING_LEFT, WALKING_RIGHT, DEAD
	}

	public MonkeyState state;
	private Rectangle body, touchArea;
	private float x, y;

	public Monkey() {
		x = 0 - Values.Monkey_Width;
		y = Values.SCREEN_HEIGHT / 5;
		body = new Rectangle(x + 9 * Values.Scalar_Width, y + 9
				* Values.Scalar_Height, Values.Monkey_Width - 18
				* Values.Scalar_Width, Values.Monkey_Height - 18
				* Values.Scalar_Height);
		touchArea = new Rectangle(x - 9 * Values.Scalar_Width, y - 9
				* Values.Scalar_Height, Values.Monkey_Width + 18
				* Values.Scalar_Width, Values.Monkey_Height + 18
				* Values.Scalar_Height);
		state = MonkeyState.STAND;
	}

	public Rectangle getBody() {
		return body;
	}

	public Rectangle getTouchArea() {
		return touchArea;
	}

	public boolean isCollided(Rectangle rect) {
		return body.overlaps(rect);
	}
	
	public MonkeyState getState() {
		return state;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
		touchArea.x = x;
		body.x = x + 9 * Values.Scalar_Width;
	}

	public void setY(float y) {
		this.y = y;
		touchArea.y = y;
		body.y = y + 9 * Values.Scalar_Height;
	}
}
