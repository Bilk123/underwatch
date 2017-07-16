package com.underwatch.game.level.entities.characters;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.underwatch.game.level.LevelObjectFixtureType;
import com.underwatch.game.level.entities.Entity;
import com.underwatch.game.level.entities.LevelObjectReference;

import static com.underwatch.game.UnderwatchApp.PPM;

/**
 * The hero class is used to encapsulate all of the features and functions that in game characters
 * will have and be able to do.
 *
 * @see com.underwatch.game.level.entities.Entity
 * @see com.underwatch.game.level.LevelObject
 */
public abstract class Hero extends Entity {
    /**
     * enumerations which allow for different forms of input to move the Hero.
     */
    public enum MovementEvent {
        LEFT, RIGHT, STOP_HORIZONTAL, STOP, JUMP
    }

    /**The properties of the hero
     * @see HeroDef**/
    private HeroDef heroDef;

    /**A calculated scalar which the product of this and speed is the vertical velocity which produces the jumpHeight specified**/
    private final float speedScalar;

    /**
     * Creates a body with three fixtures, a head, a body, and feet
     * as well as the sprite for the character and sets the <code>speedScalar</code>
     * <p>
     * In each Fixture and Entity Reference is set as its user data, which is used to manage collisions with other <code>LevelObjects</code>.
     * @param spriteImagePath The image file path.
     * @param x The body's X component in the world.
     * @param y The body's Y component in the world.
     * @param heroDef The properties of the hero.
     * @param world The world the hero will be created in.
     * @see LevelObjectReference
     * @see Entity
     */
    protected Hero(String spriteImagePath, float x, float y, HeroDef heroDef, World world) {
        super(spriteImagePath,x,y, 2f, 2f, world);
        this.heroDef = heroDef;

        //Creates the body of the hero.
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(0.35f, 0.5f);
        Fixture torso = body.createFixture(poly, 1);
        torso.setUserData(new LevelObjectReference(this, LevelObjectFixtureType.HERO_BODY));
        poly.dispose();

        //Creates the head of the hero.
        CircleShape circle = new CircleShape();
        circle.setRadius(0.25f);
        circle.setPosition(new Vector2(0, 0.875f));
        Fixture head = body.createFixture(circle, 0);
        head.setUserData(new LevelObjectReference(this, LevelObjectFixtureType.HERO_HEAD));
        circle.dispose();

        //Creates the feet of the hero.
        CircleShape circle2 = new CircleShape();
        circle2.setRadius(0.35f + 1 / PPM);
        circle2.setPosition(new Vector2(0, -0.5f));
        Fixture feet = body.createFixture(circle2, 0);
        feet.setUserData(new LevelObjectReference(this, LevelObjectFixtureType.HERO_FEET));
        circle2.dispose();

        body.setBullet(true);
        body.setFixedRotation(true);

        //Calculates the speed scalar.
        speedScalar = (float) (Math.sqrt(-2 * (world.getGravity().y) * heroDef.jumpHeight) / heroDef.speed);
    }

    /**
     * Moves the hero depending on the <code>MovementEvent</code> by setting the linear velocity of the hero's body
     * @param me The type of movement the hero will move
     */
    public void handleMovement(MovementEvent me) {
        switch (me) {
            case LEFT:
                body.setLinearVelocity(-heroDef.speed, body.getLinearVelocity().y);
                break;
            case RIGHT:
                body.setLinearVelocity(heroDef.speed, body.getLinearVelocity().y);
                break;
            case JUMP:
                if (grounded)body.setLinearVelocity(body.getLinearVelocity().x, heroDef.speed * speedScalar);
                break;
            case STOP_HORIZONTAL:
                body.setLinearVelocity(0, body.getLinearVelocity().y);
                break;
            case STOP:
                body.setLinearVelocity(0,0);
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

    /**
     * Activates the hero's unique ultimate ability.
     */
    public abstract void useUltimate();

    /**
     * Activates the hero's standard ability.
     */
    public abstract void useAbility();

    /**
     * Shoots the hero's weapon.
     */
    public abstract void shoot();
}