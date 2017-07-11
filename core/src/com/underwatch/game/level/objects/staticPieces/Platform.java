package com.underwatch.game.level.objects.staticPieces;

import com.badlogic.gdx.physics.box2d.World;
import com.underwatch.game.level.objects.MapPiece;

/**
 * Created by Blake on 10/07/2017.
 */
public class Platform extends MapPiece {

    public Platform(float x, float y, float width, float height, World world) {
        super(x, y, width, height, true, world);
    }

    @Override
    public String toString() {
        return "plat";
    }
}
