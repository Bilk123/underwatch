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

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x, y);
        body = world.createBody(def);

        PolygonShape poly = new PolygonShape();
        poly.setAsBox(0.45f, 1f);
        Fixture torso = body.createFixture(poly, 1);
        fixtures.put("torso", torso);
        poly.dispose();

        CircleShape circle = new CircleShape();
        circle.setRadius(0.45f);
        circle.setPosition(new Vector2(0, -1f));
        Fixture feet = body.createFixture(circle, 0);
        fixtures.put("feet", feet);
        circle.dispose();

        body.setBullet(true);
        body.setFixedRotation(true);
    }

    float maxVel = 20;


    @Override
    public void update(GameScreen gameScreen) {
        super.update(gameScreen);
        if(Gdx.input.isKeyPressed(Input.Keys.D) && body.getLinearVelocity().x < maxVel) {
            body.applyLinearImpulse(15, 0, body.getPosition().x, body.getPosition().y, true);
            body.applyForceToCenter(0, 20, true);
            fixtures.get("feet").setFriction(0);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A) && body.getLinearVelocity().x > -maxVel) {
            body.applyLinearImpulse(-15, 0, body.getPosition().x, body.getPosition().y, true);
            body.applyForceToCenter(0, 20, true);
            fixtures.get("feet").setFriction(0);
        }

        if(!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A)) {
            body.setLinearVelocity(body.getLinearVelocity().x * 0.5f, body.getLinearVelocity().y);
            fixtures.get("feet").setFriction(1000F);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && isGrounded(gameScreen)) {
            body.applyLinearImpulse(0, 30, body.getPosition().x, body.getPosition().y, true);
        }
    }

    //  TODO this allows players to jump off others' heads. Maybe cool, maybe not
    public boolean isGrounded(GameScreen gameScreen) {
        Array<Contact> contacts = gameScreen.world.getContactList();
        for(Contact c : contacts) {
            if(c.isTouching() && c.getFixtureA() == fixtures.get("feet") || c.getFixtureB() == fixtures.get("feet")) {
                Vector2 pos = body.getPosition();
                WorldManifold manifold = c.getWorldManifold();
                boolean below = true;
                for(Vector2 p : manifold.getPoints()) {
                    below &= (p.y < pos.y - 1);
                }
                if(below) return true;
            }
        }
        return false;
    }
}
