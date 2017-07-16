package com.underwatch.game.level.entities;

import com.underwatch.game.level.LevelObjectFixtureType;
import com.underwatch.game.level.LevelObject;
import com.underwatch.game.level.objects.staticPieces.StaticMapPiece;

/**
 * A structure used to manage collisions between entities.
 * <p>
 *     The class will reference a LevelObject with a <code>LevelObjectFixtureType</code>,
 *     allowing for the different actions depending on where you contact the LevelObject
 * </p>
 *
 * @author Blake
 */
public class LevelObjectReference {

    /**The type of LevelObject which is being referenced**/
    private LevelObjectType levelObjectType;

    /**If the <code>LevelObjectType</code> is not <code>LevelObjectType.ENTITY</code> this field will be null**/
    private Entity entity;

    /**If the <code>LevelObjectType</code> is not <code>LevelObjectType.STATIC_MAP_PIECE</code> this field will be null**/
    private StaticMapPiece staticMapPiece;

    /**
     * All types of LevelObjects
     */
    private enum LevelObjectType {
        ENTITY, STATIC_MAP_PIECE
    }

    /**
     * The type of fixture used
     * @see LevelObjectFixtureType
     */
    private LevelObjectFixtureType piece;

    /**
     * Creates the reference.
     * @param levelObject The levelObjectBeingReferenced.
     * @param piece The type of fixture associated with the LevelObject.
     */
    public LevelObjectReference(LevelObject levelObject, LevelObjectFixtureType piece) {
        if (levelObject instanceof Entity) levelObjectType = LevelObjectType.ENTITY;
        else if (levelObject instanceof StaticMapPiece) levelObjectType = LevelObjectType.STATIC_MAP_PIECE;
        switch (levelObjectType) {
            case ENTITY:
                entity = (Entity) levelObject;
                break;
            case STATIC_MAP_PIECE:
                staticMapPiece = (StaticMapPiece) levelObject;
                break;
        }
        this.piece = piece;
    }

    /**
     * Returns the LevelObject being referenced if not null else an error is shown.
     * @return an instance of Entity if it can.
     */
    public Entity getEntity() {
        if (entity != null)
            return entity;
        else System.err.println("this is a reference to a: " + levelObjectType);
        return null;
    }

    /**
     * Returns the LevelObject being referenced if it is a StaticMapPiece else an error is shown and null is returned.
     * @return an instance of StaticMapPiece if it can.
     */
    public StaticMapPiece getStaticMapPiece() {
        if (staticMapPiece != null)
            return staticMapPiece;
        else System.err.println("this is a reference to a: " + levelObjectType);
        return null;
    }

    /**
     * @return The type of fixture.
     */
    public LevelObjectFixtureType getPiece() {
        return piece;
    }


    @Override
    public String toString() {
        return piece.toString();
    }
}
