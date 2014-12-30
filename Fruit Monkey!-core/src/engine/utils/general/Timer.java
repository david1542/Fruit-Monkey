package engine.utils.general;

/**
 * Timer class. Helps with spawning new objects
 * such as entities. Very simple and customized
 * @author David Lasry
 *
 */
public class Timer
{
    private float remaining;
    private float interval;

    public Timer(float interval)
    {
        this.interval = interval;
        this.remaining = interval;
    }

    public boolean hasTimeElapsed() {
    	return remaining < 0.0F;
    }
    
    public void reset() {
    	remaining = interval;
    }

    public void reset(float interval) {
        this.interval = interval;
        this.remaining = interval;
    }

    public void update(float delta) {
    	remaining -= delta;
    }
    public float getInterval() {
    	return interval;
    }
}
