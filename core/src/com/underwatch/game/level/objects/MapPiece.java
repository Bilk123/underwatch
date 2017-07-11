package com.underwatch.game.level.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.underwatch.game.level.GamePiece;
import com.underwatch.game.level.LevelObject;
import com.underwatch.game.level.entities.EntityReference;

import static com.underwatch.game.UnderwatchApp.PPM;

/**
 * Created by Blake on 10/07/2017.
 */
public abstract class MapPiece extends LevelObject {

    protected static final float PIECE_FRICTION = 10f;
    protected static final float PIECE_DENSITY = 1f;
    protected static final float PADDING_THICKNESS = 1f / PPM;

    public MapPiece(float x, float y, float width, float height, boolean isStatic, World world) {
        this.width = width;
        this.height = height;
        if (isStatic) {
            body = world.createBody(createStaticBodyDef(x, y));
            PolygonShape center = new PolygonShape();
            center.setAsBox(width / 2 - PADDING_THICKNESS, height / 2 - PADDING_THICKNESS);
            addFixture(center);

            PolygonShape top = new PolygonShape();
            top.setAsBox(width / 2 - PADDING_THICKNESS, PADDING_THICKNESS / 2, new Vector2(0, height / 2 - PADDING_THICKNESS / 2f), 0);
            addFixture(top, new EntityReference(this, GamePiece.GROUND));

            PolygonShape left = new PolygonShape();
            left.setAsBox(PADDING_THICKNESS / 2, height / 2, new Vector2(-width / 2 + PADDING_THICKNESS / 2, 0), 0);
            addFixture(left, new EntityReference(this, GamePiece.WALL));

            PolygonShape right = new PolygonShape();
            right.setAsBox(PADDING_THICKNESS / 2, height / 2, new Vector2(width / 2 - PADDING_THICKNESS / 2, 0), 0);
            addFixture(right, new EntityReference(this, GamePiece.WALL));

            PolygonShape bot = new PolygonShape();
            bot.setAsBox(width / 2 - PADDING_THICKNESS, PADDING_THICKNESS / 2, new Vector2(0, -(height / 2 - PADDING_THICKNESS / 2f)), 0);
            addFixture(bot);
        } else {
            body = world.createBody(createDynamicBodyDef(x, y));
        }
    }


    private BodyDef createStaticBodyDef(float x, float y) {
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.fixedRotation = true;
        bDef.position.set(x, y);
        return bDef;
    }

    private BodyDef createDynamicBodyDef(float x, float y) {
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.fixedRotation = true;
        bDef.position.set(x, y);
        return bDef;
    }

    protected void addFixture(Shape shape) {
        FixtureDef fDef = new FixtureDef();
        fDef.friction = PIECE_FRICTION;
        fDef.density = PIECE_DENSITY;
        fDef.shape = shape;
        body.createFixture(fDef);
        shape.dispose();
    }

    protected void addFixture(Shape shape, Object userData) {
        FixtureDef fDef = new FixtureDef();
        fDef.friction = PIECE_FRICTION;
        fDef.density = PIECE_DENSITY;
        fDef.shape = shape;
        body.createFixture(fDef).setUserData(userData);
        shape.dispose();
    }

    public void update(float dt) {

    }

    public void render(SpriteBatch batch) {

    }

    public void dispose() {

    }

}
