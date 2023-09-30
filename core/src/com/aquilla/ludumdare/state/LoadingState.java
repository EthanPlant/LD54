package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;

public class LoadingState extends State {
    public LoadingState(LudumDare game) {
        super(game);

        loadAssets();
    }

    private void loadAssets() {
        getAssets().loadTexture("texture/bullet.png", "bullet_tex");
        getAssets().loadTexture("texture/enemy.png", "enemy_tex");
        getAssets().loadTexture("texture/player_up.png", "player_up_tex");
        getAssets().loadTexture("texture/player_down.png", "player_down_tex");
        getAssets().loadTexture("texture/player_left.png", "player_left_tex");
        getAssets().loadTexture("texture/player_right.png", "player_right_tex");


        getAssets().loadTiledMap("tilemap/test_map.tmx", "test_map");
    }

    @Override
    public void update(float delta) {
        if (getAssets().update()) {
            setState(new GameState(getGame()));
        }
    }

    @Override
    public void draw() {
        getGame().getBatch().setProjectionMatrix(getCam().combined);
        getGame().getBatch().begin();
        getGame().getBatch().end();
    }
}
