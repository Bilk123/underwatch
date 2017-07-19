package com.underwatch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.underwatch.game.UnderwatchApp;
import com.underwatch.game.level.entities.Entity;
import com.underwatch.game.level.Level;

import static com.underwatch.game.UnderwatchApp.PPM;

public class GameScreen extends UnderScreen {

    private World world;

    private Camera camera;

    private Box2DDebugRenderer b2dr;

    private boolean showDebugRenderer = true;

    private Level level;

    GameScreen(UnderwatchApp underwatchApp) {
        super(underwatchApp);
        b2dr = new Box2DDebugRenderer(true, true, false,false,true,true);
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -25f), false);
        level = new Level(50, 35, 30, world);
        camera = level.getPlayer().getCamera();
        underwatchApp.setProjectionMatrixFromCamera(camera);
    }

    @Override
    public void update(float dt) {
        world.step(1f / UnderwatchApp.APP_FPS, 6, 2);
        level.update(dt);
        camera.update();
        underwatchApp.setProjectionMatrixFromCamera(camera);
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) showDebugRenderer = !showDebugRenderer;
    }

    @Override
    public void render(float dt) {
        super.render(dt);

        underwatchApp.batch.begin();

        level.render(underwatchApp.batch);

        underwatchApp.batch.end();
        if (showDebugRenderer)
            b2dr.render(world, camera.combined.cpy().scl(PPM));
    }


    public void setCamera(Camera camera){
        this.camera= camera;
        underwatchApp.setProjectionMatrixFromCamera(camera);

    }

    @Override
    public void dispose() {
        level.dispose();
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
