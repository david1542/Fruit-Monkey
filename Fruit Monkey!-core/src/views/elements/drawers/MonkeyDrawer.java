package views.elements.drawers;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;

import engine.Values;
import engine.utils.general.AssetsLoader;
import engine.utils.graphics.AnimationRegions;
import entities.Monkey;

/**
 * MonkeyDrawer class. Doesn't inherit from EntitiesDrawer because there is only
 * one entitiy here - the monkey. Thus, it is advised not to inherit from
 * EntitiesDrawer
 * 
 * @author David
 *
 */
public class MonkeyDrawer extends Group {

	private Monkey monkey;
	private AnimationRegions monkeyAnimRight;
	private AnimationRegions monkeyAnimLeft;
	private TextureRegion monkeyStand;
	private TextureRegion monkeyDead;

	public MonkeyDrawer(Monkey monkey) {
		this.monkey = monkey;
		monkeyStand = new TextureRegion(AssetsLoader.getInstance()
				.getAsset("animations/monkey.txt", TextureAtlas.class)
				.findRegion("monkey_stand"));
		monkeyAnimRight = new AnimationRegions(AssetsLoader.getInstance()
				.getAsset("animations/monkey.txt", TextureAtlas.class)
				.findRegion("monkeywalk"), 1, 4, 1 / 10f);
		monkeyAnimLeft = new AnimationRegions(AssetsLoader.getInstance()
				.getAsset("animations/monkey.txt", TextureAtlas.class)
				.findRegion("monkeywalk"), 1, 4, 1 / 10f, true,
				PlayMode.LOOP_REVERSED);
		// Textures
		monkeyDead = new TextureRegion(AssetsLoader.getInstance()
				.getAsset("animations/monkey.txt", TextureAtlas.class)
				.findRegion("monkeystates").getTexture(), 1205, 2, 191, 292);
		addActor(monkeyAnimRight);
		addActor(monkeyAnimLeft);
	}

	public void drawMonkey(SpriteBatch batch) {
		switch (monkey.getState()) {
		case STAND:
			batch.draw(monkeyStand, monkey.getX(), monkey.getY(),
					Values.Monkey_Width*1.2f, Values.Monkey_Height*1.2f);
			break;
		case WALKING_RIGHT:
			batch.draw(monkeyAnimRight.getCurrentFrame(), monkey.getX(),
					monkey.getY(), Values.Monkey_Width, Values.Monkey_Height);
			break;
		case WALKING_LEFT:
			batch.draw(monkeyAnimLeft.getCurrentFrame(), monkey.getX(),
					monkey.getY(), Values.Monkey_Width, Values.Monkey_Height);
			break;
		case DEAD:
			batch.draw(monkeyDead, monkey.getX(), monkey.getY(),
					Values.Monkey_Width, Values.Monkey_Height);
			break;
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		drawMonkey((SpriteBatch) batch);
	}
}
