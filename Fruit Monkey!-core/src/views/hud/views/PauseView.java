package views.hud.views;

import views.renderers.HUDManager;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import engine.Values;

class PauseView extends GameSubView {

	private ImageButton quit, resume;
	private Window pauseWindow;

	public PauseView(Skin skin, HUDManager hudManager) {
		super(skin, hudManager);
		initiate();
	}

	@Override
	public void initiate() {
		pauseWindow = new Window("Pause", skin, "default");
		pauseWindow.setSize(Values.Window_Width, Values.Window_Height);
		pauseWindow.setPosition(0 - pauseWindow.getWidth(), Values.SCREEN_HEIGHT / 2
				- pauseWindow.getHeight() / 2);
		resume = new ImageButton(skin.getDrawable("play"));
		resume.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				pauseWindow.setVisible(false);
				setVisible(false);
				remove();
				hudManager.getListener().resumeGame();
			}
		});
		quit = new ImageButton(skin.getDrawable("menue"));
		quit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				hudManager.getListener().goToMainMenu();
			}
		});
		pauseWindow.align(Align.center);
		pauseWindow
				.add(resume)
				.size(Values.HUD_Bar_Image_Width * 1.3f,
						Values.HUD_Bar_Image_Height * 1.3f).center().pad(20)
				.row();
		pauseWindow
				.add(quit)
				.size(Values.HUD_Bar_Image_Width * 1.3f,
						Values.HUD_Bar_Image_Height * 1.3f).center().pad(20)
				.row();
		pauseWindow.setVisible(false);
		addActor(pauseWindow);
	}

	public Window getPauseWindow() {
		return pauseWindow;
	}
}
