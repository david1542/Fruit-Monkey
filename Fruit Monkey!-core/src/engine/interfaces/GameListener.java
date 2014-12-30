package engine.interfaces;

import views.screens.State;

import com.badlogic.gdx.math.Vector2;

public interface GameListener {
	void notifiyGameOver();

	void notifyScoreIncreasment(Vector2 position, int points);

	void notifyStrikesDecreasment();

	void setSlowMotionOn(float duration);
	
	void disableMonkeyInput();
	
	void enableMonkeyInput();
	
	State getState();

	void pauseGame();

	void resumeGame();

	void goToMainMenu();

	void restartGame();
	
	void determineBestScore();
	
	int getBestScore();
	
	void setBestScore(int bestScore);
}
