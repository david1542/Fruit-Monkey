package views.elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

import entities.Entity;

public abstract class EntitiesDrawer<T extends Entity> extends Group{

	protected Array<T> entities;
	public EntitiesDrawer(Array<T> entities) {
		this.entities = entities;
		initiate();
	}
	
	protected abstract void initiate();
	public abstract void draw(SpriteBatch batch, float parentAlpha);
	
	public void clear() {
		entities.clear();
	}
}
