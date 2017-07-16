package com.underwatch.game.level.objects.staticPieces;


import com.badlogic.gdx.physics.box2d.World;

/**
 * A Square 2mx2m static block.
 * @see StaticMapPiece
 * @author Blake
 */
public class Block extends StaticMapPiece {
    /**
     * creates a 2x2 block in a specified world.
     *
     * @param x The world X component coordinate.
     * @param y The world Y component coordinate.
     * @param world The world the block will be created in.
     */
    public Block(float x, float y, World world) {
        super(x, y,"sadfellow.png" ,"sad.png", world);
    }

}
