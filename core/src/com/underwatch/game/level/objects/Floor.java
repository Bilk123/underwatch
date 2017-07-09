package com.underwatch.game.level.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Floor {
    private Body body;

    public Floor(float width, World world){
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.fixedRotation = true;
        bDef.position.set(0,1);
        body = world.createBody(bDef);
        PolygonShape rect = new PolygonShape();
        rect.setAsBox(width/2, 2);
        body.createFixture(rect, 1.0f);

        rect.dispose();
    }

    public void render(Batch batch){

    }
}
