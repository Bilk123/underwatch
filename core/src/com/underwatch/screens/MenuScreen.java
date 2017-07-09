package com.underwatch.screens;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.underwatch.game.UnderwatchApp;
import com.underwatch.game.ui.Button;


public class MenuScreen extends UnderScreen {

	// private Button start, options, quit;
	private SpriteBatch batch;
	Button test = new Button(50, 50, 100, 100, Color.BLUE, Color.WHITE, "test", new Button.ButtonEvent() {
		@Override
		public void executeAction() {
			System.out.println("hello");
		}
	});
	public MenuScreen(UnderwatchApp underwatchApp) {
		super(underwatchApp);
		batch = new SpriteBatch();
		// button initializing goes here
	}

	@Override
	public void update(float dt) {
		// update buttons
		test.update(dt);
	}

	@Override
	public void render(float dt) {
		batch.begin();
		// draw title
		// draw buttons
		test.draw(batch);
		batch.end();
	}

	@Override
	public void show() {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
