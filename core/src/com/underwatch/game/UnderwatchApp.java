package com.underwatch.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.underwatch.game.ui.UIInputController;
import com.underwatch.screens.UnderwatchScreenManager;

/**
 * Extends Game to allow the use of the built in Screen interface of libGDX
 * Stores config features which link to other modules in the project.
 * Stores inputListeners and batches for rendering
 */
public class UnderwatchApp extends Game {

    /**
     * The window or app title.
     */
    public static String APP_TITLE = "Underwatch";

    /**
     * The width of desktop window in pixels.
     */
    public static int DESKTOP_WIDTH = 1080;

    /**
     * The height of the desktop window in pixels.
     */
    public static int DESKTOP_HEIGHT = 720;

    /**
     * The constant fps of the program which libGDX will try maintain.
     */
    public static int APP_FPS = 60;

    //Game variables
    /**
     * Pixels per meter a scaling constant used to convert world coordinates to pixel coordinates.
     */
    public static float PPM = 32F;

    //allows for different sized screens, but the same display scaled
    /**
     * Viewport width, the width which the camera will 'view'.
     */
    public static int V_WIDTH = (int) (1080 / 1.6f);

    /**
     * Viewport height, the height which the camera 'views'.
     */
    public static int V_HEIGHT = (int) (720 / 1.6f);

    //Managers
    /**
     * Manages which screen will be drawn and updated.
     */
    private UnderwatchScreenManager usm;

    /**
     * The input controlled for android or desktop.
     */
    public static InputMultiplexer im = new InputMultiplexer();

    //Batches
    /**
     * sprite batch renders sprites and textures.
     */
    public SpriteBatch batch;

    /**
     * Renders basic shapes. debug uses mainly.
     */
    public ShapeRenderer shapeBatch;


    //initialises managers and batches
    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeBatch = new ShapeRenderer();
        usm = new UnderwatchScreenManager(this);

        im.addProcessor(new UIInputController());
        Gdx.input.setInputProcessor(im);
    }

    //renders the screen which app.screen is set to
    @Override
    public void render() {
        super.render();
    }

    //disposes thingys duh
    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        shapeBatch.dispose();
        usm.dispose();
    }

    //connects the batches to a camera
    public void setProjectionMatrixFromCamera(Camera camera) {
        //this implements the PPM, so the camera viewport is (V_WIDTH, V_HEIGHT) pixels or (V_WIDTH/PPM, V_HEIGHT/PPM)'meters'
        //this makes the 120m/s speed high enough even for bullets(maybe)
        batch.setProjectionMatrix(camera.combined.cpy().scl(PPM));
        shapeBatch.setProjectionMatrix(camera.combined.cpy().scl(PPM));
    }
}
