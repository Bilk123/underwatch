package com.underwatch.game.level.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Blake on 10/07/2017.
 */
public abstract class LevelPiece {
    protected Body body;
    protected static final float PIECE_FRICTION = 5f;
    protected static final float PIECE_DENSITY = 1f;


    public LevelPiece(float x, float y, float width, float height, World world) {
        body = world.createBody(createBodyDef(x, y));
        PolygonShape rect = new PolygonShape();
        rect.setAsBox(width / 2, height / 2);
        addFixture(rect);
        rect.dispose();

    }

    public LevelPiece(float x, float y, float radius, World world) {
        body = world.createBody(createBodyDef(x, y));
        CircleShape circle = new CircleShape();
        circle.setRadius(radius);
        addFixture(circle);
        circle.dispose();
    }

    public LevelPiece(float x, float y, Vector2[] verts, World world) {
        body = world.createBody(createBodyDef(x,y));
        ChainShape shape = new ChainShape();
        shape.createLoop(verts);
        addFixture(shape);
        shape.dispose();

    }

    private BodyDef createBodyDef(float x, float y) {
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.fixedRotation = true;
        bDef.position.set(x, y);
        return bDef;
    }

    private void addFixture(Shape shape) {
        FixtureDef fDef = new FixtureDef();
        fDef.friction = PIECE_FRICTION;
        fDef.density = PIECE_DENSITY;
        fDef.shape = shape;
        body.createFixture(fDef);
    }

    public void update() {

    }

    public void render() {

    }

    public void dispose(){

    }

}
