package com.underwatch.screens;

import java.awt.FontMetrics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.underwatch.game.Game;

// 	Rubbish class to test out stuff
public class TestScreen extends UnderScreen {

	SpriteBatch batch;
	BitmapFont font;
	
	public TestScreen(Game game) {
		super(game);
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("vcr_24.fnt"));
		System.out.println("?");
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		batch.begin();
		font.draw(batch, "Please KYS", 100, 100);
		batch.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
