package com.underwatch.game.level.entities.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.underwatch.game.UnderwatchApp;
import com.underwatch.game.level.entities.Entity;
import com.underwatch.screens.GameScreen;

public abstract class Hero extends Entity {
    public enum eTeam {
        RED, BLUE, BROWN, GOLD
    }

    public enum MovementEvent {
        LEFT, RIGHT, JUMP
    }

    protected Sprite headSprite;

    //modify these in the constructor of child classes
    protected eTeam team = eTeam.RED;
    protected int hitPoints = 100;//health
    protected int ammo = 20;
    protected float reloadTime = 1f;//1s
    protected int damage = 10;//hitpoints
    protected float speed= 1f;//m/s
    protected int armour=0;//if we decide to add armour
    protected float minCrosshairRadius = 10;//pixels
    protected float maxCrosshairRadius = 20;//pixels
    //////////////////////////////////////////////////

    // Movement shit
    private byte movementBits = 0b000;

    //Decided to make the bodies for all heroes identical
    public Hero(String bodySpriteImagePath, String headWeaponImagePath, float x, float y, World world) {
        super(bodySpriteImagePath, x, y, 2, 2f);
        //headSprite = new Sprite(new Texture(headWeaponImagePath));
        //headSprite.setSize(0.9f, 0.9f);//needs to be adjusted

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x, y);
        body = world.createBody(def);

        PolygonShape poly = new PolygonShape();
        poly.setAsBox(0.35f, 0.5f);
        Fixture torso = body.createFixture(poly, 1);
        fixtures.put("torso", torso);
        poly.dispose();

        CircleShape circle2 = new CircleShape();
        circle2.setRadius(0.25f);
        circle2.setPosition(new Vector2(0, 0.875f));
        Fixture head = body.createFixture(circle2, 0);
        fixtures.put("head", head);
        circle2.dispose();

        CircleShape circle = new CircleShape();
        circle.setRadius(0.35f);
        circle.setPosition(new Vector2(0, -0.5f));
        Fixture feet = body.createFixture(circle, 0);
        fixtures.put("feet", feet);
        circle.dispose();

        body.setBullet(true);
        body.setFixedRotation(true);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        //headSprite.draw(sb);

    }

    @Override
    protected float getMaxVel() {
        return 12;
    }

    public void handleMovement(MovementEvent me, GameScreen gameScreen) {
        switch (me) {
            case LEFT:
                if(body.getLinearVelocity().x > -getMaxVel()) {
                    movementBits |= 0b010;
                    fixtures.get("feet").setFriction(0);
                    body.applyLinearImpulse(-15, 0, body.getPosition().x, body.getPosition().y, true);
                    //body.applyForceToCenter(0, 20, true);
                }
                break;
            case RIGHT:
                if(body.getLinearVelocity().x < getMaxVel()) {
                    movementBits |= 0b100;
                    fixtures.get("feet").setFriction(0);
                    body.applyLinearImpulse(15, 0, body.getPosition().x, body.getPosition().y, true);
                    //body.applyForceToCenter(0, 20, true);
                }
                break;
            case JUMP:
                if(isGrounded(gameScreen)) {
                    movementBits |= 0b001;
                    fixtures.get("feet").setFriction(0);
                    body.applyLinearImpulse(0, 10, body.getPosition().x, body.getPosition().y, true);
                } else {
                    System.out.println("NOTJUMP");
                }
                break;
        }
    }

    @Override
    public void update(GameScreen gameScreen) {
        //super.update(gameScreen);
        if((movementBits) == 0) {
            fixtures.get("feet").setFriction(100F);
            body.setLinearVelocity(body.getLinearVelocity().x * 0.5f, body.getLinearVelocity().y);
        }
        movementBits = 0;
        float posX = body.getPosition().x - width / 2;
        float posY = body.getPosition().y - height / 2 + 3f / UnderwatchApp.PPM;//attempt to place the head on top of the body sprite
        float rotation = (float) Math.toDegrees(body.getAngle());//This value should be calculated from the mouse or passed through the server in the future
        bodySprite.setPosition(posX, posY);
        bodySprite.setRotation(rotation);
    }

    public abstract void onUltimateUsed();

    public abstract void onAbilityUsed();

    public abstract void shoot();


}