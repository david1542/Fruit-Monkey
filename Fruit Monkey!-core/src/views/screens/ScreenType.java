package views.screens;


public enum ScreenType {
 
    MAIN_MENU {
        @Override
        protected ScreenWrapper getScreenInstance() {
             return new MenuScreen();
        }
    },
 
    GAME {
        @Override
        protected ScreenWrapper getScreenInstance() {
             return new GameScreen();
        }
    };
 
    protected abstract ScreenWrapper getScreenInstance();
}
