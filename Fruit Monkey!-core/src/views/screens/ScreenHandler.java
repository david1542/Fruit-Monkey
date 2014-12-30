package views.screens;

import com.badlogic.gdx.utils.IntMap;

import engine.MonkeyRun;

/* Created by David Lasry : 11/1/14 */
public final class ScreenHandler {

	/* ScreenManager class. Takes care of the swapping, disposing and creating of the screens */
	private static ScreenHandler instance;
	private IntMap<ScreenWrapper> screensMap;
	private MonkeyRun game;
 
    private ScreenHandler() {
    	screensMap = new IntMap<ScreenWrapper>();
    }
 
    public MonkeyRun getGame() {
    	return game;
    }
    public static ScreenHandler getInstance() {
        if (null == instance) {
            instance = new ScreenHandler();
        }
        return instance;
    }
 
    public void initialize(MonkeyRun game) {
        this.game = game;
    }
    
    public void show(ScreenType screenType) {
        if (null == game) 
        	return;
        screensMap.put(screenType.ordinal(), screenType.getScreenInstance());
        game.setScreen(screensMap.get(screenType.ordinal()));
    }
 
    public ScreenWrapper getScreen(ScreenType screenType) {
    	if (null == game) 
        	return null;
    	else if (screensMap.containsKey(screenType.ordinal())) {
            return screensMap.get(screenType.ordinal());
        }
        return null;
    }
    
    public void dispose(ScreenType screenType) {
        if (!screensMap.containsKey(screenType.ordinal())) 
        	return;
        screensMap.remove(screenType.ordinal()).dispose();
    }
 
    public void dispose() {
        for (ScreenWrapper screen : screensMap.values())
            screen.dispose();
        screensMap.clear();
        instance = null;
    }
}
