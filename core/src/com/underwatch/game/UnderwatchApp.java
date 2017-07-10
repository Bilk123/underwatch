package com.underwatch.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.underwatch.screens.UnderwatchScreenManager;

// TODO font scaling is rubbish. Decide which sizes we need!
//extends Game to allow the use of the built in Screen interface of libGDX
public class UnderwatchApp extends Game {

	//App variables to tie with desktop config
	public static String APP_TITLE = "Underwatch";
	public static int DESKTOP_WIDTH = 1080;
	public static int DESKTOP_HEIGHT = 720;
	public static int APP_FPS = 60;

	//Game variables
	public static int PPM = 64; //(pixels per metre)
	//allows for different sized screens, but the same display scaled
	public static int V_WIDTH = 1080;
	public static int V_HEIGHT = 720;

	//Managers
	//public AssetManager assets;
	public UnderwatchScreenManager usm;

	//Batches
	public SpriteBatch batch;
	public ShapeRenderer shapeBatch;


	//initialises managers and batches
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeBatch = new ShapeRenderer();
		//assets = new AssetManager();
		usm = new UnderwatchScreenManager(this);
	}

	//renders the screen which app.screen is set to
	@Override
	public void render () {
		super.render();
	}

	//disposes thingys duh
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		shapeBatch.dispose();
		//assets.dispose();
		usm.dispose();
	}

	//connects the batches to a camera
	public void setProjectionMatrixFromCamera(Camera camera){
		//this implements the PPM, so the camera viewport is (V_WIDTH, V_HEIGHT) pixels or (V_WIDTH/PPM, V_HEIGHT/PPM)'meters'
		//this makes the 120m/s speed high enough even for bullets(maybe)
		batch.setProjectionMatrix(camera.combined.cpy().scl(PPM));
		shapeBatch.setProjectionMatrix(camera.combined.cpy().scl(PPM));
	}
}
