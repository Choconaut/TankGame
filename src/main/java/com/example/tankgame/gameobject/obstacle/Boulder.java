package com.example.tankgame.gameobject.obstacle;

import com.example.tankgame.gameobject.GameObject;
import com.example.tankgame.gameobject.missile.Missile;

/**
 * This class represents a boulder obstacle in the game.
 * A boulder is a destructible obstacle that can be removed by shooting missiles at it.
 */
public class Boulder extends Obstacle {
    public Boulder(double x, double y) {
        super(x, y );
        this.width = 57;
        this.height = 42;
        this.health = 50;
    }

    @Override
    public void update() {}

    @Override
    public String getImagePath() { return "/com/example/tankgame/images/boulder.png"; }

    @Override
    public void handleCollision(GameObject other) {
        if (other instanceof Missile) {
            collideWithMissile((Missile) other);
        }
    }

    private void collideWithMissile(Missile missile) {
        this.setHealth(this.getHealth() - missile.getDamage());
        System.out.println("Boulder currentHealth: " + this.getHealth());

        if (this.getHealth() <= 0) {
            this.setActive(false);
        }
    }
}
