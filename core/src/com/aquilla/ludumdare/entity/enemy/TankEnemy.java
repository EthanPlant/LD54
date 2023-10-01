package com.aquilla.ludumdare.entity.enemy;

import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.entity.Player;

public class TankEnemy extends Enemy {
    public TankEnemy(float x, float y) {
        super(x, y, 16, 16);
        setTexture(Assets.getInstance().getTexture("enemy_tex"));
        setHealth(100);
        setSpeed(20);
        setDamage(3);
        setAttackRadius(16);
        setAttackCooldown(2f);
        setAttackTimer(getAttackCooldown());
    }

    @Override
    public void attack(Player player) {
        if (getAttackTimer() >= getAttackCooldown()) {
            player.damage(getDamage());
            setAttackTimer(0);
        }
    }
}
