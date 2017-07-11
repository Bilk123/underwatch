package com.underwatch.game.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class LevelObject {
    protected Body body;
    protected float width, height;

    public abstract void render(SpriteBatch batch);

    public abstract void update(float dt);

    public abstract void dispose();
}
