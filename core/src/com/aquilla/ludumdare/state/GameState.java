package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.entity.Bullet;
import com.aquilla.ludumdare.entity.Direction;
import com.aquilla.ludumdare.entity.Player;
import com.aquilla.ludumdare.entity.enemy.Enemy;
import com.aquilla.ludumdare.entity.enemy.WaveManager;
import com.aquilla.ludumdare.input.InputManager;
import com.aquilla.ludumdare.ui.HealthBar;
import com.aquilla.ludumdare.util.CollisionHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
public class GameState extends State {

    private final TiledMapRenderer mapRenderer;

    private final InputManager inputManager;

    private final Player player;
    private final WaveManager waveManager;

    private final Stage stage;
    private final HealthBar playerHealth;

    public GameState(LudumDare game) {
        super(game);

        TiledMap map = getAssets().getTiledMap("map");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);

        player = new Player(getCam().position.x, getCam().position.y);
        waveManager = new WaveManager();
        waveManager.updatePaths(player);

        stage = new Stage();
        playerHealth = new HealthBar(250, 20, Player.MAX_HEALTH);
        playerHealth.setPosition(10, stage.getViewport().getWorldHeight() - 30);
        stage.addActor(playerHealth);
    }

    @Override
    public void update(float delta) {
        getCam().update();
        if (player.getHealth() <= 0) {
            setState(new GameOverState(getGame()));
        }

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
            if (CollisionHandler.isCollidingWithMapObject(b)) {
                player.getBullets().removeValue(b, true);
            } else {
                for (Enemy e : waveManager.getEnemies()) {
                    if (CollisionHandler.areEntitiesColliding(b, e)) {
                        player.getBullets().removeValue(b, true);
                        e.onHit();
                    }
                }
            }
        }

        Vector2 playerStartPos = player.getPos().cpy();
        player.update(delta);
        if (CollisionHandler.isCollidingWithMapObject(player)) {
            player.setPos(playerStartPos);
        }
        // Check if player has moved a tile
        if ((int) player.getPos().x / 16 != (int) playerStartPos.x / 16 ||
                (int) player.getPos().y / 16 != (int) playerStartPos.y / 16) {
            waveManager.updatePaths(player);
        }

        waveManager.update(delta, player, this);

        playerHealth.setValue(player.getHealth());
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

        stage.getBatch().setProjectionMatrix(getCam().combined);
        stage.act(Gdx.graphics.getDeltaTime());

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
       stage.getViewport().update(width, height, true);
       super.resize(width, height);
    }
}
