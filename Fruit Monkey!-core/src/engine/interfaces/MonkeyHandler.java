package engine.interfaces;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import entities.Monkey;
import entities.Monkey.MonkeyState;

/* Created by David Lasry : 10/25/14 */
public interface MonkeyHandler {

	void setMonkeyPosition(float x, float y);

	void setMonkeyState(Monkey.MonkeyState state);

	Rectangle getMonkeyBody();

	Rectangle getTouchArea();
	
	MonkeyState getMonkeyState();

	Vector2 getMonkeyPosition();
}
