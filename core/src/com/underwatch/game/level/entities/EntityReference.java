package com.underwatch.game.level.entities;

import com.underwatch.game.level.GamePiece;
import com.underwatch.game.level.LevelObject;
import com.underwatch.game.level.objects.MapPiece;

public class EntityReference {
    private Entity entity;
    private MapPiece mapPiece;
    private LevelObjectType levelObjectType;

    private enum LevelObjectType {
        ENTITY, MAP_PIECE;
    }

    private GamePiece piece;

    public EntityReference(LevelObject levelObject, GamePiece piece) {
        if (levelObject instanceof Entity) levelObjectType = LevelObjectType.ENTITY;
        else if (levelObject instanceof MapPiece) levelObjectType = LevelObjectType.MAP_PIECE;

        switch (levelObjectType) {
            case ENTITY:
                entity = (Entity) levelObject;
                break;
            case MAP_PIECE:
                mapPiece = (MapPiece) levelObject;
                break;
        }
        this.piece = piece;
    }

    public Entity getEntity() {
        if (entity != null)
            return entity;
        else System.err.println("this is a reference to a: " + levelObjectType);
        return null;
    }

    public MapPiece getMapPiece() {
        if (mapPiece != null)
            return mapPiece;
        else System.err.println("this is a reference to a: " + levelObjectType);
        return null;
    }

    public GamePiece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return piece.toString();
    }
}
