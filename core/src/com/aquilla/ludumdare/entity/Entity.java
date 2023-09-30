package com.aquilla.ludumdare.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    private Sprite sprite;
    private final Vector2 pos;
    private Vector2 vel;
    private final Rectangle hitBox;

    public Entity(float x, float y, int width, int height) {
        pos = new Vector2(x, y);
        vel = Vector2.Zero;
        hitBox = new Rectangle(x, y, width, height);
    }

    public void update(float delta) {
        pos.add(vel.cpy().scl(delta));
        hitBox.setPosition(pos);
    }


    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getPos() {
        return pos;
    }

    public Vector2 getVel() {
        return vel;
    }

    public void setVel(Vector2 vel) {
        this.vel = vel;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

}
