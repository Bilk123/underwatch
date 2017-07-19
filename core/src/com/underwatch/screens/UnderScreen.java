package com.underwatch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.underwatch.game.UnderwatchApp;

//	Made by Sam
public abstract class UnderScreen implements Screen{
	UnderwatchApp underwatchApp;
	public static BitmapFont font24;
	public static BitmapFont font36;
	public static BitmapFont font64;


	static{
		font24 = new BitmapFont(Gdx.files.internal("fonts/vcr_24.fnt"));
		font36 = new BitmapFont(Gdx.files.internal("fonts/vcr_36.fnt"));
		font64 = new BitmapFont(Gdx.files.internal("fonts/vcr_64.fnt"));
	}

	//	Constructor with reference to parent underwatchApp. Needed for changing screens and such
	public UnderScreen(UnderwatchApp underwatchApp) {
		this.underwatchApp = underwatchApp;
	}
	
	// Methods all good classes have
	public abstract void update(float dt);

	//moved gl functions here, since the implementation of libGDX's Game instead of ApplicationListener
	@Override
	public void render(float dt) {
		update(dt);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void dispose() {

	}

	@Override
	public void resize(int width, int height) {

	}
}
