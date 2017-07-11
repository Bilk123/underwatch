package com.underwatch.game.level.entities.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.underwatch.game.UnderwatchApp;
import com.underwatch.game.level.GamePiece;
import com.underwatch.game.level.entities.Entity;
import com.underwatch.game.level.entities.EntityReference;

import static com.underwatch.game.UnderwatchApp.PPM;

public abstract class Hero extends Entity {
    public enum Team {
        RED, BLUE, BROWN, GOLD
    }

    public enum MovementEvent {
        LEFT, RIGHT, JUMP
    }


    //modify these in the constructor of child classes
    protected Team team = Team.RED;
    protected int hitPoints = 100;//health
    protected int ammo = 20;
    protected int rayCastsPerShot = 1;//a shotgun would have more
    protected float reloadTime = 1f;//1s
    protected int damage = 10;//hitpoints per hit raycast
    protected float speed = 5f;//m/s
    protected float jumpHeight = 2.5f;//meters;
    protected int armour = 0;//if we decide to add armour
    protected float minCrosshairRadius = 10;//pixels
    protected float maxCrosshairRadius = 20;//pixels
    //////////////////////////////////////////////////

    private float speedScalar;

    //Decided to make the bodies for all heroes identical
    public Hero(String spriteImagePath, float x, float y, World world) {
        super(spriteImagePath, 2f, 2f);
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x, y);
        body = world.createBody(def);

        PolygonShape poly = new PolygonShape();
        poly.setAsBox(0.35f, 0.5f);
        Fixture torso = body.createFixture(poly, 1);
        fixtures.put("torso", torso);
        torso.setUserData(new EntityReference(this, GamePiece.HERO_BODY));
        poly.dispose();

        CircleShape circle = new CircleShape();
        circle.setRadius(0.25f);
        circle.setPosition(new Vector2(0, 0.875f));
        Fixture head = body.createFixture(circle, 0);
        head.setUserData(new EntityReference(this, GamePiece.HERO_HEAD));
        fixtures.put("head", head);
        circle.dispose();

        CircleShape circle2 = new CircleShape();
        circle2.setRadius(0.35f + 1 / PPM);
        circle2.setPosition(new Vector2(0, -0.5f));
        Fixture feet = body.createFixture(circle2, 0);
        feet.setUserData(new EntityReference(this, GamePiece.HERO_FEET));
        fixtures.put("feet", feet);
        circle2.dispose();

        body.setBullet(true);
        body.setFixedRotation(true);

        speedScalar = (float) (Math.sqrt(-2 * (world.getGravity().y) * jumpHeight) / speed);
    }

    @Override
    public void render(SpriteBatch sb) {
        //super.render(sb);
        //headSprite.draw(sb);
    }

    public void handleMovement(MovementEvent me) {
        switch (me) {
            case LEFT:
                body.setLinearVelocity(-speed, body.getLinearVelocity().y);
                break;
            case RIGHT:
                body.setLinearVelocity(speed, body.getLinearVelocity().y);
                break;
            case JUMP:
                if (grounded) { body.setLinearVelocity(body.getLinearVelocity().x, speed * speedScalar); }
                break;
        }
    }

    @Override
    public void update(float dt) {
        float posX = body.getPosition().x - width / 2;
        float posY = body.getPosition().y - height / 2 + 3f / PPM;
        float rotation = (float) Math.toDegrees(body.getAngle());//This value should be calculated from the mouse or passed through the server in the future
        bodySprite.setPosition(posX, posY);
        bodySprite.setRotation(rotation);
    }

    public abstract void onUltimateUsed();

    public abstract void onAbilityUsed();

    public abstract void shoot();

}