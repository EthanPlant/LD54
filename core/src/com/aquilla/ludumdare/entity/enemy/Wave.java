package com.aquilla.ludumdare.entity.enemy;

import com.aquilla.ludumdare.entity.Player;
import com.aquilla.ludumdare.util.CollisionHandler;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Wave {
    private final Array<Enemy> enemies;

    private boolean completed;

    public Wave(Array<Enemy> enemies) {
        this.enemies = new Array<>();
        System.out.println(enemies);
        for (Enemy e : enemies) {
            this.enemies.add(new BasicEnemy(e.getPos().x, e.getPos().y));
        }
        completed = false;
    }

    public void update(float delta, Player player) {
        for (Enemy e : enemies) {
            if (e.getHealth() <= 0) {
                enemies.removeValue(e, true);
            } else {
                Vector2 dir = player.getPos().cpy().sub(e.getPos()).nor();
                Vector2 vel = dir.cpy();
                for (int i = 0; i < enemies.size; i++) {
                    Enemy e2 = enemies.get(i);
                    if (e != e2) {
                        Vector2 edir = e2.getPos().cpy().sub(e.getPos()).nor();
                        vel.sub(edir.scl(500 / e.getPos().dst2(e2.getPos()))).nor();
                    }
                }
                e.setVel(vel.scl(e.getSpeed()));
                Vector2 startingPos = e.getPos().cpy();
                e.update(delta);
                if (CollisionHandler.isCollidingWithMapObject(e)) {
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
