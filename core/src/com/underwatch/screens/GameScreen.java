package com.underwatch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.underwatch.game.UnderwatchApp;
import com.underwatch.game.level.entities.Entity;
import com.underwatch.game.level.entities.Player;
import com.underwatch.game.level.entities.TestEntity;
import com.underwatch.game.level.entities.characters.Hero;
import com.underwatch.game.level.objects.Floor;
import com.underwatch.game.level.objects.Level;
import com.underwatch.game.level.objects.LevelPiece;

import static com.underwatch.game.UnderwatchApp.PPM;

public class GameScreen extends UnderScreen {
    public World world;

    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;
    private boolean showDebugRenderer = true;
    private Level level;
    private Array<Entity> entities;

    class CollisionListener implements ContactListener {

        @Override
        public void beginContact(Contact contact) {
            Object a = contact.getFixtureA().getUserData();
            Object b = contact.getFixtureB().getUserData();

            if(a instanceof Hero) {
                if(b instanceof LevelPiece) {
                    ((Hero)a).setGrounded(true);
                }
            } else if(b instanceof Hero) {
                if(a instanceof LevelPiece) {
                    ((Hero)b).setGrounded(true);
                }
            }
        }

        @Override
        public void endContact(Contact contact) {
            Object a = contact.getFixtureA().getUserData();
            Object b = contact.getFixtureB().getUserData();

            System.out.println("end");

            if(a instanceof Hero) {
                if(b instanceof LevelPiece) {
                    ((Hero)a).setGrounded(false);
                }
            } else if(b instanceof Hero) {
                if(a instanceof LevelPiece) {
                    ((Hero)b).setGrounded(false);
                }
            }
        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {

        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {

        }
    }

    public GameScreen(UnderwatchApp underwatchApp) {
        super(underwatchApp);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, UnderwatchApp.V_WIDTH, UnderwatchApp.V_HEIGHT);
        b2dr = new Box2DDebugRenderer();
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -25f), false);
        underwatchApp.setProjectionMatrixFromCamera(camera);
        level = new Level(1080/PPM,720/PPM, 30,world);
        entities = new Array<>();
        world.setContactListener(new CollisionListener());
        //entities.add(new TestEntity(10,10,1,1,world));

    }

    @Override
    public void update(float dt) {
        world.step(1f / UnderwatchApp.APP_FPS, 6, 2);
        for(Entity entity : entities)
            entity.update(this);
        level.update(dt, this);
        //if (Gdx.input.isKeyJustPressed(Input.Keys.D)) showDebugRenderer = !showDebugRenderer;
    }

    @Override
    public void render(float dt) {
        super.render(dt);
        underwatchApp.batch.begin();
        //all entities and objects to be rendered here
        for(Entity entity : entities)
            entity.render(underwatchApp.batch);
        level.render(underwatchApp.batch);
        //--------------------------------------------
        underwatchApp.batch.end();

        if (showDebugRenderer)
            b2dr.render(world, camera.combined.cpy().scl(PPM));
    }


    @Override
    public void dispose() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
