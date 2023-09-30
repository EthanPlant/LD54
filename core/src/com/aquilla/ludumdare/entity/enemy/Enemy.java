package com.aquilla.ludumdare.entity.enemy;

import com.aquilla.ludumdare.entity.Entity;

public abstract class Enemy extends Entity {
    private int speed;

    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
