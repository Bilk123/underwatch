package com.underwatch.screens;

import com.underwatch.game.Game;

//	Made by Sam
public abstract class UnderScreen {
	protected Game game;
	
	//	Constructor with reference to parent game. Needed for changing screens and such
	public UnderScreen(Game game) {
		this.game = game;
	}
	
	// Methods all good classes have
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
}
