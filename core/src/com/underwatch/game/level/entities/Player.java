package com.underwatch.game.level.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.underwatch.game.UnderwatchApp;
import com.underwatch.game.level.entities.characters.Hero;
import com.underwatch.util.MathUtil;

import static com.underwatch.game.UnderwatchApp.PPM;

/**
 * A client side way to control a Hero
 * Only one instance <code>Player</code> should be created.
 *
 * @author Blake
 * @see Hero
 */
public class Player {

    /**
     * The hero attached to the player
     */
    private Hero hero;

    /**
     * A camera which follows the player.
     */
    private OrthographicCamera camera;

    /**
     * Creates the controllable hero
     *
     * @param hero The hero which will be controlled
     */
    public Player(Hero hero) {
        this.hero = hero;
        InputAdapter gameInputController = new InputAdapter() {
            @Override
            public boolean keyUp(int keycode) {
                if (keycode == Input.Keys.A || keycode == Input.Keys.D) {
                    hero.handleMovement(Hero.MovementEvent.STOP_HORIZONTAL);
                    return true;
                }
                return false;
            }
        };
        UnderwatchApp.im.addProcessor(gameInputController);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, UnderwatchApp.V_WIDTH, UnderwatchApp.V_HEIGHT);
    }

    /**
     * Calls input and updates the Hero and Camera.
     */
    public void update(float dt) {
        input();
        hero.update(dt);
        Vector3 camPos = MathUtil.toVector3(hero.getBody().getPosition().cpy().scl(PPM),0);
        camera.position.lerp(camPos, 0.2f);
    }

    /**
     * Checks for key presses to move the hero.
     */
    private void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            hero.handleMovement(Hero.MovementEvent.RIGHT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            hero.handleMovement(Hero.MovementEvent.LEFT);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            hero.handleMovement(Hero.MovementEvent.JUMP);
        }
    }

    /**
     * Renders the Hero's sprite.
     *
     * @param spriteBatch Used to render the Hero's sprite.
     */
    public void render(SpriteBatch spriteBatch) {
        hero.render(spriteBatch);
    }

    /**
     * Disposes the hero when the program closes.
     */
    public void dispose() {
        hero.dispose();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
