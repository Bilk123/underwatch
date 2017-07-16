package com.underwatch.game.level;

public enum LevelObjectFixtureType {
    GROUND(10f),
    HERO_BODY(1f),
    HERO_HEAD(1f),
    HERO_FEET(1f),
    WALL(0f);

    public final float friction;

    LevelObjectFixtureType(float friction){
        this.friction = friction;
    }

}
