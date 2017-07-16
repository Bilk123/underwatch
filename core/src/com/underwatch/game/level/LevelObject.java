package com.underwatch.game.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * A structure which defines all pieces which can be added to a level.
 *
 * @author Blake
 */
public abstract class LevelObject {
    /**
     * A box2D physical structure which has physics.
     */
    protected Body body;

    /**
     * The dimensions in meters of the physical object, used for positioning sprites.
     */
    protected float width, height;

    /**
     * Renders sprites
     * @param batch The batch which renders the sprite.
     */
    public abstract void render(SpriteBatch batch);

    /**
     * Updates sprites position and rotation.
     * @param dt The time taken to change a frame.
     */
    public abstract void update(float dt);

    /**
     * Removes resources correctly when necessary.
     */
    public abstract void dispose();

    /**
     * Returns the physics body.
     * @return The physical body of the LevelObject.
     */
    public Body getBody() {
        return body;
    }
}
