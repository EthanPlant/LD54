package com.aquilla.ludumdare.entity.enemy;

import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.entity.Player;
import com.badlogic.gdx.Gdx;

public class BasicEnemy extends Enemy {
    public BasicEnemy(float x, float y) {
        super(x, y, 16, 16);
        setHealth(100);
        setTexture(Assets.getInstance().getTexture("enemy_tex"));
        setSpeed(40);
        setAttackRadius(16);
        setAttackCooldown(0.5f);
        setAttackTimer(getAttackCooldown());
    }

    @Override
    public void attack(Player player) {
        if (getAttackTimer() >= getAttackCooldown()) {
            Gdx.app.log("BasicEnemy", "Attacked!");
            setAttackTimer(0);
        }
    }
}
