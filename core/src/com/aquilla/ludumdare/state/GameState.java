package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.entity.Player;
import com.aquilla.ludumdare.entity.enemy.BasicEnemy;
import com.aquilla.ludumdare.entity.enemy.Enemy;
import com.aquilla.ludumdare.input.InputManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

public class GameState extends State {

    private final TiledMap map;
    private final TiledMapRenderer mapRenderer;

    private final InputManager inputManager;

    private final Player player;
    private final Array<Enemy> enemies;

    public GameState(LudumDare game) {
        super(game);

        map = getAssets().getTiledMap("test_map");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);

        player = new Player(getCam().position.x, getCam().position.y, 16, 16);
        enemies = new Array<>();
        enemies.add(new BasicEnemy(100, 100));
        enemies.add(new BasicEnemy(200, 100));
        enemies.add(new BasicEnemy(300, 100));
    }

    @Override
    public void update(float delta) {
        if (inputManager.isUp()) {
            player.setVel(0, Player.PLAYER_SPEED);
        } else if (inputManager.isDown()) {
            player.setVel(0, -Player.PLAYER_SPEED);
        } else if (inputManager.isLeft()) {
            player.setVel(-Player.PLAYER_SPEED, 0);
        } else if (inputManager.isRight()) {
            player.setVel(Player.PLAYER_SPEED, 0);
        } else {
            player.setVel(0, 0);
        }

        player.update(delta);
    }

    @Override
    public void draw() {
        getGame().getBatch().setProjectionMatrix(getCam().combined);
        mapRenderer.setView(getCam());
        getGame().getBatch().begin();
        mapRenderer.render();
        getGame().getBatch().draw(player.getTexture(), player.getPos().x, player.getPos().y);
        for (Enemy e : enemies) {
            getGame().getBatch().draw(e.getTexture(), e.getPos().x, e.getPos().y);
        }
        getGame().getBatch().end();
    }
}
