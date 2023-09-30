package com.aquilla.ludumdare.entity;

import com.aquilla.ludumdare.assets.Assets;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Player extends Entity {
    public static final int PLAYER_SPEED = 100;
    private static final float ATTACK_COOLDOWN = 0.1f;

    private final Array<Bullet> bullets;
    private float attackTimer;

    private Direction dir;

    public Player(float x, float y) {
        super(x, y, 16, 16);
        setTexture(Assets.getInstance().getTexture("player_down_tex"));
        bullets = new Array<>();
        attackTimer = ATTACK_COOLDOWN;
        dir = Direction.DOWN;
    }

    public void shoot() {
        if (attackTimer >= ATTACK_COOLDOWN) {
            Vector2 target = getVel().cpy().scl(1000); // Point a vector in the direction of the player's velocity
            Bullet b = new Bullet(getPos().x, getPos().y, 5, 5, target);
            bullets.add(b);
            attackTimer = 0;
        }
    }

    @Override
    public void update(float delta) {
        attackTimer += delta;

        switch (dir) {
            case DOWN:
                setTexture(Assets.getInstance().getTexture("player_down_tex"));
                break;
            case UP:
                setTexture(Assets.getInstance().getTexture("player_up_tex"));
                break;
            case LEFT:
                setTexture(Assets.getInstance().getTexture("player_left_tex"));
                break;
            case RIGHT:
                setTexture(Assets.getInstance().getTexture("player_right_tex"));
                break;
        }

        super.update(delta);
    }

    public Array<Bullet> getBullets() {
        return bullets;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
}
