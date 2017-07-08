package com.underwatch.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.underwatch.screens.UnderScreen;


public class Button {
    public static final int FONT_SIZE = 36;

    private String text;
    private ButtonEvent event;
    private Color bgColor, fontColor;
    private Sprite button;
    private float txtWidth, txtHeight;
    private boolean hover = false, click = false;

    public Button(float x, float y, float width, float height, Color bgColor, Color fontColor, String text, ButtonEvent event) {
        this.text = text;
        this.event = event;
        this.bgColor = bgColor;
        this.fontColor = fontColor;
        Pixmap tmp = new Pixmap(1, 1, Pixmap.Format.RGB888);
        tmp.setColor(bgColor);
        tmp.fill();
        button = new Sprite(new Texture(tmp));
        button.setSize(width, height);
        button.setOriginCenter();
        button.setPosition(x - width / 2, y - height / 2);
        GlyphLayout gl = new GlyphLayout(UnderScreen.font36, text);
        txtWidth = gl.width;
        txtHeight = gl.height;
    }

    public void draw(SpriteBatch sb) {
        button.draw(sb);
        sb.setColor(fontColor);
        UnderScreen.font36.draw(sb, text, button.getX() + button.getWidth() / 2 - txtWidth / 2, button.getY() + button.getHeight() / 2);
        sb.setColor(Color.WHITE);
    }

    public void update(float dt) {
        if (button.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
            //On Click
            System.out.println(fontColor);
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                if (!click) {
                    event.executeAction();
                    click = true;
                }
            }
            //On Hover
            if (!hover) {
                //UnderScreen.font36.setColor(bgColor);
                button.setColor(fontColor);
                hover = true;
            }
        }
        //No interaction
        else {
            hover = false;
            click = false;
            button.setColor(bgColor);
            //UnderScreen.font36.setColor(fontColor);
        }
    }

    public interface ButtonEvent {
        void executeAction();
    }
}
