package com.underwatch.game.level.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.underwatch.game.level.entities.Player;
import com.underwatch.game.level.entities.TestEntity;
import com.underwatch.game.level.entities.characters.Hero;
import com.underwatch.screens.GameScreen;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Blake on 10/07/2017.
 */
public class Level {

    private float width, height;
    private ArrayList<LevelPiece> staticPieces;
    private Floor floor;
    private Hero hero;
    private Player player;
    private Vector2 sp1, sp2;

    public Level(float width, float height, int generatedLevelPieces, World world) {
        staticPieces = new ArrayList<LevelPiece>();
        this.width = width;
        this.height = height;
        floor = new Floor(width, world);
        sp1 = new Vector2(2, 2);
        sp2 = new Vector2(width - 2, 2);
        hero = new Hero("badlogic.jpg","hahaha", sp1.x,sp1.y,world) {
            @Override
            public void onUltimateUsed() {

            }

            @Override
            public void onAbilityUsed() {

            }

            @Override
            public void shoot() {

            }
        };
        player = new Player("badlogic.jpg",hero);

        Random rnd = new Random();
        for (int i = 0; i < generatedLevelPieces / 2; i++) {
            float x = (float) rnd.nextGaussian() * (width / 2 - 1.5f) + width / 2;
            float y = rnd.nextInt((int) (height - 1)) + 2;

            staticPieces.add(new Platform(x, y, 1.5f, 0.4f, world));
            staticPieces.add(new Platform(width - x, y, 1.5f, 0.4f, world));
            float tx = x;
            float ty = y;


        }
    }

    public void update(float dt, GameScreen gameScreen){
        player.update(dt,gameScreen);
    }

    public void render(SpriteBatch batch) {
        player.render(batch);
    }

    public void dispose() {
        player.dispose();
    }


}
