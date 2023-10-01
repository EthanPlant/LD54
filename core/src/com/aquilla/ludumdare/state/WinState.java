package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;

public class WinState extends State {
    public WinState(LudumDare game) {
        super(game);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw() {
        getGame().getBatch().begin();
        getGame().getBatch().draw(getAssets().getTexture("winscreen"), 0, 0, LudumDare.WIDTH, LudumDare.HEIGHT);
        getGame().getBatch().end();
    }
}
