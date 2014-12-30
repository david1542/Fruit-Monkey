package engine.interfaces;

import views.screens.GameScreen.LocalGameListener;

import com.badlogic.gdx.math.Vector2;

public interface InformationProvider {

	boolean isAreaClean(Vector2 position); // 1 - From fruits,
															// 2 - From snakes
	LocalGameListener getGameListener();
}
