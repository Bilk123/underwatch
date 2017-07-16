package com.underwatch.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MathUtil {
    private MathUtil() {
    }

    public static Vector3 toVector3(Vector2 vec, float z) {
        return new Vector3(vec.x, vec.y, z);
    }

}
