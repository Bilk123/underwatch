package com.underwatch.game.level.objects.staticPieces;


import com.badlogic.gdx.physics.box2d.World;
import com.underwatch.game.level.objects.MapPiece;

public class Block extends MapPiece {
    public Block(float x, float y, World world) {
        super(x, y, 2, 2, true,world);
    }

    @Override
    public String toString() {
        return "block";
    }
}
