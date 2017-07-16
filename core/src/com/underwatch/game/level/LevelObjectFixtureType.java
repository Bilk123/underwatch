package com.underwatch.game.level;

/**
 * A system for defining whether an entity is in contact with different structures.
 * e.g: WALL will not ground the entity where GROUND will.
 */
public enum LevelObjectFixtureType {
    GROUND(10f),
    HERO_BODY(1f),
    HERO_HEAD(1f),
    HERO_FEET(1f),
    WALL(0f);

    /**
     * The friction of the fixture.
     */
    public final float friction;

    /**
     * Sets friction.
     * @param friction The friction.
     */
    LevelObjectFixtureType(float friction){
        this.friction = friction;
    }

}
