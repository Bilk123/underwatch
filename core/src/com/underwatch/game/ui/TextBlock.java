package com.underwatch.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.underwatch.screens.UnderScreen;

public class TextBlock {
	public static final byte BIG = 0;
	public static final byte MEDIUM = 1;
	public static final byte SMALL = 2;
	
	private byte size;
	private float x, y, width, height;
	private BitmapFont font;
	private String text;
	
	public TextBlock(float x, float y, Color textColor, String text, byte size) {
		switch (size) {
		case BIG:
			font = UnderScreen.font64;
			break;
		case MEDIUM:
			font = UnderScreen.font36;
			break;
		case SMALL:
			font = UnderScreen.font24;
			break;
		default:
			font = UnderScreen.font36;
			break;
		}
		
		GlyphLayout glyphLayout = new GlyphLayout(font, text);
		width = glyphLayout.width;
		height = glyphLayout.height;
		
		this.x = x - width / 2;
		this.y = y + height / 2;
		
		this.text = text;
	}
	
	public void setText(String text) {
		this.text = text;
		
		GlyphLayout glyphLayout = new GlyphLayout(font, text);
		width = glyphLayout.width;
		height = glyphLayout.height;
		
		this.x = x - width / 2;
		this.y = y - height / 2;
	}
	
	public void draw(SpriteBatch sb) {
		font.draw(sb, text, x, y);
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}