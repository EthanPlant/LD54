package com.aquilla.ludumdare.entity.enemy;

import com.aquilla.ludumdare.entity.Player;
import com.aquilla.ludumdare.util.Pathfinding;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class WaveManager {
    private Wave currentWave;
    private int waveNumber;

    private final Array<Enemy> enemies;
    private Map<Vector2, Vector2> paths;

    public WaveManager() {
        enemies = new Array<>();
        enemies.add(new BasicEnemy(100, 50));
        enemies.add(new BasicEnemy(200, 150));
        enemies.add(new BasicEnemy(300, 150));

        paths = new HashMap<>();

        waveNumber = 1;
        Gdx.app.log("WaveManager", "Starting wave 1");
        currentWave = new Wave(enemies);
    }

    public void update(float delta, Player player) {
        if (!currentWave.isCompleted()) {
            currentWave.update(delta, player, paths);
        } else {
            waveNumber++;
            currentWave = new Wave(enemies);
            Gdx.app.log("WaveManager", "Starting wave " + waveNumber);
        }
    }

    public void updatePaths(Player player) {
        paths = Pathfinding.calculatePaths(player);
    }

    public Array<Enemy> getEnemies() {
        return currentWave.getEnemies();
    }
}
