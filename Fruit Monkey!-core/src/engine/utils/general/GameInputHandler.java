package engine.utils.general;

import views.screens.State;
import views.screens.GameScreen.LocalGameListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import engine.Values;
import engine.interfaces.MonkeyHandler;
import entities.Monkey.MonkeyState;

/* Created by David Lasry : 10/25/14 */
public class GameInputHandler extends InputAdapter{

	private MonkeyHandler manager;
	private OrthographicCamera camera;
	private Vector3 clickPosition,previousCoordinates;
	private LocalGameListener listener;
	private boolean enabled;
	public GameInputHandler(MonkeyHandler world, OrthographicCamera camera, LocalGameListener listener) {
		this.manager = world;
		this.camera = camera;
		this.listener = listener;
		clickPosition = new Vector3();
		previousCoordinates = new Vector3();
		enabled = true;
	}

	/* Input Adapter implementation */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(enabled) {
			if(listener.getState() == State.RUN && manager.getMonkeyState() != MonkeyState.DEAD) {
				camera.unproject(clickPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0));
				if(manager.getMonkeyState() != MonkeyState.DEAD) {
					setCoordinates();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(enabled) {
			if(listener.getState() == State.RUN && manager.getMonkeyState() != MonkeyState.DEAD) {
				setCoordinates();
				return true;
			}
		}
		return false;
	}
	
	private void setCoordinates() {
		previousCoordinates.set(clickPosition);
		camera.unproject(clickPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0));
		if(clickPosition.x > previousCoordinates.x+10*Values.Scalar_Width)
			manager.setMonkeyState(MonkeyState.WALKING_RIGHT);
		else if(clickPosition.x < previousCoordinates.x-10*Values.Scalar_Width)
			manager.setMonkeyState(MonkeyState.WALKING_LEFT);
		else if(clickPosition.x == previousCoordinates.x)
			manager.setMonkeyState(MonkeyState.STAND);
		manager.setMonkeyPosition(clickPosition.x-Values.Monkey_Width/2, clickPosition.y-Values.Monkey_Height/2);
	}
	
	public void enable(boolean flag) {
		enabled = flag;
	}
}
