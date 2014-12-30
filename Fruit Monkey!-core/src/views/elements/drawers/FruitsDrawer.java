package views.elements.drawers;

import views.elements.EntitiesDrawer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

import engine.Values;
import engine.utils.general.AssetsLoader;
import entities.Entity;
import entities.Fruit;

public class FruitsDrawer extends EntitiesDrawer<Fruit>{

	private Sprite bananaNormal;
	private Sprite bananaSpecial;
	private Sprite pineapple;
	private Sprite grapes;
	private Sprite strawberry;
	private Sprite watermelon;
	public FruitsDrawer(Array<Fruit> entities) {
		super(entities);
	}
	@Override
	protected void initiate() {
		bananaNormal = AssetsLoader.getInstance().getAsset("images/data/packs/fruits.txt", TextureAtlas.class).createSprite("banana");
		bananaNormal.setSize(Values.Banana_Width_Normal, Values.Banana_Height_Normal);
		bananaNormal.setOrigin(bananaNormal.getWidth()/2, bananaNormal.getHeight()/2);
		bananaSpecial = AssetsLoader.getInstance().getAsset("images/data/packs/fruits.txt", TextureAtlas.class).createSprite("bananabunch");
		bananaSpecial.setSize(Values.Banana_Width_Special, Values.Banana_Height_Special);
		bananaSpecial.setOrigin(bananaSpecial.getWidth()/2, bananaSpecial.getHeight()/2);
		pineapple = AssetsLoader.getInstance().getAsset("images/data/packs/fruits.txt", TextureAtlas.class).createSprite("pineapple");
		pineapple.setSize(Values.Pineapple_Width, Values.Pineapple_Height);
		pineapple.setOrigin(pineapple.getWidth()/2, pineapple.getHeight()/2);
		watermelon = AssetsLoader.getInstance().getAsset("images/data/packs/fruits.txt", TextureAtlas.class).createSprite("watermelon");
		watermelon.setSize(Values.Watermelon_Width, Values.Watermelon_Height);
		watermelon.setOrigin(watermelon.getWidth()/2, watermelon.getHeight()/2);
		strawberry = AssetsLoader.getInstance().getAsset("images/data/packs/fruits.txt", TextureAtlas.class).createSprite("strawberry");
		strawberry.setSize(Values.Strawberry_Width, Values.Strawberry_Height);
		strawberry.setOrigin(strawberry.getWidth()/2, strawberry.getHeight()/2);
		grapes = AssetsLoader.getInstance().getAsset("images/data/packs/fruits.txt", TextureAtlas.class).createSprite("grapes");
		grapes.setSize(Values.Grapes_Width, Values.Grapes_Height);
		grapes.setOrigin(grapes.getWidth()/2, grapes.getHeight()/2);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		for(Entity entity : entities) {
			switch(((Fruit) entity).getFruitType()) {
			case NORMAL_BANANA:
				bananaNormal.setPosition(entity.getX(), entity.getY());
				bananaNormal.draw(batch);
				break;
			case SPECIAL_BANANA:
				bananaSpecial.setPosition(entity.getX(), entity.getY());
				bananaSpecial.draw(batch);
				break;
			case PINEAPPLE:
				pineapple.setPosition(entity.getX(), entity.getY());
				pineapple.draw(batch);
				break;
			case WATERMELON:
				watermelon.setPosition(entity.getX(), entity.getY());
				watermelon.draw(batch);
				break;
			case STRAWBERRY:
				strawberry.setPosition(entity.getX(), entity.getY());
				strawberry.draw(batch);
				break;
			case GRAPES:
				grapes.setPosition(entity.getX(), entity.getY());
				grapes.draw(batch);
				break;
			}
		}
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		bananaNormal.rotate(5f*Values.Scalar_Width);
		bananaSpecial.rotate(5f*Values.Scalar_Width);
		pineapple.rotate(5f*Values.Scalar_Width);
		watermelon.rotate(5f*Values.Scalar_Width);
		strawberry.rotate(5f*Values.Scalar_Width);
		grapes.rotate(5f*Values.Scalar_Width);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		draw((SpriteBatch) batch, parentAlpha);
	}
}
