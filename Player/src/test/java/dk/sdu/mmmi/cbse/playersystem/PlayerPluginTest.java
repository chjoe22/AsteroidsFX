package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerPluginTest {

    private PlayerPlugin playerPlugin;
    private GameData gameData;
    private World world;

    @BeforeEach
    public void setup(){
        playerPlugin = new PlayerPlugin();
        gameData = new GameData();
        world = new World();
    }

    @Test
    public void testPlayerCreation(){
        playerPlugin.start(gameData, world);
        assertFalse(world.getEntities().isEmpty());
        assertNotNull(world.getEntities());
    }

    @Test
    public void testPlayerRemoval(){
        playerPlugin.start(gameData, world);
        playerPlugin.stop(gameData, world);
        assertTrue(world.getEntities().isEmpty());
    }
}