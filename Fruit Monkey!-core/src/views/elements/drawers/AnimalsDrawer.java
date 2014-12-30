package views.elements.drawers;

import views.elements.EntitiesDrawer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

import engine.Values;
import engine.utils.general.AssetsLoader;
import engine.utils.graphics.AnimationActor;
import engine.utils.graphics.Scrollable.ScrollableType;
import entities.Animal;
import entities.Entity;

public class AnimalsDrawer extends EntitiesDrawer<Animal>{
	// Animations and States:
	private AnimationActor snakeVertical;
	private AnimationActor snakeLeft;
	private AnimationActor flyVertical;
	private AnimationActor spiderLeft;
	private AnimationActor spiderRight;
	public AnimalsDrawer(Array<Animal> entities) {
		super(entities);
	}

	@Override
	protected void initiate() {
		snakeVertical = new AnimationActor(1/15f, AssetsLoader.getInstance().getAsset("animations/snake.txt", TextureAtlas.class));
		snakeVertical.setSize(Values.Snake_Width, Values.Snake_Height);
		snakeLeft = new AnimationActor(1/15f, AssetsLoader.getInstance().getAsset("animations/snakeLeft.txt", TextureAtlas.class));
		snakeLeft.setSize(Values.Fly_Width, Values.Fly_Height);
		flyVertical =  new AnimationActor(1/30f, AssetsLoader.getInstance().getAsset("animations/fly.txt", TextureAtlas.class));
		flyVertical.setSize(Values.Fly_Width, Values.Fly_Height);
		spiderLeft =  new AnimationActor(1/30f, AssetsLoader.getInstance().getAsset("animations/spiderLeft.txt", TextureAtlas.class));
		spiderLeft.setSize(Values.Spider_Width, Values.Spider_Height);
		spiderRight =  new AnimationActor(1/30f, AssetsLoader.getInstance().getAsset("animations/spiderRight.txt", TextureAtlas.class));
		spiderRight.setSize(Values.Spider_Width, Values.Spider_Height);
		addActor(snakeVertical);
		addActor(snakeLeft);
		addActor(flyVertical);
		addActor(spiderLeft);
		addActor(spiderRight);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		for(Entity entity : entities) {
			switch(((Animal) entity).getAnimalType()) {
			case SNAKE:
				if(entity.getScrollableType() == ScrollableType.VERTICAL) {
					snakeVertical.setPosition(entity.getX(), entity.getY());
					snakeVertical.draw(batch, parentAlpha);
				} else {
					snakeLeft.setPosition(entity.getX(), entity.getY());
					snakeLeft.draw(batch, parentAlpha);
				}
				break;
			case FLY:
				flyVertical.setPosition(entity.getX(), entity.getY());
				flyVertical.draw(batch, parentAlpha);
				break;
			case SPIDER:
				if(entity.getScrollableType() == ScrollableType.HORIZONTAL_LEFT) {
					spiderLeft.setPosition(entity.getX(), entity.getY());
					spiderLeft.draw(batch, parentAlpha);
				} else {
					spiderRight.setPosition(entity.getX(), entity.getY());
					spiderRight.draw(batch, parentAlpha);
				}
				break;
			}
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		draw((SpriteBatch) batch, parentAlpha);
	}
}
