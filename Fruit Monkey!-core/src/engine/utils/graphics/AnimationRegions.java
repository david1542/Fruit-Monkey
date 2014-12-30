package engine.utils.graphics;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * A utility class that can take a texture, a serious of images
 * that reside in the same texture and animate them.
 * @author David Larsry
 *
 */
public class AnimationRegions extends Actor {

	private Animation walkAnimation;
	private TextureRegion walkSheet;
	private TextureRegion[] walkFrames;
	private Sprite currentFrame;
	private float elapsedTime = 0f;

	public AnimationRegions(TextureRegion sheet, int Columns, int Rows,
			float duration) {
		walkSheet = sheet;
		TextureRegion[][] tmp = walkSheet.split(walkSheet.getRegionWidth()
				/ Columns, walkSheet.getRegionHeight() / Rows);
		walkFrames = new TextureRegion[Columns * Rows];
		int index = 0;
		for (int i = 0; i < Rows; i++) {
			for (int j = 0; j < Columns; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(duration, walkFrames);
		walkAnimation.setPlayMode(PlayMode.LOOP_REVERSED);
		currentFrame = new Sprite();
	}
	
	public AnimationRegions(TextureRegion sheet, int Columns, int Rows,
			float duration, int specificRow) {
		assert specificRow+1 <= Rows;
		walkSheet = sheet;
		TextureRegion[][] tmp = walkSheet.split(walkSheet.getRegionWidth()
				/ Columns, walkSheet.getRegionHeight() / Rows);
		walkFrames = new TextureRegion[Columns];
		int index = 0;
		for (int i = specificRow; i < specificRow+1; i++) {
			for (int j = 0; j < Columns; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(duration, walkFrames);
		walkAnimation.setPlayMode(PlayMode.LOOP_REVERSED);
		currentFrame = new Sprite();
	}
	
	public AnimationRegions(TextureRegion sheet, int Columns, int Rows,
			float duration, boolean flip, PlayMode playMode) {
		walkSheet = sheet;
		TextureRegion[][] tmp = walkSheet.split(walkSheet.getRegionWidth()
				/ Columns, walkSheet.getRegionHeight() / Rows);
		walkFrames = new TextureRegion[Columns * Rows];
		int index = 0;
		for (int i = 0; i < Rows; i++) {
			for (int j = 0; j < Columns; j++) {
				if(flip)
					tmp[i][j].flip(true, false);
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(duration, walkFrames);
		walkAnimation.setPlayMode(playMode);
		currentFrame = new Sprite();
	}
	public void changeTexture(TextureRegion sheet, int Columns, int Rows,
			float duration) {
		walkSheet = sheet;
		TextureRegion[][] tmp = walkSheet.split(walkSheet.getRegionWidth()
				/ Columns, walkSheet.getRegionHeight() / Rows);
		walkFrames = new TextureRegion[Columns * Rows];
		int index = 0;
		for (int i = 0; i < Rows; i++) {
			for (int j = 0; j < Columns; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(duration, walkFrames);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		elapsedTime += delta;
		currentFrame.setRegion(walkAnimation.getKeyFrame(elapsedTime, true));
		currentFrame.setSize(getWidth(), getHeight());
		currentFrame.setOrigin(currentFrame.getX() - currentFrame.getWidth()
				/ 2, currentFrame.getY() - currentFrame.getHeight() / 2);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		currentFrame.draw(batch);
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		currentFrame.setPosition(x, y);
	};
	
	@Override
	public void setScale(float scaleXY) {
		super.setScale(scaleXY);
		currentFrame.setScale(scaleXY);
	}

	@Override
	public float getScaleX() {
		return currentFrame.getScaleX();
	}

	@Override
	public float getScaleY() {
		return currentFrame.getScaleY();
	}

	public Sprite getCurrentFrame() {
		return currentFrame;
	}

	public boolean isAnimationFinished() {
		return walkAnimation.isAnimationFinished(elapsedTime);
	}

	public TextureRegion getRegion() {
		return currentFrame;
	}

	public void resetTime() {
		elapsedTime = 0;
	}
}
