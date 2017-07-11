package com.underwatch.game.level.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.underwatch.game.level.entities.characters.Hero;
import com.underwatch.screens.GameScreen;

//started player class
//the player will choose a hero and basically a controller will be attached to the chosen hero
public class Player{
    private Hero hero;

    public Player(Hero hero) {
        this.hero = hero;
    }

    public void update(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            hero.handleMovement(Hero.MovementEvent.RIGHT);
        } else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            hero.handleMovement(Hero.MovementEvent.LEFT);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            hero.handleMovement(Hero.MovementEvent.JUMP);
        }
        hero.update(dt);
    }

    public void render(SpriteBatch spriteBatch) {
        hero.render(spriteBatch);
    }

    public void dispose(){
        hero.dispose();
    }
}
