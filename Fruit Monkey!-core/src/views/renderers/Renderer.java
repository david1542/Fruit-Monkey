package views.renderers;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;

/* Created by David Lasry : 10/23/14 */
public abstract class Renderer extends Group implements Disposable{

	/* A Renderer Class */
	public Renderer() {
		
	}
	public abstract void initiate();
    public void dispose() {
    	// Implement if it is needed
    	// Batch is already dispose
    }
}
