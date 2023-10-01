package com.aquilla.ludumdare.util;

import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.entity.Entity;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;

public final class CollisionHandler {

    public static boolean areHitboxesColliding(Rectangle hitbox1, Rectangle hitbox2) {
        return hitbox1.overlaps(hitbox2);
    }

    public static boolean isCollidingWithMapObject(Entity e) {
        for (RectangleMapObject object : Assets.getInstance()
                .getTiledMap("map")
                .getLayers()
                .get("collisions")
                .getObjects()
                .getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();
            if (e.getHitBox().overlaps(rect)) {
                return true;
            }
        }

        return false;
    }
}
