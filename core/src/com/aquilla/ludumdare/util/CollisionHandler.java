package com.aquilla.ludumdare.util;

import com.badlogic.gdx.math.Rectangle;

public final class CollisionHandler {

    public static boolean areHitboxesColliding(Rectangle hitbox1, Rectangle hitbox2) {
        return hitbox1.overlaps(hitbox2);
    }
}
