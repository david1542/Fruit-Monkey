package engine.managers.general;

import com.badlogic.gdx.math.Vector2;

import engine.Settings;
import engine.Values;


/* Created by David Lasry : 30/10/14 */
public class PlayerDataManager{
	private int currentBestScore = Settings.getInstance().getBestScore();
	private int score, strikes;
	public PlayerDataManager() {
		score = 0;
		strikes = 3;
		
	}
	
	public void increaseScore(Vector2 position, int points) {
		position.set(position.x+Values.Monkey_Width/2, position.y+Values.Monkey_Height/2);
		score+=points;
	}
	
	public void decreaseStrikes() {
		strikes--;
	}
	
	public int getPlayerScore() {
		return score;
	}
	
	public int getBestScore() {
		return currentBestScore;
	}
	
	public int getPlayerStrikes() {
		return strikes;
	}
}
