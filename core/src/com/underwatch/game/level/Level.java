package com.underwatch.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.underwatch.game.level.entities.LevelObjectReference;
import com.underwatch.game.level.entities.Player;
import com.underwatch.game.level.entities.characters.Hero;
import com.underwatch.game.level.entities.characters.HeroDef;
import com.underwatch.game.level.objects.staticPieces.Block;
import com.underwatch.game.level.objects.staticPieces.Floor;
import com.underwatch.game.level.objects.staticPieces.StaticMapPiece;
import com.underwatch.util.RenderUtil;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Generates a dynamic map, and houses a player instance.
 *
 * @author Blake
 */
public class Level {


    /**
     * The dimensions in meters of the generated map.
     */
    private float width, height;

    /**
     * A list of all static pieces in the map, for rendering/updating/disposing
     */
    private ArrayList<StaticMapPiece> staticPieces;

    /**
     * The Player.
     */
    private Player player;

    /**
     * The background sprite of for the map.
     */
    private Sprite background;

    /**
     * @return The player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Creates the Player and Map.
     * @param width The width in meters of the map.
     * @param height The height in meters of the map.
     * @param generatedLevelPieces How many static pieces will be generated.
     * @param world The world which all entities and staticPieces will be added to.
     */
    public Level(float width, float height, int generatedLevelPieces, World world) {
        staticPieces = new ArrayList<>();
        this.width = width;
        this.height = height;
        world.setContactListener(new CollisionListener());
        staticPieces.add(new Floor(width, world));
        Vector2 sp1 = new Vector2(10, 7);
        Vector2 sp2 = new Vector2(width - 2, 2);
        Hero hero = new Hero("sprites/sadfellow.png", sp1.x, sp1.y, new HeroDef(), world) {
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
        Texture texture = new Texture("sprites/forest-background.png");
        background = new Sprite(texture);
        background.setSize(width*2, height*2);
        background.setPosition(-width/2, -height/4);
    }

    /**
     * An inner class which links collisions with dynamic and static pieces.
     */
    class CollisionListener implements ContactListener {
        /**
         * Sets entities grounded if the contact is between the correct two fixtures.
         * @param contact The contact between two fixtures in a box2D world.
         */
        @Override
        public void beginContact(Contact contact) {
            Object a = contact.getFixtureA().getUserData();
            Object b = contact.getFixtureB().getUserData();
            LevelObjectReference levelObjectReference1, levelObjectReference2;
            if (a instanceof LevelObjectReference) levelObjectReference1 = (LevelObjectReference) a;
            else return;
            if (b instanceof LevelObjectReference) levelObjectReference2 = (LevelObjectReference) b;
            else return;
            System.out.println("contact begun: " +levelObjectReference1.toString()+ " & "+ levelObjectReference2.toString());
            setEntityGrounded(levelObjectReference1, levelObjectReference2, true);
        }

        /**
         * Sets entities grounded field to false when contact ends between two specified fixtures.
         * @param contact The contact between two fixtures in a box2D world.
         */
        @Override
        public void endContact(Contact contact) {
            Object a = contact.getFixtureA().getUserData();
            Object b = contact.getFixtureB().getUserData();
            LevelObjectReference levelObjectReference1, levelObjectReference2;
            if (a instanceof LevelObjectReference) levelObjectReference1 = (LevelObjectReference) a;
            else return;
            if (b instanceof LevelObjectReference) levelObjectReference2 = (LevelObjectReference) b;
            else return;
            System.out.println("contact ended: " +levelObjectReference1.toString()+ " & "+ levelObjectReference2.toString());
            setEntityGrounded(levelObjectReference1, levelObjectReference2, false);

        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {

        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {

        }

        /**
         * Checks if LevelObjectReferences reference the correct fixtureType for
         * both fixtures and sets the entity's grounded field accordingly.
         * @param levelObjectReference1 The first LevelObjectReference.
         * @param levelObjectReference2 The second LevelObjectReference.
         * @param grounded Whether the grounded field for the entity will be set to true or false.
         */
        private void setEntityGrounded(LevelObjectReference levelObjectReference1, LevelObjectReference levelObjectReference2, boolean grounded) {
            //handles floor collisions for heroes;
            if (checkEntityReferenceForGamePiece(levelObjectReference1, LevelObjectFixtureType.HERO_FEET) &&
                    checkEntityReferenceForGamePiece(levelObjectReference2, LevelObjectFixtureType.GROUND)) {
                levelObjectReference1.getEntity().setGrounded(grounded);
            } else if (checkEntityReferenceForGamePiece(levelObjectReference2, LevelObjectFixtureType.HERO_FEET) &&
                    checkEntityReferenceForGamePiece(levelObjectReference1, LevelObjectFixtureType.GROUND)) {
                levelObjectReference2.getEntity().setGrounded(grounded);
            }
        }

        /**
         * Compares the LevelObjectFixtureType from one LevelObjectReference with a list of specified LevelObjectFixtureTypes
         * and returns true if any elements match.
         * @param levelObjectReference The reference to the single LevelObject.
         * @param gps The list of all GamePieces.
         * @return Whether the LevelObjectReference's GamePiece matches any of elements of the list.
         */
        private boolean checkEntityReferenceForGamePiece(LevelObjectReference levelObjectReference, LevelObjectFixtureType... gps) {
            for (LevelObjectFixtureType gp : gps) {
                if (levelObjectReference.getPiece() == gp) return true;
            }
            return false;
        }
    }

    /**
     * updates all levelObjects.
     * @param dt The time from the previous frame.
     */
    public void update(float dt) {
        for (StaticMapPiece pieces : staticPieces) {
            pieces.update(dt);
        }
        player.update(dt);

    }

    /**
     * renders all LevelObjects.
     * @param batch The batch to render all levelObjects.
     */
    public void render(SpriteBatch batch) {
        background.draw(batch);
        for (StaticMapPiece staticPiece : staticPieces) {
            staticPiece.render(batch);
        }
        player.render(batch);

    }

    /**
     * removes resources correctly when necessary.
     */
    public void dispose() {
        background.getTexture().dispose();
        for (StaticMapPiece staticPiece : staticPieces) {
            staticPiece.dispose();
        }
        player.dispose();

    }


}
