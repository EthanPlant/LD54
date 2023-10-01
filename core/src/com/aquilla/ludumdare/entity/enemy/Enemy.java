package com.aquilla.ludumdare.entity.enemy;

import com.aquilla.ludumdare.entity.Entity;
import com.aquilla.ludumdare.entity.Player;

public abstract class Enemy extends Entity {
    private int speed;
    private float health;

    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public void onHit() {
        health -= Player.PLAYER_DAMAGE;
    }

    public int getSpeed() {
        return speed;
    }

    public float getHealth() {
        return health;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHealth(float health) {
        this.health = health;
    }
}
