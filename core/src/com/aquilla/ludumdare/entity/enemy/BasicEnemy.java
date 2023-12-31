package com.aquilla.ludumdare.entity.enemy;

import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.entity.Player;

public class BasicEnemy extends Enemy {
    private static final float BASIC_DAMAGE = 5;

    public BasicEnemy(float x, float y) {
        super(x, y, 16, 16);
        setHealth(20);
        setTexture(Assets.getInstance().getTexture("enemy_tex"));
        setSpeed(40);
        setAttackRadius(16);
        setAttackCooldown(1f);
        setAttackTimer(getAttackCooldown());
    }

    @Override
    public void attack(Player player) {
        if (getAttackTimer() >= getAttackCooldown()) {
            player.damage(BASIC_DAMAGE);
            setAttackTimer(0);
        }
    }
}
