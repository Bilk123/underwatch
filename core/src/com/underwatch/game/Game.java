package com.underwatch.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.underwatch.screens.MenuScreen;
import com.underwatch.screens.UnderScreen;

// TODO font scaling is rubbish. Decide which sizes we need!
public class Game extends ApplicationAdapter {
	
	public static final byte MENU = 0;
	public static final byte OPTIONS = 3;
	public static final byte PLAY = 2;
	public static final byte SERVERS = 1;
	
	private UnderScreen screen;
	
	@Override
	public void create () {
		screen = new MenuScreen(this);
	}

	@Override
	public void render () {
		screen.update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		screen.render();
	}
	
	@Override
	public void dispose () {
	
	}
	
	public void setScreen(byte which) {
		screen.dispose();
		
		switch(which) { // heh
		case MENU:
			screen = new MenuScreen(this);
			break;
		}
	}
}
