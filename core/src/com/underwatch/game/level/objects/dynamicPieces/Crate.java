package com.underwatch.game.level.objects.dynamicPieces;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.underwatch.game.level.GamePiece;
import com.underwatch.game.level.entities.EntityReference;
import com.underwatch.game.level.objects.MapPiece;

public class Crate extends MapPiece{
    public Crate(float x, float y, World world) {
        super(x, y, 3, 1, false, world);
        PolygonShape rect = new PolygonShape();
        rect.setAsBox(1.5f,0.5f);
        FixtureDef fDef = new FixtureDef();
        fDef.density=1f;
        fDef.friction=1f;
        fDef.shape = rect;
        body.createFixture(fDef).setUserData(new EntityReference(this, GamePiece.GROUND));
        rect.dispose();
    }
}
