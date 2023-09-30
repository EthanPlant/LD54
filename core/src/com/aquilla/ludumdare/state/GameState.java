package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;
import com.badlogic.gdx.graphics.Texture;

public class GameState extends State {

    public GameState(LudumDare game) {
        super(game);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw() {
        getGame().getBatch().setProjectionMatrix(getCam().combined);
        getGame().getBatch().begin();
        getGame().getBatch().draw(new Texture("badlogic.jpg"), 0, 0);
        getGame().getBatch().end();
    }
}
