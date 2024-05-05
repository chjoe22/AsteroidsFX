package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityTags;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;

public class EnemyFactory {

    public Entity createEnemyShip(GameData gameData){
        Entity enemyShip = new Enemy();

        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);

        enemyShip.setX(gameData.getDisplayWidth()/4);
        enemyShip.setY(gameData.getDisplayHeight()/4);

        enemyShip.setRadius(15);
        enemyShip.setTag(EntityTags.ENEMY);

        return enemyShip;
    }
}
