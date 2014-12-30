package engine.utils.graphics;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import engine.Values;

/* Created by David Lasry : 11/26/14 */
public class Scrollable{

	public enum ScrollableType {
		VERTICAL,
		HORIZONTAL_LEFT,
		HORIZONTAL_RIGHT
	}
	protected Vector2 position;
    protected Vector2 velocity;
    protected float width;
    protected float height;
    protected Rectangle body;
    protected boolean isNotVisible; // boolean value - is the object no longer visible?
    protected ScrollableType scrollableType;
    public Scrollable(float x, float y, ScrollableType scrollableType) {
    	position = new Vector2(x, y);
        switch(scrollableType) {
        case VERTICAL :
            velocity = new Vector2(0, Values.Entity_Scroll_Speed_Vertical);
            break;
        case HORIZONTAL_LEFT:
        	velocity = new Vector2(Values.Entity_Scroll_Speed_Horizontal, 0);
        	break;
        case HORIZONTAL_RIGHT:
        	velocity = new Vector2(Values.Entity_Scroll_Speed_Horizontal, 0);
        	break;
        }
        this.scrollableType = scrollableType;
        body = new Rectangle();
        body.x = x;
        body.y = y;
        isNotVisible = false;
    }
    public void update(float delta) {
		switch(scrollableType) {
		case VERTICAL:
			position.add(velocity.cpy().scl(delta));
			body.x = position.x;
			body.y = position.y;
	        // If the Scrollable object is no longer visible:
	        if (position.y + height < 0) {
	            isNotVisible = true;
	        }
	        return;
		case HORIZONTAL_LEFT:
			position.add(velocity.cpy().scl(delta));
			body.x = position.x;
			body.y = position.y;
	        // If the Scrollable object is no longer visible:
	        if (position.x + width < 0) {
	            isNotVisible = true;
	        }
	        return;
		case HORIZONTAL_RIGHT:
			position.add(velocity.cpy().scl(delta));
			body.x = position.x;
			body.y = position.y;
	        // If the Scrollable object is no longer visible:
	        if (position.x > Values.SCREEN_WIDTH) {
	            isNotVisible = true;
	        }
	        return;
		}
	}
    
    public void setScrollableType(ScrollableType scrollableType) {
    	this.scrollableType = scrollableType;
    	switch(scrollableType) {
        case VERTICAL :
            velocity = new Vector2(0, Values.Entity_Scroll_Speed_Vertical);
            break;
        case HORIZONTAL_LEFT:
        	velocity = new Vector2(Values.Entity_Scroll_Speed_Horizontal, 0);
        	break;
        case HORIZONTAL_RIGHT:
        	velocity = new Vector2(-Values.Entity_Scroll_Speed_Horizontal, 0);
        	break;
        }
	}
    
    public void reset(float newCoor) {
        switch(scrollableType) {
        case VERTICAL:
        	position.y = newCoor;
        case HORIZONTAL_LEFT:
        	position.x = newCoor;
        case HORIZONTAL_RIGHT:
        	position.x = newCoor;
        }
        isNotVisible = false;
    }
    
    public float getTail() {
        switch(scrollableType) {
        case VERTICAL:
        	return position.y + height;
        case HORIZONTAL_LEFT:
        	return position.x + width;
        case HORIZONTAL_RIGHT:
        	return position.x;
        default:
        	return position.x + width;
        }
    }
    public boolean isNotVisible() {
    	return isNotVisible;
    }
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }
    public Rectangle getBody() {
    	return body;
    }
    public void setPosition(float x, float y) {
    	position.set(x, y);
    	body.setPosition(x, y);
    }
    public void setPosition(Vector2 position) {
    	this.position.set(position.x, position.y);
    	body.setPosition(position.x, position.y);
    }
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
		body.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
		body.height = height;
	}
	public void setScrollSpeed(float scrollSpeed) {
		switch(scrollableType) {
		case HORIZONTAL_LEFT:
			velocity.x = scrollSpeed;
			break;
		case HORIZONTAL_RIGHT:
			velocity.x = -scrollSpeed;
			break;
		case VERTICAL:
			velocity.y = scrollSpeed;
			break;
		}
	}
}
