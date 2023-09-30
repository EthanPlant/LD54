package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;

public class GameState extends State {

    public GameState(LudumDare game) {
        super(game);
        getAssets().loadTexture("badlogic.jpg", "badlogic");
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw() {
        getGame().getBatch().setProjectionMatrix(getCam().combined);
        getGame().getBatch().begin();
        getGame().getBatch().draw(getAssets().getTexture("badlogic"), 0, 0);
        getGame().getBatch().end();
    }
}
