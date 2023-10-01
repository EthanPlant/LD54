package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.entity.Bullet;
import com.aquilla.ludumdare.entity.Direction;
import com.aquilla.ludumdare.entity.Player;
import com.aquilla.ludumdare.entity.enemy.Enemy;
import com.aquilla.ludumdare.entity.enemy.WaveManager;
import com.aquilla.ludumdare.input.InputManager;
import com.aquilla.ludumdare.util.CollisionHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameState extends State {

    private final TiledMapRenderer mapRenderer;

    private final InputManager inputManager;

    private final Player player;
    private final WaveManager waveManager;

    public GameState(LudumDare game) {
        super(game);

        TiledMap map = getAssets().getTiledMap("map");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);

        player = new Player(getCam().position.x, getCam().position.y);
        waveManager = new WaveManager();
    }

    @Override
    public void update(float delta) {
        if (inputManager.isUp()) {
            player.setVel(0, Player.PLAYER_SPEED);
            player.setDir(Direction.UP);
        } else if (inputManager.isDown()) {
            player.setVel(0, -Player.PLAYER_SPEED);
            player.setDir(Direction.DOWN);
        } else if (inputManager.isLeft()) {
            player.setVel(-Player.PLAYER_SPEED, 0);
            player.setDir(Direction.LEFT);
        } else if (inputManager.isRight()) {
            player.setVel(Player.PLAYER_SPEED, 0);
            player.setDir(Direction.RIGHT);
        } else {
            player.setVel(0, 0);
        }

        if (inputManager.isShooting()) {
            player.shoot();
        }

        for (Bullet b : player.getBullets()) {
            b.update(delta);
            for (Enemy e : waveManager.getEnemies()) {
                if (CollisionHandler.areHitboxesColliding(b.getHitBox(), e.getHitBox())) {
                    player.getBullets().removeValue(b, true);
                    e.onHit();
                }
            }
        }

        Vector2 playerStartPos = player.getPos().cpy();
        player.update(delta);
        if (CollisionHandler.isCollidingWithMapObject(player)) {
            player.setPos(playerStartPos);
        }

        waveManager.update(delta, player.getPos().cpy());
    }

    @Override
    public void draw() {
        getGame().getBatch().setProjectionMatrix(getCam().combined);
        mapRenderer.setView(getCam());
        getGame().getBatch().begin();
        mapRenderer.render();
        for (Enemy e : waveManager.getEnemies()) {
            getGame().getBatch().draw(e.getTexture(), e.getPos().x, e.getPos().y);
        }
        getGame().getBatch().draw(player.getTexture(), player.getPos().x, player.getPos().y);
        for (Bullet b : player.getBullets()) {
            getGame().getBatch().draw(b.getTexture(), b.getPos().x, b.getPos().y);
        }
        getGame().getBatch().end();
    }
}
