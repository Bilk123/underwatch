package com.underwatch.game.level.entities.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.underwatch.game.level.entities.Entity;

public abstract class Hero extends Entity {
    public enum eTeam {
        RED, BLUE, BROWN, GOLD
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

    //Decided to make the bodies for all heroes identical
    public Hero(String bodySpriteImagePath, String headWeaponImagePath, float x, float y, World world) {
        super(bodySpriteImagePath, x, y, 1, 2f);
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(x, y);
        body = world.createBody(bDef);
        PolygonShape rect = new PolygonShape();
        rect.setAsBox(width*0.9f/2, height*0.9f/2);//needs to be tweaked probably
        body.createFixture(rect, 1.0f);

        headSprite = new Sprite(new Texture(headWeaponImagePath));
        headSprite.setSize(0.25f, 0.4f);//needs to be adjusted
        //TODO: center the sprite around the head
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        headSprite.draw(sb);

    }

    @Override
    public void update() {
        super.update();
        float posX = body.getPosition().x - width / 2;
        float posY = body.getPosition().y + height / 2 + 0.25f;//attempt to place the head on top of the body sprite
        float rotation = (float) Math.toDegrees(body.getAngle());//This value should be calculated from the mouse or passed through the server in the future
        headSprite.setPosition(posX, posY);
        headSprite.setRotation(rotation);
    }

    public abstract void onUltimateUsed();

    public abstract void onAbilityUsed();

    public abstract void shoot();


}