package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;

public class LoadingState extends State {
    public LoadingState(LudumDare game) {
        super(game);

        loadAssets();
    }

    private void loadAssets() {
        getAssets().loadTexture("badlogic.jpg", "badlogic");
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
