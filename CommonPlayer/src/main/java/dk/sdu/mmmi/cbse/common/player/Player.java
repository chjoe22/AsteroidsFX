package dk.sdu.mmmi.cbse.common.player;

import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 *
 * @author Emil
 */
public class Player extends Entity {
    private final double cooldown = 0.25d;
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
