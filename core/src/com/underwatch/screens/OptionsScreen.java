package com.underwatch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.underwatch.game.UnderwatchApp;
import com.underwatch.game.ui.Button;
import com.underwatch.game.ui.TextBlock;

public class OptionsScreen extends UnderScreen {

	private SpriteBatch spriteBatch;
	private TextBlock title, lblVolume, lblControls;
	private Button vUp, vDown, btnConrols, back;

	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	//	TODO load volume from memory
	private int volume;
	
	public OptionsScreen(UnderwatchApp game) {
		super(game);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		vDown.update(dt);
		vUp.update(dt);
		back.update(dt);
	}

	@Override
	public void render(float dt) {
		// TODO Auto-generated method stub
		spriteBatch.begin();
		title.draw(spriteBatch);
		lblVolume.draw(spriteBatch);
		vDown.draw(spriteBatch);
		vUp.draw(spriteBatch);
		back.draw(spriteBatch);
		spriteBatch.end();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.line(Gdx.graphics.getWidth() / 2, 0, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());
		shapeRenderer.line(0, Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 2);
		shapeRenderer.end();
	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
		title = new TextBlock(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() * 0.8f, Color.WHITE, "OPTIONS", TextBlock.BIG);
		lblVolume = new TextBlock(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() * 0.5f, Color.WHITE, "volume 100%", TextBlock.MEDIUM);

		//vDown = new Button(Gdx.graphics.getWidth() / 2 - lblVolume.getWidth(), lblVolume.getY(), lblVolume.getHeight(), lblVolume.getHeight(), Color.FIREBRICK, Color.WHITE, "-", null);
		vUp = new Button(Gdx.graphics.getWidth() / 2 + lblVolume.getWidth() * 0.7f, Gdx.graphics.getHeight() / 2, 40, 40, Color.FIREBRICK, Color.WHITE, "+", null);
		vDown = new Button(Gdx.graphics.getWidth() / 2 - lblVolume.getWidth() * 0.7f, Gdx.graphics.getHeight() / 2, 40, 40, Color.FIREBRICK, Color.WHITE, "-", null);
		//btnControls = new Button(Gdx.graphics.getWidth() / 2, Gdx.graphics.get)
		back = new Button(Gdx.graphics.getWidth() / 2, 28, 100, 40, Color.FIREBRICK, Color.WHITE, "<<<", null);
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

	}

}
