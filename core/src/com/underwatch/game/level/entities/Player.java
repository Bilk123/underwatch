package com.underwatch.game.level.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.underwatch.game.level.entities.characters.Hero;
import com.underwatch.screens.GameScreen;

//started player class
//the player will choose a hero and basically a controller will be attached to the chosen hero
public class Player{
    //TODO: create user input and all of the player obvs
    private Hero hero;

    public Player(String spriteImgLoc, Hero hero) {
        this.hero = hero;
    }

    public void update(float dt, GameScreen gameScreen) {
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            hero.handleMovement(Hero.MovementEvent.RIGHT, gameScreen);
        } else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            hero.handleMovement(Hero.MovementEvent.LEFT, gameScreen);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            hero.handleMovement(Hero.MovementEvent.JUMP, gameScreen);
        }
        hero.update(gameScreen);
    }

    public void render(SpriteBatch spriteBatch) {
        hero.render(spriteBatch);
    }

    public void dispose(){
        hero.dispose();
    }
}
