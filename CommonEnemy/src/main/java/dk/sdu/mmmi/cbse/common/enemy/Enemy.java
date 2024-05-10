package dk.sdu.mmmi.cbse.common.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity {
    private final double cooldown = 0.75d;
    private double timer;

    public void tick(double time){
        if (timer < cooldown){
            timer += time;
        }
    }
    public boolean fire(){
        if (timer >= cooldown){
            timer -= cooldown;
            return true;
        }
        return false;
    }
}
