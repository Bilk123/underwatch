package com.underwatch.screens;

import com.underwatch.game.UnderwatchApp;

import java.util.HashMap;

public class UnderwatchScreenManager {
    //enum for each screen state
    public enum eScreenState {
        MENU,
        PLAY,
        OPTIONS,
        SERVERS
    }

    //hash for storing the screens
    private HashMap<eScreenState, UnderScreen> screens;

    //reference to the app
    private UnderwatchApp underwatchApp;

    public UnderwatchScreenManager(UnderwatchApp underwatchApp) {
        this.underwatchApp = underwatchApp;
        initScreens();
        setScreen(eScreenState.PLAY);
    }

    //creates all screen on execution of the app
    private void initScreens() {
        this.screens = new HashMap<eScreenState, UnderScreen>();
        screens.put(eScreenState.MENU, new MenuScreen(underwatchApp));
        screens.put(eScreenState.PLAY, new GameScreen(underwatchApp));
        screens.put(eScreenState.OPTIONS, new OptionsScreen(underwatchApp));
        System.out.println("successfully initialised screens: ");
    }

    //sets screen
    public void setScreen(eScreenState screen) {
        underwatchApp.setScreen(screens.get(screen));
    }

    //disposes all non null screens
    public void dispose() {
        for (UnderScreen screen : screens.values()) if (screen != null) screen.dispose();
    }

}
