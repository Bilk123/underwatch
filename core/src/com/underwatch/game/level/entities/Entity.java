package com.underwatch.game.level.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;


public abstract class Entity {
    protected Body body;

    protected Shape shape;
    protected Sprite bodySprite;
    protected float width, height;

    protected BodyDef bDef;
    protected FixtureDef fDef;

    //TODO: clean up constructor
    public Entity(String spriteImgLoc, float x, float y, float width, float height) {
        //BodyDef and FixtureDef to be specific to the entity inheriting from this
        this.width = width;
        this.height = height;
        bDef = new BodyDef();
        bDef.position.set(x,y);
        fDef = new FixtureDef();
        //bodySprite creation
        Texture img = new Texture(spriteImgLoc);
        bodySprite = new Sprite(img);
        bodySprite.setSize(width, height);
    }

    public void update(){
        //"attaches" the bodySprite to the body (don't know if this is the best way to do this)
        float posX = body.getPosition().x - width / 2;
        float posY = body.getPosition().y - height / 2;
        float rotation = (float) Math.toDegrees(body.getAngle());
        bodySprite.setPosition(posX, posY);
        bodySprite.setRotation(rotation);
    }

    public void render(SpriteBatch sb) {
        update();

        //draws this bodySprite
        bodySprite.draw(sb);
    }


    public void dispose() {
        bodySprite.getTexture().dispose();
    }

    public Body getBody() {
        return body;
    }

    //Call this after defining all the BodyDefs and FixtureDefs
    protected void createBody(World world){
        body = world.createBody(bDef);
        if(shape == null){
            PolygonShape rect = new PolygonShape();
            rect.setAsBox(width/2,height/2);
            fDef.shape = rect;
            System.out.println("no shape given, default shape assigned");
        }
        body.createFixture(fDef);
    }
}