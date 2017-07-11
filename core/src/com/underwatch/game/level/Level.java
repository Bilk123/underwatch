package com.underwatch.game.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.underwatch.game.level.entities.EntityReference;
import com.underwatch.game.level.entities.Player;
import com.underwatch.game.level.entities.characters.Hero;
import com.underwatch.game.level.objects.dynamicPieces.Crate;
import com.underwatch.game.level.objects.staticPieces.Block;
import com.underwatch.game.level.objects.staticPieces.Floor;
import com.underwatch.game.level.objects.MapPiece;

import java.util.ArrayList;

/**
 * Created by Blake on 10/07/2017.
 */
public class Level {

    private float width, height;
    private ArrayList<MapPiece> staticPieces;
    private Floor floor;
    private Hero hero;
    private Player player;
    private Vector2 sp1, sp2;
    private Block b1;
    private Crate crate;
    class CollisionListener implements ContactListener {

        @Override
        public void beginContact(Contact contact) {
            Object a = contact.getFixtureA().getUserData();
            Object b = contact.getFixtureB().getUserData();
            EntityReference ent1, ent2;
            if (a instanceof EntityReference) ent1 = (EntityReference) a;
            else return;
            if (b instanceof EntityReference) ent2 = (EntityReference) b;
            else return;

            setEntityGrounded(ent1, ent2, true);

 /*           //handles wall collisions for heroes;
            if (checkEntityReferenceForGamePiece(ent1, GamePiece.HERO_BODY, GamePiece.HERO_HEAD)&&
                    checkEntityReferenceForGamePiece(ent2, GamePiece.WALL)) {
                ent1.getEntity().setAgainstWall(true);
            } else if (checkEntityReferenceForGamePiece(ent2, GamePiece.HERO_BODY, GamePiece.HERO_HEAD)&&
                    checkEntityReferenceForGamePiece(ent1, GamePiece.WALL)) {
                ent2.getEntity().setAgainstWall(true);
            }*/
        }

        @Override
        public void endContact(Contact contact) {
            Object a = contact.getFixtureA().getUserData();
            Object b = contact.getFixtureB().getUserData();
            EntityReference ent1, ent2;
            if (a instanceof EntityReference) ent1 = (EntityReference) a;
            else return;
            if (b instanceof EntityReference) ent2 = (EntityReference) b;
            else return;
            setEntityGrounded(ent1, ent2, false);

    /*        //handles wall collisions for heroes;
            if (checkEntityReferenceForGamePiece(ent1, GamePiece.HERO_BODY, GamePiece.HERO_HEAD)&&
                    checkEntityReferenceForGamePiece(ent2, GamePiece.WALL)) {
                ent1.getEntity().setAgainstWall(false);
            } else if (checkEntityReferenceForGamePiece(ent2, GamePiece.HERO_BODY, GamePiece.HERO_HEAD)&&
                    checkEntityReferenceForGamePiece(ent1, GamePiece.WALL)) {
                ent2.getEntity().setAgainstWall(false);
            }*/

        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {

        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {

        }

        private boolean checkEntityReferenceForGamePiece(EntityReference ent1, GamePiece... gps) {
            for (int i = 0; i < gps.length; i++) {
                if (ent1.getPiece() == gps[i]) return true;
            }
            return false;
        }

        private void setEntityGrounded(EntityReference ent1, EntityReference ent2, boolean grounded){
            //handles floor collisions for heroes;
            if (checkEntityReferenceForGamePiece(ent1, GamePiece.HERO_FEET) &&
                    checkEntityReferenceForGamePiece(ent2, GamePiece.GROUND)) {
                ent1.getEntity().checkIfGrounded(grounded);
            } else if (checkEntityReferenceForGamePiece(ent2, GamePiece.HERO_FEET) &&
                    checkEntityReferenceForGamePiece(ent1, GamePiece.GROUND)) {
                ent2.getEntity().checkIfGrounded(grounded);
            }
        }
    }

    public Level(float width, float height, int generatedLevelPieces, World world) {
        staticPieces = new ArrayList<>();
        this.width = width;
        this.height = height;
        world.setContactListener(new CollisionListener());
        floor = new Floor(width, world);
        sp1 = new Vector2(2, 5);
        sp2 = new Vector2(width - 2, 2);
        hero = new Hero("sadfellow.png", sp1.x, sp1.y, world) {
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
        player = new Player(hero);
        b1 = new Block(5, 2f, world);
        crate = new Crate(5.5f,4f,world);
    }

    public void update(float dt) {
        player.update(dt);
    }

    public void render(SpriteBatch batch) {
        player.render(batch);
    }

    public void dispose() {
        player.dispose();
    }


}
