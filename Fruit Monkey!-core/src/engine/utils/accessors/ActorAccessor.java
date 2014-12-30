package engine.utils.accessors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;

import aurelienribon.tweenengine.TweenAccessor;

/* Created by David Lasry 3/11/14 */
public class ActorAccessor implements TweenAccessor<Actor> {
	public static final int POSITION_XY = 1;
    public static final int OPACITY = 2;
    public static final int ROTATION = 4;
    public static final int CENTER_POSITION_XY = 5;
    public static final int SCALE = 6;

    @Override
    public int getValues(Actor target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case POSITION_XY:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                return 2;

            case OPACITY:
                returnValues[0] = target.getColor().a;
                return 1;

            case ROTATION:
                returnValues[0] = target.getRotation();
                return 1;

            case CENTER_POSITION_XY:
            	returnValues[0] = target.getX()+target.getWidth()/2;
            	returnValues[1] = target.getY()+target.getHeight()/2;
            	return 2;
            	
            case SCALE:
            	returnValues[0] = target.getScaleX();
            	returnValues[1] = target.getScaleY();
				return 2;
				
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Actor target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case POSITION_XY:
                target.setPosition(newValues[0], newValues[1]);
                break;

            case OPACITY:
                Color c = target.getColor();
                target.setColor(c.r, c.g, c.b, newValues[0]);
                break;

            case ROTATION:
                target.setRotation(newValues[0]);
                break;
            
            case CENTER_POSITION_XY:
            	target.setPosition(newValues[0], newValues[1]);
            	break;
            	
            case SCALE: target.setScale(newValues[0]);
				break;
            default:
                assert false;
        }
    }
}
