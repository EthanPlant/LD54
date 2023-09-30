package com.aquilla.ludumdare.entity.enemy;

import com.aquilla.ludumdare.assets.Assets;

public class BasicEnemy extends Enemy {
    public BasicEnemy(float x, float y) {
        super(x, y, 16, 16);
        setTexture(Assets.getInstance().getTexture("enemy_tex"));
        setSpeed(60);
    }
}
