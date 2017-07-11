package com.underwatch.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.underwatch.game.UnderwatchApp;
import com.underwatch.game.level.entities.Entity;
import com.underwatch.game.level.Level;

import static com.underwatch.game.UnderwatchApp.PPM;

public class GameScreen extends UnderScreen {
    public World world;

    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;
    private boolean showDebugRenderer = true;
    private Level level;
    private Array<Entity> entities;



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
        level = new Level(1080 / PPM, 720 / PPM, 30, world);
        entities = new Array<>();
        //entities.add(new TestEntity(10,10,1,1,world));

    }

    @Override
    public void update(float dt) {
        world.step(1f / UnderwatchApp.APP_FPS, 6, 2);
        for (Entity entity : entities)
            entity.update(dt);
        level.update(dt);
        //if (Gdx.input.isKeyJustPressed(Input.Keys.D)) showDebugRenderer = !showDebugRenderer;
    }

    @Override
    public void render(float dt) {
        super.render(dt);
        underwatchApp.batch.begin();
        //all entities and objects to be rendered here
        for (Entity entity : entities)
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
