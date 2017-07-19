package com.underwatch.game.level.objects.staticPieces;

import com.badlogic.gdx.physics.box2d.World;

/**
 * The floor of a world
 * @see StaticMapPiece
 * @author Blake
 */
public class Floor extends StaticMapPiece {

    /**
     * A static body with given sprite and a height of 2m;
     * and placed at (0,0) world coordinates.
     *
     * @param width The width of the floor.
     * @param world Which world the floor must be created.
     */
    public Floor(float width, World world){
        super(width/2,0,width,2f,"textures/floor.png", world,true);
    }

}
