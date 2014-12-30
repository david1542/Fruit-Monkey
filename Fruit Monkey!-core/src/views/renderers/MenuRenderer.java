package views.renderers;

import views.GraphicsHelper;
import views.elements.BackgroundScroller;
import views.screens.ScreenHandler;
import views.screens.ScreenType;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import engine.Settings;
import engine.Values;
import engine.utils.general.AssetsLoader;

/* Created by David Lasry 10/30/14 */
public class MenuRenderer extends Renderer {

	/* Main Menu renderer. Takes care of rendering the menu screen */
	private Stage stage;
	private Skin menuSkin;
	private Table buttonsTable;
	private SettingsView settingsView;
	private CreditsView creditsView;
	private BackgroundScroller background;
	private ImageButton play, credits, settings;
	private Image logo;

	public MenuRenderer(Stage stage, OrthographicCamera camera,
			SpriteBatch batch) {
		this.stage = stage;
		initiate();
	}

	@Override
	public void initiate() {
		background = new BackgroundScroller();
		stage.addActor(background);
		menuSkin = AssetsLoader.getInstance().getAsset(
				"images/data/packs/skin.json", Skin.class);
		play = new ImageButton(menuSkin.getDrawable("play.up"),
				menuSkin.getDrawable("play.down"));
		settings = new ImageButton(menuSkin.getDrawable("settings.up"),
				menuSkin.getDrawable("settings.down"));
		credits = new ImageButton(menuSkin.getDrawable("credits.up"),
				menuSkin.getDrawable("credits.down"));
		credits.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				creditsView.show(stage).setSize(Values.Window_Width,
						Values.Window_Height);
				creditsView.setPosition(
						Values.SCREEN_WIDTH / 2 - creditsView.getWidth() / 2,
						Values.SCREEN_HEIGHT / 2 - creditsView.getHeight() / 2);
			}
		});
		settings.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				settingsView.show(stage).setSize(Values.Window_Width,
						Values.Window_Height);
				settingsView.setPosition(
						Values.SCREEN_WIDTH / 2 - settingsView.getWidth() / 2,
						Values.SCREEN_HEIGHT / 2 - settingsView.getHeight() / 2);
			}
		});
		play.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScreenHandler.getInstance().show(ScreenType.GAME);
				ScreenHandler.getInstance().dispose(ScreenType.MAIN_MENU);
			}
		});
		logo = new Image(menuSkin.getDrawable("new_logo"));
		logo.setSize(Values.Logo_Width, Values.Logo_Height);
		logo.setOrigin(logo.getX() + Values.Logo_Width / 2, logo.getY()
				+ Values.Logo_Height / 2);
		logo.setPosition(Values.SCREEN_WIDTH / 2 - Values.Logo_Width / 2,
				Values.SCREEN_HEIGHT);
		creditsView = new CreditsView("Credits", menuSkin, "default");
		settingsView = new SettingsView("Settings", menuSkin, "default");
		buttonsTable = new Table(menuSkin);
		buttonsTable.add(play).width(Values.Menu_Button_Width)
				.height(Values.Menu_Button_Height).row();
		buttonsTable.add(settings).width(Values.Menu_Button_Width)
				.height(Values.Menu_Button_Height).row();
		buttonsTable.add(credits).width(Values.Menu_Button_Width)
				.height(Values.Menu_Button_Height).row();
		buttonsTable.setPosition(0 - buttonsTable.getWidth(),
				Values.SCREEN_HEIGHT / 3f - buttonsTable.getHeight() / 2);
		GraphicsHelper.incomingMainLogo(logo);
		GraphicsHelper.slide(buttonsTable, new Vector2(
				0 - buttonsTable.getWidth(), Values.SCREEN_HEIGHT / 3f
						- buttonsTable.getHeight() / 2), new Vector2(
				Values.SCREEN_WIDTH / 2, Values.SCREEN_HEIGHT / 3f), 1.5f,
				false);
		stage.addActor(logo);
		stage.addActor(buttonsTable);
	}

	private class CreditsView extends Dialog {

		private Skin skin;
		private Label text;
		private ImageButton quitCreditsButton;

		public CreditsView(String title, Skin skin, String windowStyleName) {
			super(title, skin, windowStyleName);
			this.skin = skin;
			initiate();
		}

		private void initiate() {
			text = new Label("Programming and Design:\n David Lasry \n\n"
					+ "Game Art: gameartguppy.com \n"
					+ "Music: audiojungle.net\n" + "SFX:  opengameart.org\n",
					skin);
			text.setWrap(true);
			text.setAlignment(Align.center);
			text.setFontScale(Values.Scalar_Width * 1.3f,
					Values.Scalar_Height * 1.3f);
			quitCreditsButton = new ImageButton(skin.getDrawable("cancel"));
			quitCreditsButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					remove();
				}
			});
			getContentTable().align(Align.center);
			text(text).row();
			getButtonTable()
					.add(quitCreditsButton)
					.size(Values.HUD_Bar_Image_Width,
							Values.HUD_Bar_Image_Height).row();
			pack();
		}
	}

	private class SettingsView extends Dialog {

		private Skin skin;
		private ImageButton toggleMusic, toggleSound;

		public SettingsView(String title, Skin skin, String styleName) {
			super(title, skin, styleName);
			this.skin = skin;
			initiate();
		}

		private void initiate() {
			toggleMusic = new ImageButton(skin.get("music-on",
					ImageButtonStyle.class)) {
				@Override
				public void act(float delta) {
					if (Settings.getInstance().isMusicOn())
						setStyle(skin.get("music-on", ImageButtonStyle.class));
					else
						setStyle(skin.get("music-off", ImageButtonStyle.class));
				}
			};
			toggleMusic.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					ScreenHandler.getInstance().getGame().getListener()
							.toggleMusic();
				}
			});
			toggleSound = new ImageButton(skin.get("sound-on",
					ImageButtonStyle.class)) {
				@Override
				public void act(float delta) {
					if (Settings.getInstance().isSoundOn())
						setStyle(skin.get("sound-on", ImageButtonStyle.class));
					else
						setStyle(skin.get("sound-off", ImageButtonStyle.class));
				}
			};
			toggleSound.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					ScreenHandler.getInstance().getGame().getListener()
							.toggleSound();
				}
			});

			ImageButton quitSettingsButton = new ImageButton(
					skin.getDrawable("cancel"));
			quitSettingsButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					remove();
				}
			});

			align(Align.center);
			getContentTable()
					.add(toggleMusic)
					.size(Values.Inner_Button_Width, Values.Inner_Button_Height)
					.pad(5).center().row();
			getContentTable()
					.add(toggleSound)
					.size(Values.Inner_Button_Width, Values.Inner_Button_Height)
					.pad(5).center().row();
			getButtonTable()
					.add(quitSettingsButton)
					.size(Values.HUD_Bar_Image_Width,
							Values.HUD_Bar_Image_Height).bottom();
		}
	}
}
