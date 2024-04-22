package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityTags;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin  implements IGamePluginService {
    private Entity enemy;
    public EnemyPlugin(){

    }
    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }
    private Entity createEnemyShip(GameData gameData){
        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemyShip.setX(gameData.getDisplayWidth()/4);
        enemyShip.setY(gameData.getDisplayHeight()/4);
        enemyShip.setTag(EntityTags.ENEMY);
        return enemyShip;
    }
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}
