package com.underwatch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.underwatch.game.Game;

//	Made by Sam
public abstract class UnderScreen {
	protected Game game;
	public static BitmapFont font36;
	static{
		font36 = new BitmapFont(Gdx.files.internal("vcr_36.fnt"));
	}

	//	Constructor with reference to parent game. Needed for changing screens and such
	public UnderScreen(Game game) {
		this.game = game;
	}
	
	// Methods all good classes have
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
}
