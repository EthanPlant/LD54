package com.aquilla.ludumdare.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class WaveManager {
    private Wave currentWave;
    private int waveNumber;

    private final Array<Enemy> enemies;

    public WaveManager() {
        enemies = new Array<>();
        enemies.add(new BasicEnemy(100, 100));
        enemies.add(new BasicEnemy(200, 100));
        enemies.add(new BasicEnemy(300, 100));

        waveNumber = 1;
        Gdx.app.log("WaveManager", "Starting wave 1");
        currentWave = new Wave(enemies);
    }

    public void update(float delta, Vector2 playerPos) {
        if (!currentWave.isCompleted()) {
            currentWave.update(delta, playerPos);
        } else {
            waveNumber++;
            currentWave = new Wave(enemies);
            System.out.println(currentWave.getEnemies());
            Gdx.app.log("WaveManager", "Starting wave " + waveNumber);
        }
    }

    public Array<Enemy> getEnemies() {
        return currentWave.getEnemies();
    }
}
