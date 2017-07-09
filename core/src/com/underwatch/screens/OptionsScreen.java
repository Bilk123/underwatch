package com.underwatch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.underwatch.game.UnderwatchApp;
import com.underwatch.game.ui.Button;
import com.underwatch.game.ui.TextBlock;

public class OptionsScreen extends UnderScreen {

	private SpriteBatch spriteBatch;
	private TextBlock title, lblVolume;
	private Button vUp, vDown, controls;
	
	//	TODO load volume from memory
	private int volume;
	
	public OptionsScreen(UnderwatchApp underwatchApp) {
		super(underwatchApp);
		spriteBatch = new SpriteBatch();
		title = new TextBlock(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() * 0.8f, Color.WHITE, "OPTIONS", TextBlock.BIG);
		lblVolume = new TextBlock(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() * 0.5f, Color.WHITE, "volume 100%", TextBlock.MEDIUM);
		
		//vDown = new Button(Gdx.graphics.getWidth() / 2 - lblVolume.getWidth(), lblVolume.getY(), lblVolume.getHeight(), lblVolume.getHeight(), Color.FIREBRICK, Color.WHITE, "-", null);
		//vUp = new Button(Gdx.graphics.getWidth() / 2 + lblVolume.getWidth(), lblVolume.getY(), lblVolume.getHeight(), lblVolume.getHeight(), Color.FIREBRICK, Color.WHITE, "+", null);
		vDown = new Button(Gdx.graphics.getWidth() / 2, lblVolume.getY(), 250, 250, Color.FIREBRICK, Color.WHITE, "BIG PENIS", null);
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		vDown.update(dt);
		//vUp.update(dt);
	}

	@Override
	public void render(float dt) {
		// TODO Auto-generated method stub
		spriteBatch.begin();
		title.draw(spriteBatch);
		lblVolume.draw(spriteBatch);
		vDown.draw(spriteBatch);
		//vUp.draw(spriteBatch);
		spriteBatch.end();
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
