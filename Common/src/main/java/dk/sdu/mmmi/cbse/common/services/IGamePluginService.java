package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * A Start and Stop application Provider
 */

public interface IGamePluginService {

    /**
     * The Start method runs as the application starts
     * @param gameData
     * @param world
     */

    void start(GameData gameData, World world);

    /**
     * The Stop method runs as the application stop (if implemented)
     * @param gameData
     * @param world
     */

    void stop(GameData gameData, World world);
}
