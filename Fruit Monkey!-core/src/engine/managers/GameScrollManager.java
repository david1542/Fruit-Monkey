package engine.managers;

import views.screens.GameScreen.LocalGameListener;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import engine.Values;
import engine.interfaces.InformationProvider;
import engine.managers.objects.scrollers.AnimalsManager;
import engine.managers.objects.scrollers.FruitsManager;
import entities.Animal;
import entities.Fruit;

/* Created by David Lasry : 10/25/14 */
public class GameScrollManager extends Actor implements InformationProvider {

	// ScrollManager takes care of all the auto-scrolling objects
	private AnimalsManager animalsManager;
	private FruitsManager fruitsManager;
	private LocalGameListener listener;
	private boolean stop = false;

	// Constructor receives a float that tells us where we need to create our
	// Path object
	public GameScrollManager() {
		animalsManager = new AnimalsManager(4f, Values.Entity_Scroll_Speed_Vertical);
		fruitsManager = new FruitsManager(4f, Values.Entity_Scroll_Speed_Vertical,
				this);
		stop = false;
	}

	@Override
	public void act(float delta) {
		if (!stop) {
			animalsManager.update(delta);
			fruitsManager.update(delta);
		}
	}

	public void setListener(LocalGameListener listener) {
		this.listener = listener;
	}

	@Override
	public boolean isAreaClean(Vector2 position) {
		Rectangle tempRect = new Rectangle();
		tempRect.x = position.x;
		tempRect.y = position.y;
		tempRect.width = Values.Fly_Width;
		tempRect.height = Values.Fly_Height;
		for (Fruit fruit : fruitsManager.getArrayEntities()) {
			if (fruit.getBody().overlaps(tempRect))
				return false;
		}
		for (Animal animal : animalsManager.getArrayEntities()) {
			if (animal.getBody().overlaps(tempRect))
				return false;
		}
		return true;
	}

	public AnimalsManager getAnimalsManager() {
		return animalsManager;
	}

	public FruitsManager getFruitsManager() {
		return fruitsManager;
	}

	public void increaseDifficulty() {
		if (animalsManager.getTimerInterval() > 0.75f)
			animalsManager.setTimerInterval((float) (animalsManager
					.getTimerInterval() - 0.20f));
		else
			animalsManager.setTimerInterval(0.75f);

		if (fruitsManager.getTimerInterval() > 0.75f)
			fruitsManager.setTimerInterval((float) (fruitsManager
					.getTimerInterval() - 0.20f));
		else
			fruitsManager.setTimerInterval(0.75f);

	}

	@Override
	public LocalGameListener getGameListener() {
		return listener;
	}

	public void stop() {
		stop = true;
	}
	
	public void resume() {
		stop = false;
	}
	public void clear() {
		animalsManager = null;
		fruitsManager = null;
	}
}