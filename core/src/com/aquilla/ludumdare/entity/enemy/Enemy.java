package com.aquilla.ludumdare.entity.enemy;

import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.entity.Bullet;
import com.aquilla.ludumdare.entity.Entity;
import com.aquilla.ludumdare.entity.Player;
import com.badlogic.gdx.utils.Array;

public abstract class Enemy extends Entity {
    private int speed;
    private float health;
    private float damage;

    private float attackRadius;
    private float attackTimer;
    private float attackCooldown;

    private final Array<Bullet> bullets;

    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
        bullets = new Array<>();
    }

    @Override
    public void update(float delta) {
        attackTimer += delta;

        super.update(delta);
    }

    public abstract void attack(Player player);

    public void onHit() {
        health -= Player.PLAYER_DAMAGE;
        Assets.getInstance().getSound("enemy_hit_sfx").play();
    }

    public int getSpeed() {
        return speed;
    }

    public float getHealth() {
        return health;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getAttackRadius() {
        return attackRadius;
    }

    public void setAttackRadius(float radius) {
        attackRadius = radius;
    }

    public float getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(float attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public float getAttackTimer() {
        return attackTimer;
    }

    public void setAttackTimer(float attackTimer) {
        this.attackTimer = attackTimer;
    }

    public Array<Bullet> getBullets() {
        return bullets;
    }

}
