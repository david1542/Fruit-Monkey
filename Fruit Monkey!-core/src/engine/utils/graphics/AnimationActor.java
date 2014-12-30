package engine.utils.graphics;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * A utility class that takes a texture atlas with a specified frames
 * in a .txt file and animates them
 * @author David Lasry
 *
 */
public class AnimationActor extends Actor{

	private Animation animation;
	private TextureRegion currentFrame;
	private float elapsedTime;
	public AnimationActor(float duration, TextureAtlas atlas) {
		animation = new Animation(duration, atlas.getRegions());
		currentFrame = new TextureRegion(animation.getKeyFrame(0));
		elapsedTime = 0;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		currentFrame = animation.getKeyFrame(elapsedTime, true);
		elapsedTime += delta;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());
	}
}
