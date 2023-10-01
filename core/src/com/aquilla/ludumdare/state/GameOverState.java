package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;

public class GameOverState extends State {
    public GameOverState(LudumDare game) {
        super(game);
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
}
