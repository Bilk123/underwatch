package com.underwatch.game.level.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class TestEntity extends Entity{
    public TestEntity(float x, float y, float width, float height, World world) {
        super("badlogic.jpg", x, y, width, height);
        bDef.type = BodyDef.BodyType.DynamicBody;
        createBody(world);
    }


}
