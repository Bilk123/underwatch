package com.underwatch.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.underwatch.game.Game;

public class MenuScreen extends UnderScreen {

	// private Button start, options, quit;
	private SpriteBatch batch;
	
	public MenuScreen(Game game) {
		super(game);
		batch = new SpriteBatch();
		// button initializing goes here
	}

	@Override
	public void update(float dt) {
		// update buttons
	}

	@Override
	public void render() {
		batch.begin();
		// draw title
		// draw buttons
		batch.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
