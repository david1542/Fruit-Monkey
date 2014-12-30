package engine.utils.general;

/**
 * Motion class. Sets the rate of the game(slow motion, regular motion, etc)
 * @author David
 *
 */
public class MotionController {

	private float rate;
	private Timer timer;
	private boolean slowMotion;
	
	public MotionController() {
		rate = 1f;
		slowMotion = false;
	}
	
	public void setSlowMotion(float rate, float duration) {
		slowMotion = true;
		this.rate = rate;
		if(timer == null) {
			timer = new Timer(duration);
			return;
		}
		timer.reset(duration);
	}
	
	public void update(float delta) {
		if(slowMotion) {
			timer.update(delta);
			if(timer.hasTimeElapsed()) {
				slowMotion = false;
				rate = 1f;
				timer.reset();
			}
		}
	}
	
	public float getRate() {
		return rate;
	}
	
}
