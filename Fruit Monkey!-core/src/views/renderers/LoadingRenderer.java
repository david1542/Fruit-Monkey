package views.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import engine.Values;

/* Created by David Lasry : 10/25/14 */
public class LoadingRenderer extends Renderer{

	private BitmapFont font;
	private TextureAtlas monkey_face_pack, loading_bar;
	private TextureRegion emptybarT;
	private TextureRegion fullbarT;
	private NinePatch emptybar;
	private NinePatch fullbar;
	private Sprite icon;
	private float progress;
	public LoadingRenderer() {
		initiate();
	}


	@Override
	public void initiate() {
		font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"), false);
		font.setScale(Values.Scalar_Width*0.8f, Values.Scalar_Height*0.8f);
		monkey_face_pack = new TextureAtlas(Gdx.files.internal("images/data/packs/monkeystates.txt"));
		loading_bar = new TextureAtlas(Gdx.files.internal("images/data/packs/loadingpack.txt"));
		icon = monkey_face_pack.createSprite("monkey_head");
		icon.setSize(Values.Monkey_Head_Width, Values.Monkey_Head_Height);
		icon.setPosition(Values.SCREEN_WIDTH/2-Values.Monkey_Head_Width/2, Values.SCREEN_HEIGHT/1.9f);
		icon.setOrigin(icon.getX()-icon.getWidth()/2, icon.getY()-icon.getHeight()/2);
		emptybarT = loading_bar.findRegion("empty");
		fullbarT = loading_bar.findRegion("full");
		emptybar = new NinePatch(emptybarT,8,8,8,8);
		fullbar = new NinePatch(fullbarT,8,8,8,8);
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		icon.draw(batch);
		emptybar.draw(batch, Values.SCREEN_WIDTH/8f, Values.SCREEN_HEIGHT/2 ,
				Values.SCREEN_WIDTH-100*Values.Scalar_Width, 30*Values.Scalar_Height);
		fullbar.draw(batch, Values.SCREEN_WIDTH/8f, Values.SCREEN_HEIGHT/2,
				progress/100*(Values.SCREEN_WIDTH-100*Values.Scalar_Width), 30*Values.Scalar_Height);
		font.drawMultiLine(batch,"Loading: "+(int) progress+"%",Values.SCREEN_WIDTH/2f, Values.SCREEN_HEIGHT/2 ,
				0, BitmapFont.HAlignment.CENTER);
	}
	
	public void setProgress(float progress) {
		this.progress = progress;
	}
	
	@Override
	public void dispose() {
		font.dispose();
		monkey_face_pack.dispose();
	}
}
