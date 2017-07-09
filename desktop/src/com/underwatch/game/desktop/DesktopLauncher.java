package com.underwatch.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.underwatch.game.UnderwatchApp;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = UnderwatchApp.APP_TITLE;
		config.width = UnderwatchApp.DESKTOP_WIDTH;
		config.height = UnderwatchApp.DESKTOP_HEIGHT;
		config.foregroundFPS = UnderwatchApp.APP_FPS;
		config.backgroundFPS = UnderwatchApp.APP_FPS;
		new LwjglApplication(new UnderwatchApp(), config);
	}
}
