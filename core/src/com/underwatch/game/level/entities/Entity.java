package com.underwatch.game.level.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.OrderedMap;
import com.underwatch.game.level.LevelObject;


public abstract class Entity extends LevelObject{

    protected Sprite bodySprite;
    protected OrderedMap<String, Fixture> fixtures;
    protected boolean grounded;
    protected boolean againstWall;

    public Entity(String spriteImgLoc, float width, float height) {
        //BodyDef and FixtureDef to be specific to the entity inheriting from this
        this.width = width;
        this.height = height;

        //bodySprite creation
        Texture img = new Texture(spriteImgLoc);
        bodySprite = new Sprite(img);
        bodySprite.setSize(width, height);
        fixtures = new OrderedMap<>();
    }

    public void update(float dt){
        //"attaches" the bodySprite to the body (don't know if this is the best way to do this)
        float posX = body.getPosition().x - width / 2;
        float posY = body.getPosition().y - height / 2;
        float rotation = (float) Math.toDegrees(body.getAngle());
        bodySprite.setPosition(posX, posY);
        bodySprite.setRotation(rotation);
    }

    public void render(SpriteBatch sb) {
        //draws this bodySprite
        bodySprite.draw(sb);
    }

    public void dispose() {
        bodySprite.getTexture().dispose();
    }

    public void checkIfGrounded(boolean grounded) {
        this.grounded = grounded;
    }

    public void setAgainstWall(boolean againstWall){
        this.againstWall = againstWall;
    }

    public Body getBody() {
        return body;
    }
}
