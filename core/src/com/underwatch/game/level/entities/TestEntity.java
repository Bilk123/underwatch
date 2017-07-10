package com.underwatch.game.level.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.underwatch.screens.GameScreen;

public class TestEntity extends Entity {
    public TestEntity(float x, float y, float width, float height, World world) {
        super("badlogic.jpg", x, y, width, height);
        //bDef.type = BodyDef.BodyType.DynamicBody;
        //createBody(world);
    }

    float maxVel = 20;

    @Override
    public void update(GameScreen gameScreen) {
        super.update(gameScreen);
    }
}
