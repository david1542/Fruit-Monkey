package views.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * ScreenWrapper class - For convenience
 * @author David Lasry
 *
 */
public abstract class ScreenWrapper implements Screen{

	protected State state;
	protected OrthographicCamera camera;
	protected SpriteBatch batch;
	public ScreenWrapper() {
		state = State.PAUSE;
	}
	@Override
	public abstract void render(float delta);
	public void assetsLoaded() {
		state = State.RUN;
		// IMPLEMENT IN CHILDREN
	}
	public OrthographicCamera getCamera() {
		return camera;
	}
	public SpriteBatch getBatch() {
		return batch;
	}
	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		state = State.PAUSE;
	}

	@Override
	public void resume() {
		state = State.RUN;
	}
}
