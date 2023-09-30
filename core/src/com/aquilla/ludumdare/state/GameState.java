package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.assets.Assets;

public class GameState extends State {

    public GameState(LudumDare game) {
        super(game);
        Assets.getInstance().loadTexture("badlogic.jpg", "badlogic");
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw() {
        getGame().getBatch().setProjectionMatrix(getCam().combined);
        getGame().getBatch().begin();
        getGame().getBatch().draw(Assets.getInstance().getTexture("badlogic"), 0, 0);
        getGame().getBatch().end();
    }
}
