package com.aquilla.ludumdare.entity;

import com.aquilla.ludumdare.assets.Assets;

public class Player extends Entity {

    public static final int PLAYER_SPEED = 100;
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        setTexture(Assets.getInstance().getTexture("player_tex"));
    }
}
