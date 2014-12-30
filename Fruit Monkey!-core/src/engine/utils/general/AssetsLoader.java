package engine.utils.general;

import views.screens.ScreenWrapper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * AssetsManager class contains an AssetManager object instance. This class
 * takes care of all the loading work, instead of doing it manually one by one
 * This way you don't have to interact with the AssetManager directly 
 * @author David
 *
 */
public final class AssetsLoader {
	private static AssetsLoader instance = new AssetsLoader();
	private static AssetManager manager = null;
	private ScreenWrapper screenToInform = null;
	
	private AssetsLoader() {
		manager = new AssetManager();
	}
	
	public void reload() {
		manager = new AssetManager();
	}
	
	public static AssetsLoader getInstance() {
		return instance;
	}
	
	// Load assets regarding the stage of the game
	public void loadAsstes(int type, ScreenWrapper screen) {
		screenToInform = screen;
		switch (type) {
		// Assets of the menu screen
		case 1:
			manager.load("images/data/backgroundmenu.png", Texture.class);
			manager.load("images/data/packs/skin.json", Skin.class);
			manager.load("music/menu.mp3", Music.class);
			break;
		// Assets of the game itself
		case 2:
			// Effects
			ParticleEffectLoader.ParticleEffectParameter particleEffectParameter = new ParticleEffectLoader.ParticleEffectParameter();
			particleEffectParameter.imagesDir = Gdx.files.internal("effects");
			manager.load("effects/star.p", ParticleEffect.class,
					particleEffectParameter);
			manager.load("effects/star2.p", ParticleEffect.class,
					particleEffectParameter);
			manager.load("effects/specialstar.p", ParticleEffect.class,
					particleEffectParameter);
			// Textures
			manager.load("images/data/background.png", Texture.class);
			// JSON'S and Packs
			manager.load("animations/snake.txt", TextureAtlas.class);
			manager.load("animations/fly.txt", TextureAtlas.class);
			manager.load("animations/snakeLeft.txt", TextureAtlas.class);
			manager.load("animations/spiderLeft.txt", TextureAtlas.class);
			manager.load("animations/spiderRight.txt", TextureAtlas.class);
			manager.load("animations/monkey.txt", TextureAtlas.class);
			manager.load("images/data/packs/fruits.txt", TextureAtlas.class);
			manager.load("images/data/packs/text/coolwords.txt",
					TextureAtlas.class);
			manager.load("images/data/packs/generalpack.txt",
					TextureAtlas.class);
			manager.load("music/game.mp3", Music.class);
			manager.load("sfx/punch.mp3", Sound.class);
			manager.load("sfx/highscore.wav", Sound.class);
			manager.load("sfx/monkey.wav", Sound.class);
			manager.load("sfx/points.wav", Sound.class);
			break;
		}
	}

	public <T> T getAsset(String path, Class<T> type) {
		return manager.get(path, type);
	}

	public boolean update() {
		return manager.update();
	}

	public float getProgress() {
		return manager.getProgress();
	}

	public AssetManager getManager() {
		return manager;
	}

	public void unload(int type) {
		switch (type) {
		// Menu Assets
		case 1:
			manager.unload("images/data/backgroundmenu.png");
			manager.unload("music/menu.mp3");
			break;
		// Game Assets
		case 2:
			manager.unload("effects/star.p");
			manager.unload("effects/star2.p");
			manager.unload("effects/specialstar.p");
			// Textures
			manager.unload("images/data/background.png");
			// JSON'S and Packs
			manager.unload("animations/snake.txt");
			manager.unload("animations/fly.txt");
			manager.unload("animations/spiderLeft.txt");
			manager.unload("animations/spiderRight.txt");
			manager.unload("animations/snakeLeft.txt");
			manager.unload("animations/monkey.txt");
			manager.unload("images/data/packs/fruits.txt");
			manager.unload("images/data/packs/text/coolwords.txt");
			manager.unload("sfx/punch.mp3");
			manager.unload("sfx/highscore.wav");
			manager.unload("sfx/monkey.wav");
			manager.unload("music/game.mp3");
			manager.unload("sfx/points.wav");
		}
	}

	public ScreenWrapper getScreenToInform() {
		return screenToInform;
	}

	public void resetScreenToInform() {
		screenToInform = null;
	}

	public void dispose() {
		resetScreenToInform();
		manager.clear();
		manager.dispose();
	}
}
