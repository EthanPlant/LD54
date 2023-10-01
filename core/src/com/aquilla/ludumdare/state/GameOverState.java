package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class GameOverState extends State implements InputProcessor {
    public GameOverState(LudumDare game) {
        super(game);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw() {
        getGame().getBatch().begin();
        getGame().getBatch().draw(getAssets().getTexture("gameover"), 0, 0, LudumDare.WIDTH, LudumDare.HEIGHT);
        getGame().getBatch().end();
    }

    @Override
    public boolean keyDown(int keycode) {
        setState(new GameState(getGame()));
        getAssets().getSound("menu_select_sfx").play();
        getAssets().getMusic("titlescreen_bgm").stop();
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
