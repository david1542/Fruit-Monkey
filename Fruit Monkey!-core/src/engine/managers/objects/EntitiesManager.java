package engine.managers.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import engine.utils.general.Timer;
import entities.Entity;

public abstract class EntitiesManager<T extends Entity> {

	protected float Scroll_Speed;
	protected Array<T> entities;
	protected Timer timer;
	
	public EntitiesManager(float interval, float Scroll_Speed) {
		entities = new Array<T>();
		timer = new Timer(interval);
		this.Scroll_Speed = Scroll_Speed;
	}
	
	public EntitiesManager(float Scroll_Speed) {
		entities = new Array<T>();
		this.Scroll_Speed = Scroll_Speed;
	}
	
	public abstract void update(float delta);
	
	public boolean isCollidedWithMonkey(Rectangle bodyMonkey) {
		for (T entity : entities) {
			if (bodyMonkey.overlaps(entity.getBody()))
				return true;
		}
		return false;
	}
	
	public void removeValue(T entity) {
		entities.removeValue(entity, true);
	}
	
	public Array<T> getArrayEntities() {
		return entities;
	}
	
	public void setTimerInterval(float interval) {
		timer.reset(interval);
	}
	public float getTimerInterval() {
		return timer.getInterval();
	}

	public void setScrollSpeed(float ScrollSpeed) {
		this.Scroll_Speed = ScrollSpeed;
	}
	
	public float getScrollSpeed() {
		return Scroll_Speed;
	}
	
	public void clear() {
		entities = null;
		timer = null;
	}
}
