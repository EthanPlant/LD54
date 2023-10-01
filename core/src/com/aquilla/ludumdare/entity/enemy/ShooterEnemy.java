package com.aquilla.ludumdare.entity.enemy;

import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.entity.Bullet;
import com.aquilla.ludumdare.entity.Player;

public class ShooterEnemy extends Enemy {
    public ShooterEnemy(float x, float y) {
        super(x, y, 16, 16);
        setTexture(Assets.getInstance().getTexture("shooter_tex"));
        setHealth(40);
        setSpeed(35);
        setDamage(15);
        setAttackRadius(80);
        setAttackCooldown(0.75f);
        setAttackTimer(getAttackCooldown());
    }

    @Override
    public void attack(Player player) {
        if (getAttackTimer() > getAttackCooldown()) {
            Bullet b = new Bullet(getPos().x, getPos().y, 8, 8, player.getPos());
            getBullets().add(b);
            setAttackTimer(0);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        for (Bullet b : getBullets()) {
            b.update(delta);
        }
    }
}
