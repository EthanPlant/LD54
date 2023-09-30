package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.assets.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class State implements Screen {

    private final LudumDare game;
    private final Viewport port;
    private final OrthographicCamera cam;
    abstract public void update(float delta);
    abstract public void draw();

    public State(LudumDare game) {
        this.game = game;

        cam = new OrthographicCamera(LudumDare.WIDTH, LudumDare.HEIGHT);
        port = new ExtendViewport(LudumDare.WIDTH, LudumDare.HEIGHT, cam);
        cam.position.set((float) LudumDare.WIDTH / 2, (float) LudumDare.HEIGHT / 2, 0);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        draw();
    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
    }

    public LudumDare getGame() {
        return game;
    }

    public OrthographicCamera getCam() {
        return cam;
    }

    public Assets getAssets() {
        return Assets.getInstance();
    }

    public void setState(State next) {
        game.setScreen(next);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
