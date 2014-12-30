package views.hud.views;

import views.renderers.HUDManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import engine.Values;

class StrikesView extends GameSubView{

	private Image heart;
	private boolean passed;
	public StrikesView(Skin skin, HUDManager hudManager) {
		super(skin, hudManager);
		passed = false;
		initiate();
	
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(passed) {
			if(getChildren().size != hudManager.getListener().getPlayerStrikes()) {
				decreaseStrikes();
				if(getChildren().size > 0)
					hudManager.scaleAndMove(this, 1.5f, new Vector2(getX()+getWidth()/5, getY()), true);
			}
		}
	}
	
	@Override
	public void initiate() {
		for(int i=1; i<=3; i++) {
			heart = new Image(skin.getDrawable("monkey_head"));
			add(heart).pad(5).size(Values.HUD_Bar_Image_Width*1.3f, Values.HUD_Bar_Image_Height*1.3f);
		}
		pack();
		setOrigin(Align.center);
		setPosition(Values.SCREEN_WIDTH/2-getWidth()/2+10, Values.SCREEN_HEIGHT-getHeight());
		setOriginCenterActors();
		passed = true;
	}
	
	public void decreaseStrikes() {
		removeActor(getChildren().pop());
	}
	
	public void setOriginCenterActors() {
		for(Actor actor : getChildren()) {
			actor.setOrigin(Align.center);
		}
	}
	@Override
	public float getScaleX() {
		return getChildren().first().getScaleX();
	}
	
	@Override
	public float getScaleY() {
		return getChildren().first().getScaleY();
	}
	
	@Override
	public void setScale(float scaleXY) {
		super.setScale(scaleXY);
		for(Actor actor : getChildren()) {
			actor.setScale(scaleXY);
		}
	}
}
