package com.aquilla.ludumdare.entity.enemy;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.entity.Player;
import com.aquilla.ludumdare.util.CollisionHandler;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Map;

public class Wave {
    private final Array<Enemy> enemies;

    private boolean completed;

    public Wave(Array<Enemy> enemies) {
        this.enemies = new Array<>();
        for (Enemy e : enemies) {
            this.enemies.add(new BasicEnemy(e.getPos().x, e.getPos().y));
        }
        completed = false;
    }

    public void update(float delta, Player player, Map<Vector2, Vector2> paths) {
        for (Enemy e : enemies) {
            if (e.getHealth() <= 0) {
                enemies.removeValue(e, true);
            } else {
                int tileX = (int) e.getPos().x / 16;
                int tileY = (int) e.getPos().y / 16;
                Vector2 tile = new Vector2(tileX, tileY);
                Vector2 target = paths.get(tile);
                Vector2 vel = target.cpy().sub(tile);
                e.setVel(vel.scl(e.getSpeed()));
                Vector2 startingPos = e.getPos().cpy();
                e.update(delta);
                if (CollisionHandler.isCollidingWithMapObject(e)
                        || e.getPos().x > LudumDare.WIDTH
                        || e.getPos().y > LudumDare.HEIGHT
                        || e.getPos().x < 0
                        || e.getPos().y < 0) {
                    e.setPos(startingPos);
                }
                if (e.getPos().dst(player.getPos()) < e.getAttackRadius()) {
                    e.attack(player);
                }
            }
        }

        if (enemies.size == 0) {
            completed = true;
        }
    }

    public Array<Enemy> getEnemies() {
        return enemies;
    }

    public boolean isCompleted() {
        return completed;
    }
}
