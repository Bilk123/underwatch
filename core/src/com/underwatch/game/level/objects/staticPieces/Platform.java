package com.underwatch.game.level.objects.staticPieces;

import com.badlogic.gdx.physics.box2d.World;

/**
 * A platform which its dimensions are generated from a shapeGen png.
 *
 * @author Blake
 */
public class Platform extends StaticMapPiece {
    /**
     * a 4x1 platform is generated from a platform1ShapeGen.png
     * with a the sprite platform1.png
     * @param x The world X component coordinate.
     * @param y The world Y component coordinate.
     * @param world The world the platform is created.
     */
    public Platform(float x, float y, World world) {
        super(x, y, "sprites/platform1.png", "sprites/platform1ShapeGen.png", world);
    }
}
