package engine.utils.general;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;

import engine.Settings;

/**
 * This class takes care of all the audio files in the game.
 * Active music are stored in an array so they can be muted later.
 * @author David
 *
 */
public final class MediaPlayer {

	private static Array<Music> activeMusics = new Array<Music>();

	public static void playSound(Sound sound) {
		if (Settings.getInstance().isSoundOn())
			sound.play();
	}

	public static void playMusic(Music music, boolean loop) {
		if (Settings.getInstance().isMusicOn()) {
			music.setLooping(loop);
			music.play();
		}
		activeMusics.add(music);
	}

	public static void clear() {
		activeMusics.clear();
	}

	public static void enableSounds() {
		Settings.getInstance().setSoundOn(true);
		Settings.getInstance().save();
	}

	public static void muteSounds() {
		Settings.getInstance().setSoundOn(false);
		Settings.getInstance().save();
	}

	public static void enableMusic() {
		Settings.getInstance().setMusicOn(true);
		for (Music music : activeMusics) {
			music.play();
		}
		Settings.getInstance().save();
	}

	public static void muteMusic() {
		Settings.getInstance().setMusicOn(false);
		for (Music music : activeMusics) {
			music.pause();
		}
		Settings.getInstance().save();
	}
	
	public static void removeMusic(Music music) {
		if(activeMusics.contains(music, true)) {
			music.stop();
			activeMusics.removeValue(music, true);
		}
	}
	
}
