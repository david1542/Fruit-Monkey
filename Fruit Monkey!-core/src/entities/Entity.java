package entities;

import com.badlogic.gdx.utils.Pool.Poolable;

import engine.Values;
import engine.utils.graphics.Scrollable;

public class Entity extends Scrollable implements Cloneable, Poolable {

	protected EntityType type;

	public Entity(float x, float y, EntityType type, ScrollableType scrollableType) {
		super(x, y, scrollableType);
		this.type = type;
	}

	public EntityType getType() {
		return type;
	}

	public Object clone() {
		Object clone = null;
		try {
			clone = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
	
	public ScrollableType getScrollableType() {
		return scrollableType;
	}
	
	@Override
	public void reset() {
		switch(scrollableType) {
		case VERTICAL:
			position.y = Values.SCREEN_HEIGHT;
		case HORIZONTAL_LEFT:
			position.x = Values.SCREEN_WIDTH;
		case HORIZONTAL_RIGHT:
			position.x = 0;
		}
		isNotVisible = false;
	}
}
