package com.aquilla.ludumdare.entity;

import com.aquilla.ludumdare.assets.Assets;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Player extends Entity {
    public static final int PLAYER_SPEED = 100;
    public static final int PLAYER_DAMAGE = 20;
    private static final float ATTACK_COOLDOWN = 0.4f;
    public static final float MAX_HEALTH = 100f;

    private final Array<Bullet> bullets;
    private float attackTimer;
    private Direction dir;
    private State state;
    private float health;

    private final Array<Animation<TextureRegion>> walkingAnimations;
    private final Array<Animation<TextureRegion>> shootingAnimations;
    private float animationTimer;

    public Player(float x, float y) {
        super(x, y, 16, 16);
        setTexture(Assets.getInstance().getRegionFromTextureAtlas("player_tex", "player_down"));
        bullets = new Array<>();
        attackTimer = ATTACK_COOLDOWN;
        dir = Direction.DOWN;
        state = State.STANDING;
        health = MAX_HEALTH;

        walkingAnimations = new Array<>();
        walkingAnimations.add(new Animation<TextureRegion>(
                0.1f,
                Assets.getInstance().getTextureAtlas("player_tex").findRegions("player_walk_down"),
                Animation.PlayMode.LOOP
        ));
        walkingAnimations.add(new Animation<TextureRegion>(
                0.1f,
                Assets.getInstance().getTextureAtlas("player_tex").findRegions("player_walk_up"),
                Animation.PlayMode.LOOP
        ));
        walkingAnimations.add(new Animation<TextureRegion>(
                0.1f,
                Assets.getInstance().getTextureAtlas("player_tex").findRegions("player_walk_left"),
                Animation.PlayMode.LOOP
        ));
        walkingAnimations.add(new Animation<TextureRegion>(
                0.1f,
                Assets.getInstance().getTextureAtlas("player_tex").findRegions("player_walk_right"),
                Animation.PlayMode.LOOP
        ));

        shootingAnimations = new Array<>();
        shootingAnimations.add(new Animation<TextureRegion>(
                0.1f,
                Assets.getInstance().getTextureAtlas("player_tex").findRegions("player_shoot_down"),
                Animation.PlayMode.NORMAL
        ));
        shootingAnimations.add(new Animation<TextureRegion>(
                0.1f,
                Assets.getInstance().getTextureAtlas("player_tex").findRegions("player_shoot_up"),
                Animation.PlayMode.NORMAL
        ));
        shootingAnimations.add(new Animation<TextureRegion>(
                0.1f,
                Assets.getInstance().getTextureAtlas("player_tex").findRegions("player_shoot_left"),
                Animation.PlayMode.NORMAL
        ));
        shootingAnimations.add(new Animation<TextureRegion>(
                0.1f,
                Assets.getInstance().getTextureAtlas("player_tex").findRegions("player_shoot_right"),
                Animation.PlayMode.NORMAL
        ));
    }

    public void shoot() {
        state = State.SHOOTING;
        if (animationTimer > shootingAnimations.get(0).getAnimationDuration()) {
            animationTimer = 0;
        }
        if (attackTimer >= ATTACK_COOLDOWN) {
            Vector2 target = null;
            switch (dir) {
                case UP:
                    target = new Vector2(getPos().x, getPos().y + 1);
                    break;
                case DOWN:
                    target = new Vector2(getPos().x, getPos().y - 1);
                    break;
                case LEFT:
                    target = new Vector2(getPos().x - 1, getPos().y);
                    break;
                case RIGHT:
                    target = new Vector2(getPos().x + 1, getPos().y);
                    break;
            }
            Bullet b = new Bullet(getPos().x, getPos().y, 5, 5, target);
            bullets.add(b);
            attackTimer = 0;
        }
    }

    @Override
    public void update(float delta) {
        attackTimer += delta;
        if (state != State.STANDING) {
            animationTimer += delta;
        } else {
            animationTimer = 0;
        }

        if (!getVel().equals(Vector2.Zero) && state != State.SHOOTING) {
            state = State.WALKING;
        } else if (state != State.SHOOTING || shootingAnimations.get(0).isAnimationFinished(animationTimer)) {
            state = State.STANDING;
        }

        switch (dir) {
            case DOWN:
                if (state == State.STANDING) {
                    setTexture(Assets.getInstance().getRegionFromTextureAtlas("player_tex", "player_down"));
                } else if (state == State.WALKING) {
                    setTexture(walkingAnimations.get(0).getKeyFrame(animationTimer));
                } else if (state == State.SHOOTING) {
                    setTexture(shootingAnimations.get(0).getKeyFrame(animationTimer));
                }
                break;
            case UP:
                if (state == State.STANDING) {
                    setTexture(Assets.getInstance().getRegionFromTextureAtlas("player_tex", "player_up"));
                } else if (state == State.WALKING) {
                    setTexture(walkingAnimations.get(1).getKeyFrame(animationTimer));
                } else if (state == State.SHOOTING) {
                    setTexture(shootingAnimations.get(1).getKeyFrame(animationTimer));
                }
                break;
            case LEFT:
                if (state == State.STANDING) {
                    setTexture(Assets.getInstance().getRegionFromTextureAtlas("player_tex", "player_left"));
                } else if (state == State.WALKING) {
                    setTexture(walkingAnimations.get(2).getKeyFrame(animationTimer));
                } else if (state == State.SHOOTING) {
                    setTexture(shootingAnimations.get(2).getKeyFrame(animationTimer));
                }
                break;
            case RIGHT:
                if (state == State.STANDING) {
                    setTexture(Assets.getInstance().getRegionFromTextureAtlas("player_tex", "player_right"));
                } else if (state == State.WALKING) {
                    setTexture(walkingAnimations.get(3).getKeyFrame(animationTimer));
                } else if (state == State.SHOOTING) {
                    setTexture(shootingAnimations.get(3).getKeyFrame(animationTimer));
                }
                break;
        }

        super.update(delta);
    }

    public void damage(float damage) {
        health -= damage;
    }

    public Array<Bullet> getBullets() {
        return bullets;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public float getHealth() {
        return health;
    }
}
