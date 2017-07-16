package com.underwatch.game.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.underwatch.game.level.entities.LevelObjectReference;
import com.underwatch.game.level.entities.Player;
import com.underwatch.game.level.entities.characters.Hero;
import com.underwatch.game.level.entities.characters.HeroDef;
import com.underwatch.game.level.objects.staticPieces.Block;
import com.underwatch.game.level.objects.staticPieces.Floor;
import com.underwatch.game.level.objects.staticPieces.StaticMapPiece;
import com.underwatch.game.level.objects.staticPieces.Platform;

import java.util.ArrayList;

/**
 * Created by Blake on 10/07/2017.
 */
public class Level {

    private float width, height;
    private ArrayList<StaticMapPiece> staticPieces;
    private Player player;

    public Player getPlayer() {
        return player;
    }

    class CollisionListener implements ContactListener {

        @Override
        public void beginContact(Contact contact) {
            Object a = contact.getFixtureA().getUserData();
            Object b = contact.getFixtureB().getUserData();
            LevelObjectReference ent1, ent2;
            if (a instanceof LevelObjectReference) ent1 = (LevelObjectReference) a;
            else return;
            if (b instanceof LevelObjectReference) ent2 = (LevelObjectReference) b;
            else return;
            setEntityGrounded(ent1, ent2, true);
        }

        @Override
        public void endContact(Contact contact) {
            Object a = contact.getFixtureA().getUserData();
            Object b = contact.getFixtureB().getUserData();
            LevelObjectReference ent1, ent2;
            if (a instanceof LevelObjectReference) ent1 = (LevelObjectReference) a;
            else return;
            if (b instanceof LevelObjectReference) ent2 = (LevelObjectReference) b;
            else return;
            setEntityGrounded(ent1, ent2, false);

        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {

        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {

        }

        private boolean checkEntityReferenceForGamePiece(LevelObjectReference ent1, LevelObjectFixtureType... gps) {
            for (LevelObjectFixtureType gp : gps) {
                if (ent1.getPiece() == gp) return true;
            }
            return false;
        }

        private void setEntityGrounded(LevelObjectReference ent1, LevelObjectReference ent2, boolean grounded) {
            //handles floor collisions for heroes;
            if (checkEntityReferenceForGamePiece(ent1, LevelObjectFixtureType.HERO_FEET) &&
                    checkEntityReferenceForGamePiece(ent2, LevelObjectFixtureType.GROUND)) {
                ent1.getEntity().setGrounded(grounded);
            } else if (checkEntityReferenceForGamePiece(ent2, LevelObjectFixtureType.HERO_FEET) &&
                    checkEntityReferenceForGamePiece(ent1, LevelObjectFixtureType.GROUND)) {
                ent2.getEntity().setGrounded(grounded);
            }
        }
    }

    public Level(float width, float height, int generatedLevelPieces, World world) {
        staticPieces = new ArrayList<>();
        this.width = width;
        this.height = height;
        world.setContactListener(new CollisionListener());
        new Floor(width, world);
        Vector2 sp1 = new Vector2(10, 7);
        Vector2 sp2 = new Vector2(width - 2, 2);
        Hero hero = new Hero("sadfellow.png", sp1.x, sp1.y, new HeroDef(), world) {
            @Override
            public void useUltimate() {

            }

            @Override
            public void useAbility() {

            }

            @Override
            public void shoot() {

            }
        };
        player = new Player(hero);
        staticPieces.add(new Block(4, 1.5f, world));
    }

    public void update(float dt) {
        for (StaticMapPiece pieces : staticPieces) {
            pieces.update(dt);
        }
        player.update(dt);
    }

    public void render(SpriteBatch batch) {
        for (StaticMapPiece staticPiece : staticPieces) {
            staticPiece.render(batch);
        }
        player.render(batch);
    }

    public void dispose() {
        for (StaticMapPiece staticPiece : staticPieces) {
            staticPiece.dispose();
        }
        player.dispose();
    }


}
