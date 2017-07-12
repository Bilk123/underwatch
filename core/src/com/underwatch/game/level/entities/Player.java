package com.underwatch.game.level.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.underwatch.game.UnderwatchApp;
import com.underwatch.game.level.entities.characters.Hero;
import com.underwatch.screens.GameScreen;

//started player class
//the player will choose a hero and basically a controller will be attached to the chosen hero
public class Player{
    private Hero hero;
    private InputAdapter gameInputController;

    public Player(Hero hero) {
        this.hero = hero;
        gameInputController = new InputAdapter(){
            @Override
            public boolean keyUp(int keycode) {
                if(keycode == Input.Keys.A || keycode== Input.Keys.D){
                    hero.getBody().setLinearVelocity(0,hero.getBody().getLinearVelocity().y);
                    return true;
                }
                return false;
            }
        };
        UnderwatchApp.im.addProcessor(gameInputController);
    }

    public void update(float dt) {
        input();
        hero.update(dt);
    }

    public void input(){
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            hero.handleMovement(Hero.MovementEvent.RIGHT);
        } else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            hero.handleMovement(Hero.MovementEvent.LEFT);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            hero.handleMovement(Hero.MovementEvent.JUMP);
        }
    }

    public void render(SpriteBatch spriteBatch) {
        hero.render(spriteBatch);
    }

    public void dispose(){
        hero.dispose();
    }
}
