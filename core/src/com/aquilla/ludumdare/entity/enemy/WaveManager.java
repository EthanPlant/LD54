package com.aquilla.ludumdare.entity.enemy;

import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.entity.Player;
import com.aquilla.ludumdare.state.GameState;
import com.aquilla.ludumdare.state.WinState;
import com.aquilla.ludumdare.util.Pathfinding;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class WaveManager {
    private Wave currentWave;
    private int waveNumber;

    private final Array<Array<Enemy>> waves;

    private Map<Vector2, Vector2> paths;

    public WaveManager() {
        Array<Enemy> waveOneEnemies = new Array<>();
        waveOneEnemies.add(new BasicEnemy(48, 16));
        waveOneEnemies.add(new BasicEnemy(256, 32));
        waveOneEnemies.add(new BasicEnemy(256, 224));

        Array<Enemy> waveTwoEnemies = new Array<>();
        waveTwoEnemies.add(new BasicEnemy(16, 224));
        waveTwoEnemies.add(new BasicEnemy(80, 64));
        waveTwoEnemies.add(new BasicEnemy(352, 224));
        waveTwoEnemies.add(new BasicEnemy(224, 176));
        waveTwoEnemies.add(new ShooterEnemy(272, 0));

        Array<Enemy> waveThreeEnemies = new Array<>();
        waveThreeEnemies.add(new BasicEnemy(16, 192));
        waveThreeEnemies.add(new BasicEnemy(96, 16));
        waveThreeEnemies.add(new BasicEnemy(240, 224));
        waveThreeEnemies.add(new BasicEnemy(336, 0));
        waveThreeEnemies.add(new ShooterEnemy(32, 16));
        waveThreeEnemies.add(new ShooterEnemy(320, 224));
        waveThreeEnemies.add(new TankEnemy(192, 64));

        Array<Enemy> waveFourEnemies = new Array<>();
        waveFourEnemies.add(new BasicEnemy(16, 224));
        waveFourEnemies.add(new BasicEnemy(48, 16));
        waveFourEnemies.add(new BasicEnemy(192, 224));
        waveFourEnemies.add(new BasicEnemy(272, 0));
        waveFourEnemies.add(new BasicEnemy(320, 224));
        waveFourEnemies.add(new ShooterEnemy(96, 0));
        waveFourEnemies.add(new ShooterEnemy(384, 176));
        waveFourEnemies.add(new TankEnemy(368, 176));
        waveFourEnemies.add(new TankEnemy(208, 16));

        Array<Enemy> waveFiveEnemies = new Array<>();
        waveFiveEnemies.add(new BasicEnemy(128, 80));
        waveFiveEnemies.add(new BasicEnemy(192, 16));
        waveFiveEnemies.add(new BasicEnemy(256, 80));
        waveFiveEnemies.add(new BasicEnemy(192, 160));
        waveFiveEnemies.add(new ShooterEnemy(192, 112));
        waveFiveEnemies.add(new BossEnemy(192, 112));

        waves = new Array<>();
        waves.add(waveOneEnemies);
        waves.add(waveTwoEnemies);
        waves.add(waveThreeEnemies);
        waves.add(waveFourEnemies);
        waves.add(waveFiveEnemies);

        paths = new HashMap<>();

        waveNumber = 1;
        Gdx.app.log("WaveManager", "Starting wave 1");
        currentWave = new Wave(waves.get(0));
    }

    public void update(float delta, Player player, GameState state) {
        if (!currentWave.isCompleted()) {
            currentWave.update(delta, player, paths);
        } else {
            if (waveNumber >= 5) {
                state.setState(new WinState(state.getGame()));
                Assets.getInstance().getMusic("bgm").stop();
            } else {
                waveNumber++;
                currentWave = new Wave(waves.get(waveNumber - 1));
                Assets.getInstance().getSound("enemy_spawn_sfx").play();
                Gdx.app.log("WaveManager", "Starting wave " + waveNumber);
            }
        }
    }

    public void updatePaths(Player player) {
        paths = Pathfinding.calculatePaths(player);
    }

    public Array<Enemy> getEnemies() {
        return currentWave.getEnemies();
    }
}
