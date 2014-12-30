package views.hud.views;

import views.renderers.HUDManager;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

abstract class GameSubView extends Table{

	protected HUDManager hudManager;
	protected Skin skin;
	public GameSubView(Skin skin, HUDManager hudManager) {
		this.hudManager = hudManager;
		this.skin = skin;
	}
	
	public abstract void initiate();
}
