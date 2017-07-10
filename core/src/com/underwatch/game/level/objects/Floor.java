package com.underwatch.game.level.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Floor extends LevelPiece{

    public Floor(float width, World world){
        super(width/2,0,width,2,world);
    }
}
