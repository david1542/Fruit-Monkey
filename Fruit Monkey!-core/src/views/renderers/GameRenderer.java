package views.renderers;

import views.elements.drawers.AnimalsDrawer;
import views.elements.drawers.FruitsDrawer;
import views.elements.drawers.MonkeyDrawer;
import engine.GameWorld;
import engine.Values;
import engine.utils.general.AssetsLoader;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/* Created by David Lasry : 10/23/14 */
public class GameRenderer extends Renderer {

	/*
	 * The game renderer class. Takes care of all the rendering work of the
	 * actual game
	 */
	// Texture and Sprites
	private AnimalsDrawer animalsPainter;
	private FruitsDrawer fruitsPainter;
	private MonkeyDrawer monkeyPainter;
	private Texture background;
	// ---------------------
	// Helper objects
	private GameWorld gameWorld;
	// gameWorld, batch, camera
	public GameRenderer(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		initiate();
	}

	@Override
	public void initiate() {
		// Animations
		animalsPainter = new AnimalsDrawer(gameWorld.getScrollManager().getAnimalsManager().getArrayEntities());
		fruitsPainter = new FruitsDrawer(gameWorld.getScrollManager().getFruitsManager().getArrayEntities());
		monkeyPainter = new MonkeyDrawer(gameWorld.getMonkeyManager().getMonkey());
		background = AssetsLoader.getInstance().getAsset("images/data/background.png",
				Texture.class);
		addActor(fruitsPainter);
		addActor(animalsPainter);
		addActor(monkeyPainter);
	}

	private void drawBackground(SpriteBatch batch) {
		batch.draw(background, 0, 0, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		drawBackground((SpriteBatch) batch);
		super.draw(batch, parentAlpha);
	}
}
