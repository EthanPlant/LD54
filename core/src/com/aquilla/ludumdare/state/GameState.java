package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.input.InputManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameState extends State {

    private final TiledMap map;
    private final TiledMapRenderer mapRenderer;

    private final InputManager inputManager;

    public GameState(LudumDare game) {
        super(game);

        map = getAssets().getTiledMap("test_map");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);
    }

    @Override
    public void update(float delta) {
        if (inputManager.isUp()) {
            Gdx.app.log("Game", "Up");
        }
    }

    @Override
    public void draw() {
        getGame().getBatch().setProjectionMatrix(getCam().combined);
        mapRenderer.setView(getCam());
        getGame().getBatch().begin();
        mapRenderer.render();
        getGame().getBatch().end();
    }
}
