package com.underwatch.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

import static com.badlogic.gdx.Gdx.files;

/**
 * Created by sam on 7/10/17.
 */
public class Test {
    public static void main(String[] args) {
        int x = 0x99000000;
        int c = (3);
        int y = ((x&(0xff<<(8*c)))>>(8*c))&0xff;

        System.out.println(Integer.toHexString(y));

    }
}
