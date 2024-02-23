package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class EnemyControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        int max = 10;
        int min = 1;
        int chanceRight = 1;
        int chanceLeft = 2;
        for (Entity enemy : world.getEntities(Enemy.class)){
            if (Math.random() * (chanceLeft - chanceRight + 1) + 1 == chanceRight){
                enemy.setRotation(enemy.getRotation() - (Math.random() * (max - min + 1)) + 1);
            }
            if (Math.random() * (chanceLeft - chanceRight + 1) + 1 == chanceLeft){
                enemy.setRotation(enemy.getRotation() + (Math.random() * (max - min + 1)) + 1);
            }
            //enemy.setRotation(enemy.getRotation() + (Math.random() * (max - min + 1)) + 1);
            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX);
            enemy.setY(enemy.getY() + changeY);

        }
    }
}
