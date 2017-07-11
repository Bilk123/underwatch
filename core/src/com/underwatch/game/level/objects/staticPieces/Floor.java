package com.underwatch.game.level.objects.staticPieces;

import com.badlogic.gdx.physics.box2d.World;
import com.underwatch.game.level.objects.MapPiece;

public class Floor extends MapPiece {

    public Floor(float width, World world){
        super(width/2,0,width,2f,true, world);
    }

    @Override
    public String toString() {
        return "floor";
    }
}
