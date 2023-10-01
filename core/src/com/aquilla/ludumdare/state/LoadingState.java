package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class LoadingState extends State implements InputProcessor {
    public LoadingState(LudumDare game) {
        super(game);

        loadAssets();
        Gdx.input.setInputProcessor(this);
    }

    private void loadAssets() {
        getAssets().loadTexture("texture/bullet.png", "bullet_tex");
        getAssets().loadTexture("texture/enemy.png", "enemy_tex");
        getAssets().loadTexture("texture/titlescreen.png", "titlescreen");
        getAssets().loadTextureAtlas("texture/player.atlas", "player_tex");

        getAssets().loadTiledMap("tilemap/map.tmx", "map");
    }

    @Override
    public void update(float delta) {
        if (getAssets().update()) {
            setState(new TitleState(getGame()));
        }
    }

    @Override
    public void draw() {
        getGame().getBatch().setProjectionMatrix(getCam().combined);
        getGame().getBatch().begin();
        getGame().getBatch().end();
    }

    @Override
    public boolean keyDown(int keycode) {
        setState(new GameState(getGame()));
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
