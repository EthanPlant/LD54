package com.aquilla.ludumdare.entity;

import com.aquilla.ludumdare.assets.Assets;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Entity {
    private static final int SPEED = 300;

    public Bullet(float x, float y, int width, int height, Vector2 targetPos) {
        super(x, y, width, height);
        setTexture(Assets.getInstance().getTexture("bullet_tex"));

        setVel(targetPos.cpy().sub(getPos()).nor().scl(SPEED));
    }
}
