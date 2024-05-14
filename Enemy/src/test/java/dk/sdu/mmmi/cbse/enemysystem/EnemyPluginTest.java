package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyPluginTest {
    private EnemyPlugin enemyPlugin;
    private GameData gameData;
    private World world;

    @BeforeEach
    public void setup(){
        enemyPlugin = new EnemyPlugin();
        gameData = new GameData();
        world = new World();
    }

    @Test
    public void testEnemyCreation(){
        enemyPlugin.start(gameData, world);
        assertFalse(world.getEntities().isEmpty());
        assertNotNull(world.getEntities());
    }

    @Test
    public void testEnemyRemoval(){
        enemyPlugin.start(gameData, world);
        enemyPlugin.stop(gameData, world);
        assertTrue(world.getEntities().isEmpty());
    }
}