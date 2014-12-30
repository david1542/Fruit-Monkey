package engine.managers.general;

import views.GraphicsHelper;
import views.screens.GameScreen;
import views.screens.ScreenHandler;
import views.screens.ScreenType;
import views.screens.GameScreen.LocalGameListener;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import engine.Values;
import engine.managers.GameScrollManager;

/**
 * Logic Helper. A class that takes care of the logical part in the game. 
 * @author David Lasry
 *
 */
public class LogicManager {

	private final String[] CRYSTALS_COLORS = {"green", "blue", "grey", "orange", "pink", "yellow"};
	private int index = 1;
	private int limitCoolText, limitCrystal;
	private int difficulty;
	private PlayerDataManager dataManager;
	private GameScrollManager gameScrollManager;
	private LocalGameListener listener;
	public LogicManager(GameScrollManager gameScrollManager, PlayerDataManager dataManager) {
		limitCoolText = 50;
		limitCrystal = 60;
		difficulty = 1;
		this.gameScrollManager = gameScrollManager;
		this.dataManager = dataManager;
	}
	
	public void pointsLogic(int score, int points, Vector2 position) {
		if(score/15 >= difficulty) {
			difficulty++;
    		gameScrollManager.increaseDifficulty();
    		showMessage(points, position);
    	}
    	if(score >= limitCoolText) {
    		showCoolText(false);
    		showParticles(position, true);
    	} else {
    		showParticles(position, false);
    	}
    	if(score >= limitCrystal) {
    		changeCrystal(CRYSTALS_COLORS[index]);
    		scaleCrystal(1.5f);
    	}
	}
	
	public void strikesLogic() {
		if (listener.getPlayerStrikes() == 0)
			listener.notifiyGameOver();
		else if(listener.getPlayerStrikes() == 1)
			listener.setSlowMotionOn(3f);
	}
	
	public void determineBestScore() {
		if (dataManager.getBestScore() < dataManager.getPlayerScore())
			listener.setBestScore(dataManager.getPlayerScore());
	}
	
	public void setListener(LocalGameListener listener) {
		this.listener = listener;
	}
	// Helper methods
	private void showMessage(int points, Vector2 position) {
		((GameScreen) ScreenHandler.getInstance().getScreen(ScreenType.GAME))
		.getHudManager().showMessage("+"+points, position, Color.GREEN);
	}
	
	private void changeCrystal(String color) {
		limitCrystal += 60;
		((GameScreen) ScreenHandler.getInstance().getScreen(ScreenType.GAME))
		.getHudManager().changeCrystal(color);
		index++;
		if(index >= 5)
			index = 0;
	}
	
	private void scaleCrystal(float duration) {
		((GameScreen) ScreenHandler.getInstance().getScreen(ScreenType.GAME))
		.getHudManager().scaleCrystal(1f);
	}
	
	private void showCoolText(boolean special) {
		limitCoolText += 50;
		Image image = GraphicsHelper.createCoolText();
		((GameScreen) ScreenHandler.getInstance().getScreen(ScreenType.GAME))
		.getHudManager().slideFromLeft(image, new Vector2(0 - image.getWidth(),
				Values.SCREEN_HEIGHT / 3 - image.getHeight() / 2), new Vector2(Values.SCREEN_WIDTH, image.getY()), 3f, true);
	}
	
	private void showParticles(Vector2 position, boolean special) {
		((GameScreen) ScreenHandler.getInstance().getScreen(ScreenType.GAME))
		.getHudManager().showParticles(position, special);
	}
}
