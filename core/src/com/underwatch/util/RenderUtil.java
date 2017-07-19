package com.underwatch.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class RenderUtil {
    private RenderUtil() {
    }

    public static Sprite repeatTexturesInRectangle(Texture texture, float width, float height) {
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        float w = texture.getWidth();
        float h = texture.getHeight();
        System.out.println(w+ " "+ h);
        Sprite sprite = new Sprite(new TextureRegion(texture, 0, 0, width, height));
        sprite.setSize(width,  height);
        return sprite;
    }
}
