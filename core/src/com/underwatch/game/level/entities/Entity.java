package com.underwatch.game.level.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.OrderedMap;
import com.underwatch.game.level.LevelObject;

/**
 * inherited from <code>LevelObject</code>allows for rendering of sprites and creates a dynamic body to placed in a specified world.
 *
 * @see com.underwatch.game.level.LevelObject
 */
public abstract class Entity extends LevelObject{

    /**The sprite of the Entity*/
    protected Sprite bodySprite;

    /**Whether the body is in contact with a <code>LevelObject</code> with the tagged with the <code>LevelObjectType.GROUND</code>*/
    protected boolean grounded;

    public Entity(String spriteImgLoc,float x, float y, float width, float height, World world) {
        //BodyDef and FixtureDef to be specific to the entity inheriting from this
        this.width = width;
        this.height = height;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x, y);
        body = world.createBody(def);
        //bodySprite creation
        Texture img = new Texture(spriteImgLoc);
        bodySprite = new Sprite(img);
        bodySprite.setSize(width, height);
    }
    /**
     * Positions and rotates the <code>bodySprite</code> to match <code>body</code>
     */
    public void update(float dt){
        //"attaches" the bodySprite to the body (don't know if this is the best way to do this)
        float posX = body.getPosition().x - width / 2;
        float posY = body.getPosition().y - height / 2;
        float rotation = (float) Math.toDegrees(body.getAngle());
        bodySprite.setPosition(posX, posY);
        bodySprite.setRotation(rotation);
    }

    /**
     * Draws the entities Sprite.
     * @param sb Which SpriteBatch is used to render the <code>bodySprite</code>
     */
    public void render(SpriteBatch sb) {
        bodySprite.draw(sb);
    }

    /**
     * Garbage clean up when the program closes.
     */
    public void dispose() {
        bodySprite.getTexture().dispose();
    }

    /**
     * Sets the <code>grounded</code> field.
     */
    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }

}
