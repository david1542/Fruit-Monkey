package views.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import engine.Values;
import engine.utils.general.AssetsLoader;
import engine.utils.graphics.Scrollable;
import engine.utils.graphics.Scrollable.ScrollableType;

public class BackgroundScroller extends Actor {
	private Scrollable front;
	private Scrollable back;
	private Texture background;

	public BackgroundScroller() {
		front = new Scrollable(0, 0, ScrollableType.HORIZONTAL_LEFT);
		front.setWidth(Values.Background_Menu_Width);
		front.setHeight(Values.Background_Menu_Height);
		back = new Scrollable(front.getTail(), 0, ScrollableType.HORIZONTAL_LEFT);
		back.setWidth(Values.Background_Menu_Width);
		back.setHeight(Values.Background_Menu_Height);
		front.setScrollSpeed(Values.Menu_Scroll_Speed_Horizontal);
		back.setScrollSpeed(Values.Menu_Scroll_Speed_Horizontal);
		background = AssetsLoader.getInstance()
				.getAsset("images/data/backgroundmenu.png", Texture.class);
	}

	@Override
	public void act(float delta) {
		front.update(delta);
		back.update(delta);
		if (front.isNotVisible()) {
			front.reset(back.getTail());
		}
		else if (back.isNotVisible()) {
			back.reset(front.getTail());
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(background, front.getX(), front.getY(),
				Values.Background_Menu_Width, Values.Background_Menu_Height);
		batch.draw(background, back.getX(), back.getY(),
				Values.Background_Menu_Width, Values.Background_Menu_Height);
	}

	public Scrollable getFront() {
		return front;
	}

	public Scrollable getBack() {
		return back;
	}
}
