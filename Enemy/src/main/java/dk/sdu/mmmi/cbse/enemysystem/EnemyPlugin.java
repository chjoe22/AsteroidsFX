package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityTags;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {
    private Entity enemy;
    public EnemyPlugin(){

    }
    @Override
    public void start(GameData gameData, World world) {
        enemy = new EnemyFactory().createEnemyShip(gameData);
        world.addEntity(enemy);
    }


    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}
