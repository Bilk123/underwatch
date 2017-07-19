package com.underwatch.game.level.objects.staticPieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.underwatch.game.level.LevelObjectFixtureType;
import com.underwatch.game.level.LevelObject;
import com.underwatch.game.level.entities.LevelObjectReference;
import com.underwatch.util.RenderUtil;

import java.util.ArrayList;

import static com.underwatch.game.UnderwatchApp.PPM;

/**
 * Static pieces which build up the map of the level
 * Handles rendering of sprites and updates bodies.
 *
 * @author Blake
 */
public abstract class StaticMapPiece extends LevelObject {


    /**The thickness of padding for wall and ground fixture types in meters**/
    private static final float PADDING_THICKNESS = 1f / PPM;

    /**The sprite of the static piece**/
    private Sprite sprite;

    /**
     * Creates a Rectangular static piece in a specified world.
     * @param x The world x coordinate.
     * @param y The world y coordinate.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param spriteFileName The sprite's image name.
     * @param world The world the piece will be put in.
     */
    StaticMapPiece(float x, float y, float width, float height, String spriteFileName, World world, boolean repeatTexture) {
        body = world.createBody(createStaticBodyDef(x, y));
        this.width = width;
        this.height = height;

        PolygonShape rect = new PolygonShape();
        rect.setAsBox(width / 2, height / 2);
        body.createFixture(rect, 0f);
        Vector2 p1 = new Vector2(), p2 = new Vector2();
        for (int i = 0; i < 4; i++) {
            rect.getVertex(i, p1);
            rect.getVertex((i + 1) % 4, p2);
            createPadding(p1,p2);
        }
        rect.dispose();

        Texture texture = new Texture(spriteFileName);
        if(!repeatTexture) {
            sprite = new Sprite(texture);
            sprite.setSize(width, height);
        }else{
            sprite = RenderUtil.repeatTexturesInRectangle(texture,width,height);
        }
        sprite.setPosition(x-width/2, y-height/2);
    }

    /**
     * Creates a static piece in the world which it's shape is generated from a shapeGenFile png
     * @param x The world x coordinate.
     * @param y The world y coordinate.
     * @param spriteFileName The sprite's image name.
     * @param shapeGenFileName The png which generates the shape of the piece.
     * @param world The world the piece will be put in.
     */
    StaticMapPiece(float x, float y, String spriteFileName, String shapeGenFileName, World world) {
        body = world.createBody(createStaticBodyDef(x, y));

        Pixmap map = new Pixmap(Gdx.files.internal(shapeGenFileName));
        ChainShape newShape = new ChainShape();
        ArrayList<Vector2> vertsTemp = new ArrayList<>();
        width = map.getWidth() / PPM;
        height = map.getHeight() / PPM;
        int checkColor = 0x000000;//can be changed to what we want
        for (int my = 0; my < map.getHeight(); my++) {
            for (int mx = 0; mx < map.getWidth(); mx++) {
                int col = (map.getPixel(mx, my) & 0xffffff00) >> 8;
                if (col == checkColor) {
                    checkColor++;
                    vertsTemp.add(new Vector2(mx / PPM, (map.getHeight() - my) / PPM));
                    mx = 0;
                    my = 0;
                }
            }
        }

        Vector2[] vertices = new Vector2[vertsTemp.size()];
        vertsTemp.toArray(vertices);
        newShape.createLoop(vertices);
        body.createFixture(newShape, 0f);

        Vector2 p1 = new Vector2(), p2 = new Vector2();
        for (int i = 0; i < newShape.getVertexCount(); i++) {
            newShape.getVertex(i, p1);
            newShape.getVertex((i + 1) % newShape.getVertexCount(), p2);
            createPadding(p1, p2);
        }

        Texture texture = new Texture(spriteFileName);
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        sprite.setPosition(x, y);

        newShape.dispose();
        map.dispose();
    }

    /**
     * Creates a fixture of thickness PADDING_THICKNESS which acts as either a wall or the ground
     * @param p1 Point 1, Vertex from a shape.
     * @param p2 Point 2, Another Vertex from a shape.
     */
    private void createPadding(Vector2 p1, Vector2 p2) {
        float angle = p1.cpy().sub(p2).angle();
        Vector2 mid = (p1.cpy().add(p2)).scl(0.5f);
        float w = p1.dst(p2);
        FixtureDef fDef = new FixtureDef();
        PolygonShape rect = new PolygonShape();
        rect.setAsBox(w / 2, PADDING_THICKNESS / 2, mid, (float) Math.toRadians(angle));
        fDef.shape = rect;
        fDef.density = 0f;
        System.out.println(p1+" "+p2);
        if (Math.abs(Math.sin(Math.toRadians(angle))) > Math.sin(Math.toRadians(75))) {
            fDef.friction = LevelObjectFixtureType.WALL.friction;
            System.out.println("is wall");
            body.createFixture(fDef).setUserData(new LevelObjectReference(this, LevelObjectFixtureType.WALL));
        } else {
            fDef.friction = LevelObjectFixtureType.GROUND.friction;
            System.out.println("is ground");
            body.createFixture(fDef).setUserData(new LevelObjectReference(this, LevelObjectFixtureType.GROUND));
        }
    }

    @Override
    public void update(float dt) {

    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    private BodyDef createStaticBodyDef(float x, float y) {
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.fixedRotation = true;
        bDef.position.set(x + width / 2, y + height / 2);
        return bDef;
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }

}
