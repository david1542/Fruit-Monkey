package engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public final class Settings {
	private static Preferences preferences;
    private boolean soundOn;
    private boolean musicOn;
    private boolean isFirstTime;
    private int bestScore;
    private static Settings instance = new Settings();

    public static Settings getInstance() {
        return instance;
    }

    private Settings() {
    	preferences = Gdx.app.getPreferences("game_settings");
        soundOn = preferences.getBoolean("sound", true);
        musicOn = preferences.getBoolean("music", true);
        isFirstTime = preferences.getBoolean("isFirstTime", true);
        setSoundOn(true);
        setMusicOn(true);
        save();
        bestScore = preferences.getInteger("best_score", 0);
    }
    
    public synchronized void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
        preferences.putBoolean("sound", this.soundOn);
    }

    public synchronized void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
        preferences.putBoolean("music", this.musicOn);
    }

    public void setFirstTime(boolean isFirstTime) {
    	this.isFirstTime = isFirstTime;
    	preferences.putBoolean("isFirstTime", this.isFirstTime);
    }
    
    public void setBestScore(int bestScore) {
    	this.bestScore = bestScore;
    	preferences.putInteger("best_score", this.bestScore);
    }
    public void save() {
    	preferences.flush();
    }
    public boolean isSoundOn() {
        return soundOn;
    }

    public boolean isMusicOn() {
        return musicOn;
    }
    
    public boolean isFirstTime() {
    	return isFirstTime;
    }
    
    public int getBestScore() {
    	return bestScore;
    }
}
